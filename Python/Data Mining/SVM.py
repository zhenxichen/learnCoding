# Python 实现支持向量机

import numpy as np
import pandas as pd

from sklearn.model_selection import KFold
from prettytable import PrettyTable

# SMO中使用的部分公式可见：https://www.cnblogs.com/lengyue365/p/5043592.html

kernel_mode = 'lin'
sigma = 20000

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
		self.K = []		# 缓存核函数计算结果


	def train(self, X, Y):
		self.X = np.mat(X)
		self.Y = np.array(Y).flatten()
		self.Y[self.Y == 0] = -1
		self.m = X.shape[0]
		self.Es = np.zeros(self.m)
		self.K = np.mat(np.zeros((self.m, self.m)))
		for i in range(self.m):
			self.K[:,i] = kernel(self.X, self.X[i,:], kernel_mode, sigma)
		self.smo()		# 用SMO快速计算b和α
		self.weight()

	def smo(self):
		# 如果所有变量的解都满足KKT，则返回
		# 否则，选择两个α，固定其他变量，针对这两个变量构建一个
		# 两个变量，一个是违反KKT最严重的那个，另一个由约束条件自动确定
		self.b = 0
		self.alphas = np.zeros(self.m)
		i = 0
		maxIter = 2000	# 最大迭代次数
		entireSet = True		# 标记本轮是否进行全集遍历
		alphaPairsChanged = 0
		self.update_Es()

		while i < maxIter and (entireSet or alphaPairsChanged > 0):
			alphaPairsChanged = 0 # 记录每一轮遍历修改的α对数
			if entireSet:	# 全集遍历
				for i in range(self.m):
					alphaPairsChanged += self.inner_loop(i)
				entireSet = False
			else:			# 边界遍历
				# 选择(0, C)样本点的索引值
				bound_i = np.nonzero((self.alphas > 0)* (self.alphas < self.C))[0]
				for i in bound_i:
					alphaPairsChanged += self.inner_loop(i)
			i += 1
		

	# 计算Ek的值
	def calculate_Ek(self, k):
		Y = self.Y
		gxk = np.float(np.multiply(self.alphas, Y) * self.K[:,k]) + self.b
		Ek = gxk - np.float(Y[k])
		return Ek

	# 更新Ek的值
	def update_Ek(self, k):
		Ek = self.calculate_Ek(k)
		self.Es[k] = Ek
	
	def update_Es(self):
		for i in range(self.m):
			self.update_Ek(i)

	# 已知i，启发式选择j
	def select_j(self, i, Ei):
		j = -1
		max_delta_E = 0
		Ej = 0
		self.Es[i] = Ei
		if len(self.alphas[(self.alphas != 0) & (self.alphas != self.C)]) > 1:
			if Ei > 0:
				j = np.argmin(self.Es)
			else:
				j = np.argmax(self.Es)
		else:		# 第一次循环
			j = random_select_j(i, self.m)
			Ej = self.calculate_Ek(j)
		return j, Ej
	
	# 内循环
	def inner_loop(self, i):
		Ei = self.calculate_Ek(i)
		# 判断Ei是否违反KKT超过toler
		if ((self.Y[i]*Ei < -self.toler) and (self.alphas[i] < self.C)) or \
			((self.Y[i]*Ei > self.toler) and (self.alphas[i] > 0)):
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
			eta = self.K[i, i] + self.K[j, j] - 2 * self.K[i, j]
			if eta == 0:
				return 0
			# 更新αj
			alpha_j = self.alphas[j]
			alpha_j += self.Y[j] * (Ei - Ej) / eta
			alpha_j = clip_alpha(alpha_j, L, H)
			self.alphas[j] = alpha_j
			epsilon = 0.00001
			if alpha_j - aj_old < epsilon:
				return 0		# 若改变值小于ε，返回
			# 根据αj计算αi
			alpha_i = ai_old + self.Y[i] * self.Y[j] * \
				(alpha_j - aj_old)
			self.alphas[i] = alpha_i
			self.update_Es()
			# 分别计算b1和b2
			b1 = self.b - Ei - self.Y[i] * (alpha_i - ai_old) * \
				self.K[i, i] - self.Y[j] * (alpha_j - aj_old) * self.K[j, j]
			b2 = self.b - Ej - self.Y[i] * (alpha_i - ai_old) * \
				self.K[i, j] - self.Y[j] * (alpha_j - aj_old) * self.K[j, j]
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
		print(w)
		self.w = w

	def predict(self, x):
		kernelEval = kernel(self.X, x, kernel_mode, sigma)
		p = np.multiply(self.Y, self.alphas) * kernelEval + self.b
		if p > 0:
			return 1
		else:
			return -1

	def test(self, X, Y):
		Y[Y == 0] = -1
		TP = 0; FP = 0; FN = 0; TN = 0
		for i in range(len(Y)):
			x = X[i]
			y = Y[i]
			predict = self.predict(x)
			if predict == 1 and y == 1:
				TP += 1
			elif predict == 1 and y == -1:
				FP += 1
			elif predict == -1 and y == 1:
				FN += 1
			else:
				TN += 1
		return TP, FP, FN, TN	
		


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

def kernel(X, Z, mode= 'rbf', sigma= 0.1):
	m, n = np.shape(X)
	K = np.mat(np.zeros((m, 1)))
	if mode == 'rbf':
		K = np.exp(-(np.linalg.norm(X-Z, 2, axis=1))**2/(2*sigma**2)).reshape(-1,1)	
	else:
		K = X * Z.reshape(-1, 1)
	return K

def read_data():
	filepath = 'D:\\learnCoding\\Python\\Data Mining\\heart_failure_clinical_records_dataset.csv'
	df = pd.read_csv(filepath)
	dataset = np.array(df)
	X = dataset[:,0:-1]
	Y = dataset[:,-1]
	return X, Y

def main():
	dataset_X, dataset_Y = read_data()
	kf = KFold(n_splits= 10, shuffle= True)
	svm = SVM(C=200)
	total_count = 0
	accurate = 0
	total_tp = 0; total_fp = 0; total_fn = 0; total_tn = 0
	macro_P = 0.0; macro_R = 0.0
	for train_index, test_index in kf.split(dataset_X):
		train_X, test_X = dataset_X[train_index], dataset_X[test_index]
		train_Y, test_Y = dataset_Y[train_index], dataset_Y[test_index]

		svm.train(train_X, train_Y)
		tp, fp, fn, tn = svm.test(test_X, test_Y)
		total_tp += tp; total_fp += fp; total_fn += fn; total_tn += tn
		if tp == 0:
			macro_P += 0
			macro_R += 0
		else:
			macro_P += tp / (tp + fp)
			macro_R += tp / (tp + fn)
		total_count += (tp + fp + fn + tn)
		accurate += (tp + tn)
		print(tp, fp, fn, tn)
	print("accuracy:", float(accurate)/total_count)
	micro_P = total_tp / (total_tp + total_fp)
	micro_R = total_tp / (total_tp + total_fn)
	micro_F1 = 2 * micro_P * micro_R / (micro_P + micro_R)
	macro_P /= 10; macro_R /= 10
	macro_F1 = 2 * macro_P * macro_R / (macro_P + macro_R)
	table = PrettyTable(['Macro-P', 'Macro-R', 'Macro-F1', \
		'Micro-P', 'Micro-R', 'Micro-F1'])
	table.add_row([macro_P, macro_R, macro_F1, micro_P, micro_R, micro_F1])
	print(table)

if __name__ == "__main__":
	main()