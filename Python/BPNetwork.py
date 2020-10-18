import numpy as np

class BPNetwork:
	def __init__(self):
		self.input_number = 0			# 输入层神经元数量
		self.hidden_number = 0			# 隐层神经元数量
		self.output_number = 0			# 输出层神经元数量
		self.input_vector = []			# 输入层输出值
		self.hidden_vector = []			# 隐层输出值
		self.output_vector = []			# 输出层输出值
		self.hidden_threshold = []		# 隐层阈值θ
		self.output_threshold = []		# 输出层阈值γ
		self.ih_weight = []				# 输入层第i个节点到隐层第h个节点的权重vih
		self.ho_weight = []				# 隐层第h个节点到输出层第j个节点的权重ωhj

	def init_network(self):
		self.input_number = 3
		self.hidden_number = 4
		self.output_number = 3
		self.hidden_threshold = np.random.randint(-5, 5, (1, self.input_number)).astype(np.float64)
		

		