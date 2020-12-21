# Python 实现支持向量机

import numpy as np
import pandas as pd

from sklearn.model_selection import KFold
from math import exp

# SMO中使用的部分公式可见：https://www.cnblogs.com/lengyue365/p/5043592.html

class SVM:
	
	def __init__(self, C, ) -> None:
		self.C = C		# 参数C（见教材公式65）
		self.b = 0		# 偏移量bias
		self.w = []		# 权重向量
		self.alphas = []	# 保存α
		self.X = []		# 数据集输入值
		self.Y = []		# 标签
		self.m = 0		# 当前数据集行数
		self.toler = 0.0001
		self.Es = []	# 缓存计算出的Ek


	def train(self, X, Y):
		self.X = X
		self.Y = Y
		self.m = X.shape[0]
		self.Es = np.mat(np.zeros((self.m, 2)))
		self.smo()		# 用SMO快速计算b和α
		self.weight()

	def smo(self):
		# 如果所有变量的解都满足KKT，则返回
		# 否则，选择两个α，固定其他变量，针对这两个变量构建一个
		# 两个变量，一个是违反KKT最严重的那个，另一个由约束条件自动确定
		self.b = 0
		self.alphas = np.mat(np.zeros((self.m, 1)))
		i = 0
		maxIter = 200	# 最大迭代次数
		entireSet = True		# 标记本轮是否进行全集遍历
		alphaPairsChanged = 0

		while i < maxIter and (entireSet or alphaPairsChanged > 0):
			alphaPairsChanged = 0 # 记录每一轮遍历修改的α对数
			if entireSet:	# 全集遍历
				for i in range(self.m):
					alphaPairsChanged += self.inner_loop(i)
				entireSet = False
			else:			# 边界遍历
				# 选择(0, C)样本点的索引值
				bound_i = np.nonzero((self.alphas.A > 0)* (self.alphas.A < self.C))[0]
				for i in bound_i:
					alphaPairsChanged += self.inner_loop(i)
			i += 1
		

	# 计算Ek的值
	def calculate_Ek(self, k):
		gxk = float(np.multiply(self.alphas, self.Y).transpose()*\
			kernal(self.X, self.X[k,:])) + self.b
		Ek = gxk - float(self.Y[k])
		return Ek

	# 更新Ek的值
	def update_Ek(self, k):
		Ek = self.calculate_Ek(k)
		self.Es = [1, Ek]

	# 已知i，启发式选择j
	def select_j(self, i, Ei):
		selected_j = -1
		max_delta_E = 0
		final_Ej = 0
		self.Es[i] = [1, Ei]
		validList = np.nonzero(self.Es[:,0].A)[0]
		if len(validList) > 1:
			for j in validList:
				if j == i:
					continue
				Ej = self.calculate_Ek(j)
				delta_E = abs(Ej - Ei)
				if delta_E > max_delta_E:
					max_delta_E = delta_E
					selected_j = j
					final_Ej = Ej
		else:		# 只有i可用（第一次循环）
			selected_j = random_select_j(i, self.m)
			final_Ej = self.calculate_Ek(j)
		return selected_j, final_Ej
	
	# 内循环
	def inner_loop(self, i):
		Ei = self.calculate_Ek(i)
		# 判断Ei是否违反KKT超过toler
		if (self.Y[i]*Ei < -self.toler and self.alphas[i] < self.C) or \
			(self.Y[i]*Ei > self.toler and self.alphas[i] > 0):
			# 根据启发式选择选择j
			j, Ej = self.select_j(i, Ei)
			# 保存原来的αi和αj
			ai_old = self.alphas[i].copy()
			aj_old = self.alphas[j].copy()
			C = self.C
			# 计算L, H
			if self.Y[i] != self.Y[j]:
				L = max(0, aj_old - ai_old)
				H = min(C, C + aj_old - ai_old)
			else:
				L = max(0, aj_old + ai_old - C)
				H = min(C, aj_old + ai_old)
			if L == H:
				return 0		# 返回，继续遍历下一个αi
			# 计算η
			eta = self.X[i,:] * self.X[i,:].transpose() + \
				self.X[j,:] * self.X[j, :].transpose() - \
					2 * self.X[i,:] * self.X[j, :].transpose()
			if eta == 0:
				return 0
			# 更新αj
			alpha_j = self.alphas[j] + self.Y[j] * (Ei - Ej) / eta
			alpha_j = clip_alpha(alpha_j, L, H)
			self.alphas[j] = alpha_j
			self.update_Ek(j)
			epsilon = 0.00001
			if alpha_j - aj_old < epsilon:
				return 0		# 若改变值小于ε，返回
			# 根据αj计算αi
			alpha_i = ai_old + self.Y[i] * self.Y[j] * \
				(alpha_j - aj_old)
			self.alphas[i] = alpha_i
			self.update_Ek(i)
			# 分别计算b1和b2
			b1 = self.b - Ei - self.Y[i] * (alpha_i - ai_old) * \
				self.X[i,:] * self.X[i,:].transpose() - self.Y[j] * \
					(alpha_j - aj_old) * self.X[j,:] * self.X[j,:].transpose()
			b2 = self.b - Ej - self.Y[i] * (alpha_i - ai_old) * \
				self.X[i,:] * self.X[j,:].transpose() - self.Y[j] * \
					(alpha_j - aj_old) * self.X[j,:] * self.X[j,:].transpose()
			# 选择b
			if alpha_i > 0 and alpha_i < C:
				self.b = b1
			elif alpha_j > 0 and alpha_j < C:
				self.b = b2
			else:
				self.b = (b1 + b2) / 2
			return 1	# 完成一次内循环
		else:
			return 0	# 若αi满足KKT条件，则遍历下一个		
	
	# 根据α计算权重向量w
	def weight(self):
		n = self.X.shape[1]
		w = np.mat(np.zeros((1, n)))	
		m = self.m
		for i in range(m):
			if self.alphas[i] > 0:
				w += self.Y[i] * self.alphas[i] * self.X[i,:]
		self.w = w.tolist()




# 根据i随机选择j
def random_select_j(i, m):
	j = i
	while j == i:
		j = int(np.random.uniform(0, m))
	return j

# 在[L, H]范围内裁切alpha
def clip_alpha(alpha_j, L, H):
	if alpha_j < L:
		alpha_j = L
	elif alpha_j > H:
		alpha_j = H
	return alpha_j

def kernal(X, Z, mode= 'rbf', sigma= 0.1):
	m, n = np.shape(X)
	K = np.mat(np.zeros((m, 1))
	if mode == 'rbf':
		for j in range(m):
			delta = X[j,:] - Z
			K[j] = delta * delta.T
		K = exp(-K**2/2*sigma)
	else:
		K = X * Z.T
	return K

def read_data():
	filepath = 'D:\\learnCoding\\Python\\Data Mining\\heart_failure_clinical_records_dataset.csv'
	df = pd.read_csv(filepath)
	dataset = np.array(df)
	return dataset

def main():
	read_data()

if __name__ == "__main__":
	main()