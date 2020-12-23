# Python 实现基于多项式朴素贝叶斯的文本主题分类

import os
import random
import numpy as np
import jieba
import sys

from math import log
from sklearn.model_selection import KFold
from collections import defaultdict
from prettytable import PrettyTable

# 使用到的第三方库：
# 结巴分词（进行中文分词）：https://github.com/fxsjy/jieba
# scikit-learn（用于10折交叉验证）: https://github.com/scikit-learn/scikit-learn

root_path = 'D:\\THUCnews\\THUCNews\\'
tag_array = ['体育', '娱乐', '财经', '时政', '游戏'] # 只选取部分话题进行训练和测试

class MultiNaiveBayes:
	def __init__(self):
		self.prior_dict = {  # 存储先验概率
			'体育': 1.0,
			'娱乐': 1.0,
			'财经': 1.0,
			'时政': 1.0,
			'游戏': 1.0
		}
		self.count_dict = defaultdict(int)
		self.con_prob = defaultdict(lambda: 1.1)	# 存储条件概率
		self.V = set()	# 记录训练样本的单词表
		self.test_count = 0		# 总共测试次数
		self.right_count = 0	# 结果正确次数

	def train(self, train_X, train_Y):
		self.prior_probability(train_X, train_Y)
		self.conditional_probability(train_X, train_Y)


	def test(self, test_X, test_Y):
		tag_dict = {
			'体育': 0,
			'娱乐': 1,
			'财经': 2,
			'时政': 3,
			'游戏': 4
		}
		confuse_matrix = np.zeros((5, 5))	# 创建一个5*5的混淆矩阵
		# 5*4的矩阵，行表示五个分类，四列分别表示TP FN FP TN
		count_matrix = np.zeros((5, 4))	
		for i in range(len(test_X)):
			x = test_X[i]
			y = tag_dict[test_Y[i]]
			predict = tag_dict[self.predict(x)]
			self.test_count += 1
			confuse_matrix[y][predict] += 1	# 行表示实际，列表示预测结果
			if y == predict:				# 若预测正确
				count_matrix[y][0] += 1		# y类型计入1次TP
				self.right_count += 1
				for i in range(4):
					if i != y:				# 其他类型计入一次TN
						count_matrix[i][3] += 1
			else:							# 若预测不准确
				count_matrix[y][1] += 1		# Y的FN + 1
				count_matrix[predict][2] += 1	# predict的FP + 1
				for i in range(4):
					if i != y and i != predict:
						count_matrix[i][3] += 1	# 其他类型记一次TN
		print(confuse_matrix)
		return count_matrix		# 返回计数，用于计算macro和micro平均值

		

	def prior_probability(self, X, Y):		# 计算先验概率
		# 网络上很多资料中说要用各个类型文本的词数来计算先验概率
		# 但教材上是直接用文本的篇数
		# 这里按照教材来计算先验概率
		prior_count = {		# 记录每种类型的文本篇数
			'体育': 0,
			'娱乐': 0,
			'财经': 0,
			'时政': 0,
			'游戏': 0
		}
		total_count = 0		# 记录总的文本篇数
		for i in range(len(X)):
			x = X[i]
			y = Y[i]
			prior_count[y] += 1
			total_count += 1
			# 在计算先验概率的同时
			# 遍历出词表V
			seg_list = jieba.lcut(x, cut_all= False)
			seg_list = cut_word(seg_list)
			for seg in seg_list:
				self.V.add(seg)
		for tag in tag_array:	# 计算先验概率
			self.prior_dict[tag] = prior_count[tag] / float(total_count)	# 该标签下词数/总词数

	def conditional_probability(self, X, Y):	# 计算条件概率
		count_con_dict = defaultdict(int)	# 记录各个词在不同分类下的出现次数
		count_dict = defaultdict(int)		# 记录各个类型文档的总词数
		lumbda = 1
		v = len(self.V)
		for i in range(len(X)):
			x = X[i]
			y = Y[i]
			seg_list = jieba.lcut(x, cut_all= False)		# 利用结巴分词精确模式
			seg_list = cut_word(seg_list)
			for seg in seg_list:
				count_con_dict[(seg, y)] += 1
				count_dict[y] += 1
		self.count_dict = count_dict
		for key, value in count_con_dict.items():	# key[0]: 词汇 key[1]: 类型 value: 次数
			self.con_prob[(key[0], key[1])] = \
				(value + lumbda) / float(count_dict[key[1]] + v * lumbda)		# 拉普拉斯平滑
		
	
	def predict(self, x):		# 利用计算出的先验概率和条件概率进行判断
		max_prob = -1000000.0
		max_tag = '体育'
		seg_list = jieba.lcut(x, cut_all= False)
		seg_list = cut_word(seg_list)
		prop = {
			'体育': log(self.prior_dict['体育']),
			'娱乐': log(self.prior_dict['娱乐']), 
			'财经': log(self.prior_dict['财经']),
			'时政': log(self.prior_dict['时政']),
			'游戏': log(self.prior_dict['游戏'])
		}
		for seg in seg_list:
			for tag in tag_array:
				prob = self.con_prob[(seg, tag)]
				if prob == 1.1:		# 若字典中未出现该词
					# 根据教材第三章(31)式计算概率，使其不为0
					prob = 1 / float(len(self.V) + self.count_dict[tag])
				prop[tag] += log(prob)
		for tag in tag_array:
			if max_prob < prop[tag]:
				max_prob = prop[tag]
				max_tag = tag
		return max_tag

	def getAccuracy(self):
		return self.right_count / float(self.test_count)

def read_data():
	dataset_X = []
	dataset_Y = []
	pick_rate = 0.01  # 按原文件数量的1%随机选择文件
	for tag in tag_array:
		dirpath = root_path + tag
		files = os.listdir(dirpath)
		# pick_num = int(len(files) * pick_rate)
		pick_num = 100	# 每类选100个
		files = random.sample(files, pick_num)
		for file in files:
			filepath = dirpath + '\\' + file
			with open(filepath, 'r', encoding= 'utf-8') as f:
				content = f.read()
				dataset_X.append(content)
				dataset_Y.append(tag)
	return dataset_X, dataset_Y

def cut_word(seg_list):		# 删除停用词
	filepath = 'D:\\learnCoding\\Python\\Data Mining\\stop_words.txt'
	ctwlist = [line.strip() for line in \
		open(filepath, encoding='utf-8').readlines()]
	for seg in seg_list:
		for item in ctwlist:
			if seg == item:
				seg_list.remove(seg)
	return seg_list

def createTable(count_matrix, round_matrix):
	table = PrettyTable(['类型', 'Macro-P', 'Micro-P', 'Macro-R', 'Micro-R', \
		'Macro-F1', 'Micro-F1'])
	for i in range(len(tag_array)):
		tag = tag_array[i]
		macro_P = round_matrix[i][0]	# macro:直接利用每轮的P R求平均
		macro_R = round_matrix[i][1]
		macro_F1 = 2 * macro_P * macro_R / float(macro_P + macro_R)
		TP = count_matrix[i][0]
		FN = count_matrix[i][1]
		FP = count_matrix[i][2]
		micro_P = TP / float(TP + FP)
		micro_R = TP / float(TP + FN)
		micro_F1 = 2 * micro_P * micro_R / float(micro_P + micro_R)
		table.add_row([tag, macro_P, micro_P, macro_R, micro_R, macro_F1, micro_F1])
	return table

def measure(count_matrix):		# 计算每轮测试的查准率和查全率
	pr_matrix = np.zeros((5, 2))
	for i in range(len(count_matrix)):
		TP = count_matrix[i][0]
		FN = count_matrix[i][1]
		FP = count_matrix[i][2]
		P = TP / float(TP + FP)
		R = TP / float(TP + FN)
		pr_matrix[i][0] = P
		pr_matrix[i][1] = R
	return pr_matrix

		

def main():
	dataset_X, dataset_Y = read_data()
	dataset_X = np.array(dataset_X)
	dataset_Y = np.array(dataset_Y)
	mnb = MultiNaiveBayes()
	kf = KFold(n_splits= 10, shuffle= True)
	round_time = 0
	round_matrix = np.zeros((5, 2))	# 记录每轮每个类的查准率、查全率和F1
	total_count = np.zeros((5, 4))	# 记录每个类总的TP FP FN TN，用于计算微平均
	for train_index, test_index in kf.split(dataset_X):
		train_X, test_X = dataset_X[train_index], dataset_X[test_index]
		train_Y, test_Y = dataset_Y[train_index], dataset_Y[test_index]
		mnb.train(train_X, train_Y)
		count_matrix = mnb.test(test_X, test_Y)
		pr_matrix = measure(count_matrix)
		total_count += count_matrix
		round_matrix += pr_matrix
		round_time += 1
	accuracy = mnb.getAccuracy()
	round_matrix /= 10		# 得到micro-P micro-R和micro-F1
	print("Accuracy:{}".format(accuracy))
	table = createTable(total_count, round_matrix)
	print(table)

		

if __name__ == "__main__":
	main()
