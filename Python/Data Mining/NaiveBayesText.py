# Python 实现基于多项式朴素贝叶斯的文本主题分类

import os
import random

root_path = 'D:\\THUCnews\\THUCNews\\THUCNews\\'
tag_array = ['体育', '娱乐', '财经', '时政', '游戏'] # 只选取部分话题进行训练和测试

class MultiNaiveBayes:
	def __init__(self):
		self.summarizes = { } # 提取的特征值
		self.prior_dict = {  # 存储先验概率
		 '体育': 0.0,
		 '娱乐': 0.0,
		 '财经': 0.0,
		 '时政': 0.0,
		 '游戏': 0.0
		}

	def train(self, train_X, train_Y):
		pass

def read_data():
	dataset_X = []
	dataset_Y = []
	pick_rate = 0.05  # 按原文件数量的5%随机选择文件
	for tag in tag_array:
		dirpath = root_path + tag
		files = os.listdir(dirpath)
		pick_num = int(len(files) * pick_rate)
		files = random.sample(files, pick_num)
		for file in files:
			filepath = dirpath + '\\' + file
			with open(filepath, 'r', encoding= 'utf-8') as f:
				content = f.read()
				dataset_X.append(content)
				dataset_Y.append(tag)
	return dataset_X, dataset_Y



def main():
	pass

if __name__ == "__main__":
	main()
