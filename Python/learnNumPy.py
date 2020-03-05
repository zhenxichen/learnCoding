#稍微学习一下NumPy的用法
#之前虽然已经稍微有用到了，但是实际上很多功能都还不知道

import numpy as np 

#ndarray数组的操作
arr = np.array([9,8,7,6,5])
#起始编号：终止编号（不含）：步长
print(arr[1:4:2])
arr = np.arange(24).reshape((2,3,4))  #生成[0,23]范围的数组，然后reshape为三维数组
#print(arr)
#多维数组切片
print(arr[:,1:3,::2])                 #不考虑第一维度，第二维度范围为[1,2]，第三维度步长为2
arr = arr/arr.mean()                  #ndarray与标量进行运算，相当于对数组中每个数都进行运算
#print(arr)
print(np.var(arr))                    #求方差
arr = np.random.randint(0,20,(5))
print(arr)
print(np.gradient(arr))               #求梯度
