import numpy as np

class BPNetwork:
	def __init__(self):
		self.input_number = 0			# 输入层神经元数量
		self.hidden_number = 0		# 隐层神经元数量
		self.output_number = 0		# 输出层神经元数量
		self.input_vector = []		# 输入层输出值
		self.hidden_vector = []		# 隐层输出值
		self.output_vector = []		# 输出层输出值
		