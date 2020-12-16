# Python 实现支持向量机

import numpy as np
import pandas as pd

from sklearn.model_selection import KFold

class SVM:
	
	def __init__(self, C, ) -> None:
		self.C = C		# 参数C（见教材公式65）
		self.b = 0		# 偏移量bias
		self.w = []		# 权重向量



	def train(self, X, Y):
		pass

	def kernal_rbf(self):
		pass

	def smo(self, X, Y):
		b = 0
		alphas = np.mat(np.zeros(np.shape(X)[0], 1))
		i = 0
		maxIter = 200	# 最大迭代次数
		
		self.b = b
		return b, alphas


def read_data():
	filepath = 'D:\\learnCoding\\Python\\Data Mining\\heart_failure_clinical_records_dataset.csv'
	df = pd.read_csv(filepath)
	dataset = np.array(df)
	return dataset

def main():
	read_data()

if __name__ == "__main__":
	main()