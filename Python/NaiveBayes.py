# Python实现朴素贝叶斯分类器

import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from collections import defaultdict
from math import log2

class NaiveBayes:
	def __init__(self) -> None:
		self.summaries = { }	# 提取的属性特征
		self.prior_dict = { 
			-1: 0.0,
			1: 0.0
		}	# 存储先验概率
		self.count_dict = { 
			-1: 0,
			1: 0
		}	# 存储记录的出现频率
		self.count_con_dict = defaultdict(int)
		self.total_num = 0		# 训练集的总长度
		self.condition_dict = defaultdict(int)	# 存储条件概率

	def prior_probability(self, Y):
		num = len(Y)
		# print(Y)
		self.total_num += num
		count_dict = {
			-1: 0,
			1: 0
		}
		for y in Y:
			count_dict[y] = count_dict[y] + 1
		self.count_dict[-1] += count_dict[-1]
		self.count_dict[1] += count_dict[1]
		self.prior_dict[1] = self.count_dict[1] / float(self.total_num)
		self.prior_dict[-1] = self.count_dict[-1] / float(self.total_num)	

	def conditional_probaility(self, X, Y):
		lumbda = 1
		mi = 2		# 进行二分离散化后的数据集每一列的可能取值均为2种
		for i in range(X.shape[1]):
			count_con_dict = defaultdict(int)		# 记录指定条件下特征出现频率
			v_i = X[:,i]
			for x, y in zip(v_i, Y):
				count_con_dict[(x, y)] += 1
				self.count_con_dict[(x,y)] = count_con_dict[(x,y)]
			for key, value in self.count_con_dict.items():
				self.condition_dict[(i, key[0], key[1])] = \
					(value + lumbda) / float(self.count_dict[key[1]] + lumbda * mi)

	# 根据先前计算的先验概率和条件概率，预测该组输入的结果
	def predict(self, x):
		prop_t = self.prior_dict[1]
		prop_f = self.prior_dict[-1]
		for j in range(len(x)):
			prop_t *= self.condition_dict[(j, x[j], 1)]
			prop_f *= self.condition_dict[(j, x[j], -1)]
		# 将两概率之和变为1
		prop_t_1 = prop_t * 1 / float(prop_t + prop_f)
		prop_f_1 = prop_f * 1 / float(prop_t + prop_f)
		print("{}\t\t{}\t{}".format(x, prop_t_1, prop_f_1))
		if prop_t > prop_f:
			return 1
		return -1
		


	def train(self, trainset):
		X = trainset[:,[0, 1, 2]]
		Y = trainset[:,3]
		self.prior_probability(Y)	# 计算先验概率
		self.conditional_probaility(X, Y) # 计算条件概率
		

	def test(self, testset):
		X = testset[:,[0, 1, 2]]
		Y = testset[:, 3]
		true_count = 0
		total_count = 0
		for i in range(len(Y)):
			x = X[i]
			y = Y[i]
			predict = self.predict(x)
			if predict == y:
				true_count += 1
			total_count += 1
		return true_count / float(total_count)



# 以信息增益为标准，计算第一列的阈值
# 2, 3, 4列本就是二分数据，因此不需要求阈值
def get_threshold(dataset):
	col1 = dataset[...,0]
	Y = dataset[:,3]
	# 第一列实际上是有四种可能的离散值
	# 因此我们实际上需要考虑的阈值有3种
	candidate_threshold = [-1.86, -0.922, 0.0215]
	winner = 0
	min_entropy = 0.0
	for i in range(len(candidate_threshold)):
		threshold = candidate_threshold[i]
		# 根据阈值将列表分为两份
		# 计算两侧的信息熵
		# 信息增益以信息熵的降低为衡量标准
		# 因此，我们认为两侧信息熵之和最低的分割点信息增益最大
		# 取其为阈值
		count_left_true = 0
		count_left = 0
		count_right_true = 0
		count_right = 0
		for x, y in zip(col1, Y):
			if x < threshold:
				count_left += 1
				if y == 1:
					count_left_true += 1
			else:
				count_right += 1
				if y == 1:
					count_right_true += 1
		pr_true_left = count_left_true / float(count_left)
		pr_true_right = count_right_true / float(count_right)
		entropy = entropy(pr_true_left) + entropy(pr_true_right)	# 两侧的信息熵之和
		if i == 0:
			min_entropy = entropy
		else:
			if entropy < min_entropy:
				winner = i
				min_entropy = entropy
	return candidate_threshold[winner]

# 计算信息熵
def entropy(pr_true):
	pr_false = 1 - pr_true
	return -1.0 * pr_true * log2(pr_true) - pr_false * log2(pr_false)

# 将数据进行离散化
def discretize(dataset, threshold):
	labels = [-1, 1]
	bins = [
		[-1.87, threshold, 0.966],
		[-0.228, 0, 4.39],
		[-1.92, 0, 0.522],
		[-1.0, 0, 1.1]
	]
	for i in range(4):
		col = pd.cut(dataset[...,i], bins=bins[i], labels=labels, \
			include_lowest= True)
		dataset[...,i] = col
	return dataset	
	
# 划分数据集
def split_dataset(dataset):
	train, test = train_test_split(dataset, test_size= 0.3)
	return train, test

def main():
	# 读取文件
	filepath = 'D:\\learnCoding\\Python\\titanic.dat'
	data = pd.read_table(filepath, sep= ',', \
		skiprows= [0, 1, 2, 3, 4, 5, 6, 7])
	dataset = np.array(data)
	# print(dataset)
	threshold = get_threshold(dataset)
	dataset = discretize(dataset, threshold)
	# print(dataset)
	train_set, test_set = split_dataset(dataset)
	nbc = NaiveBayes()
	nbc.train(train_set)
	accuracy = nbc.test(test_set)
	print('准确率：', accuracy)

	

if __name__ == "__main__":
	main()