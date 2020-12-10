# Python 实现支持向量机

import numpy as np
import pandas as pd

from sklearn.model_selection import KFold

class SVM:
	
	def __init__(self) -> None:
		pass

def read_data():
	filepath = 'D:\\learnCoding\\Python\\Data Mining\\heart_failure_clinical_records_dataset.csv'
	df = pd.read_csv(filepath)
	dataset = np.array(df)
	return dataset

def main():
	read_data()

if __name__ == "__main__":
	main()