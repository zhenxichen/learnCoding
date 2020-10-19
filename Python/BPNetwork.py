import numpy as np
from sklearn.metrics import mean_squared_error
import matplotlib.pyplot as plt

class BPNetwork:
    def __init__(self):
        self.input_number = 0   # 输入层神经元数量
        self.hidden_number = 0   # 隐层神经元数量
        self.output_number = 0   # 输出层神经元数量
        self.input_vector = []   # 输入层输出值
        self.hidden_vector = []   # 隐层输出值
        self.output_vector = []   # 输出层输出值
        self.hidden_threshold = []  # 隐层阈值θ
        self.output_threshold = []  # 输出层阈值γ
        self.ih_weight = []    # 输入层第i个节点到隐层第h个节点的权重vih
        self.ho_weight = []    # 隐层第h个节点到输出层第j个节点的权重ωhj
        self.activation = sigmoid  # 激活函数

    def init_network(self, i: int, h: int, o: int):
        # 定义各层神经元数量
        self.input_number = i
        self.hidden_number = h
        self.output_number = o
        # 初始化存储各层数值的vector
        self.input_vector = np.zeros(self.input_number)
        self.hidden_vector = np.zeros(self.hidden_number)
        self.output_vector = np.zeros(self.output_number)
        # 在(0, 1)范围内随机初始化网络中所有连接权和阈值
        self.hidden_threshold = np.random.rand(self.hidden_number)
        self.output_threshold = np.random.rand(self.output_number)
        self.ih_weight = np.random.rand(self.input_number, self.hidden_number)
        self.ho_weight = np.random.rand(self.hidden_number, self.output_number)

    def training(self, x, y, learning_rate= 0.1):
        # 正向传播
        self.input_vector = np.array(x)
        hidden_input = np.dot(self.input_vector.T, self.ih_weight)
        for h in range(self.hidden_number):
            self.hidden_vector[h] = self.activation(hidden_input[h] - self.hidden_threshold[h])
        output_input = np.dot(self.hidden_vector.T, self.ho_weight)
        # print(self.ho_weight)
        # print(output_input)
        for j in range(self.output_number):
            self.output_vector[j] = self.activation(output_input[j] - self.output_threshold[j])
        # 反向传播
        output_gradient = np.zeros(self.output_number)  # 输出层神经元的梯度项
        for j in range(self.output_number):
            yj = self.output_vector[j]
            output_gradient[j] = yj * (1 - yj) * (yj - y[j])
        hidden_gradient = np.zeros(self.hidden_number) # 隐层神经元的梯度项
        for h in range(self.hidden_number):
            bh = self.hidden_vector[h]
            hidden_gradient[h] = bh * (1 - bh) * (np.dot(
                self.ho_weight[h].T, output_gradient))
        # 更新连接权和阈值
        for h in range(self.hidden_number):
            for j in range(self.output_number):
                delta_weight_hj = learning_rate * output_gradient[j] * self.hidden_vector[h]
                self.ho_weight[h][j] += delta_weight_hj
        for i in range(self.input_number):
            for h in range(self.hidden_number):
                delta_weight_ih = learning_rate * hidden_gradient[h] * self.input_vector[i]
                self.ih_weight[i][h] += delta_weight_ih
        for h in range(self.hidden_number):
            delta_gamma_h = -1.0 * learning_rate * hidden_gradient[h]
            self.hidden_threshold[h] += delta_gamma_h
        for j in range(self.output_number):
            delta_theta_j = -1.0 * learning_rate * output_gradient[j]
            self.output_threshold[j] += delta_theta_j

    def test(self, x, y):
        self.input_vector = np.array(x)
        hidden_input = np.dot(self.input_vector.T, self.ih_weight)
        for h in range(self.hidden_number):
            self.hidden_vector[h] = self.activation(hidden_input[h] - self.hidden_threshold[h])
        output_input = np.dot(self.hidden_vector.T, self.ho_weight)
        for j in range(self.output_number):
            self.output_vector[j] = self.activation(output_input[j] - self.output_threshold[j])
        err = mean_squared_error(self.output_vector, y)
        return err


def sigmoid(x):
    return 1.0 / (1.0 + np.exp(-x))

def sigmoid_derivative(x):
    return sigmoid(x) *(1 - sigmoid(x))

if __name__ == "__main__":
    network = BPNetwork()
    network.init_network(3, 4, 1)
    # 训练集
    X = [
        [1.0, 1.0, 1.0],
        [1.0, 2.0, 1.0],
        [1.0, 2.0, 2.0],
        [0.9, 1.0, 1.0],
        [0.9, 0.8, 0.7]
    ]
    Y = [
        [1.0],
        [1.3],
        [1.5],
        [0.95],
        [0.83],
    ]
    # 测试集
    x_test = [0.1, 0.2, 0.3]
    y_test = [0.17]
    # 存储均方误差
    errs = []
    for g in range(100):
        i = g % 5
        network.training(X[i], Y[i])
        err = network.test(x_test, y_test)
        print('loop {gen}, 均方误差: {err}'.format(gen= g + 1, err= err))
        errs.append(err)
    x = np.arange(1, 99)
    res = np.array(errs)
    y = res[x]
    plt.xlabel('loop')
    plt.ylabel('err')
    plt.plot(x, y)
    plt.show()
