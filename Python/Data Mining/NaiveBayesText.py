# Python 实现基于多项式朴素贝叶斯的文本主题分类

import os
import random
import numpy as np
import jieba
import sys

from math import log
from sklearn.model_selection import KFold
from collections import defaultdict

# 使用到的第三方库：
# 结巴分词（进行中文分词）：https://github.com/fxsjy/jieba
# scikit-learn（用于10折交叉验证）: https://github.com/scikit-learn/scikit-learn

root_path = 'D:\\THUCnews\\THUCNews\\'
tag_array = ['体育', '娱乐', '财经', '时政', '游戏'] # 只选取部分话题进行训练和测试

class MultiNaiveBayes:
	def __init__(self):
		self.summarizes = { } # 提取的特征值
		self.prior_dict = {  # 存储先验概率
			'体育': 1.0,
			'娱乐': 1.0,
			'财经': 1.0,
			'时政': 1.0,
			'游戏': 1.0
		}
		# 因为需要用log处理，如果默认值用1.0的话会导致未出现过的词反而不减少prob值
		self.con_prob = defaultdict(lambda: 0.0001)	# 存储条件概率(默认值需足够小)
		self.V = set()	# 记录训练样本的单词表

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
		for i in range(len(test_X)):
			x = test_X[i]
			y = tag_dict[test_Y[i]]
			predict = tag_dict[self.predict(x)]
			confuse_matrix[y][predict] += 1	# 行表示实际，列表示预测结果
		print(confuse_matrix)

	def prior_probability(self, X, Y):		# 计算先验概率
		prior_count = {
			'体育': 0,
			'娱乐': 0,
			'财经': 0,
			'时政': 0,
			'游戏': 0
		}
		total_count = 0
		for i in range(len(X)):
			x = X[i]
			y = Y[i]
			seg_list = jieba.lcut(x, cut_all= False)
			seg_list = cut_word(seg_list)
			for seg in seg_list:
				prior_count[y] += 1
				total_count += 1
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
		for key, value in count_con_dict.items():	# key[0]: 词汇 key[1]: 类型 value: 次数
			self.con_prob[(key[0], key[1])] = \
				(value + lumbda) / float(count_dict[key[1]] + v)		# 拉普拉斯平滑
		
	
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
		print(prop)
		for seg in seg_list:
			for tag in tag_array:
				prob = self.con_prob[(seg, tag)]
				prop[tag] += log(prob)
		for tag in tag_array:
			if max_prob < prop[tag]:
				max_prob = prop[tag]
				max_tag = tag
		print(prop)
		return max_tag

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


def main():
	dataset_X, dataset_Y = read_data()
	dataset_X = np.array(dataset_X)
	dataset_Y = np.array(dataset_Y)
	mnb = MultiNaiveBayes()
	kf = KFold(n_splits= 10, shuffle= True)
	round_time = 1
	for train_index, test_index in kf.split(dataset_X):
		train_X, test_X = dataset_X[train_index], dataset_X[test_index]
		train_Y, test_Y = dataset_Y[train_index], dataset_Y[test_index]
		print("round: {}".format(round_time))
		print("------------------------------")
		mnb.train(train_X, train_Y)
		mnb.test(test_X, test_Y)
		round_time += 1
	

if __name__ == "__main__":
	main()
