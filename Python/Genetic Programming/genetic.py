# genetic.py
'''目标函数：y=x^2-3*x+6 转为解析树后为4层'''

import operator

from basicDS import Stack
from basicDS import BinTree
from init import Individual


#安全版除法，防止程序崩溃（借鉴gplearn库中的除法）
def Div(a, b):
    if abs(b) < 0.001:
        return 1
    else:
        return a / b


#遗传过程中的操作
class Genetic:
    #育种选择函数
    def select(self):
        return

    #交叉操作函数
    def mate(self):
        return

    #突变操作函数：
    def mutate(self):
        return

    #评价函数（对种群中的每个个体进行评价）
    def evaluate(self,population):
        return


#计算解析树所代表的式子的值
def calcu(parseTree):
    opers = {'+': operator.add, '-': operator.sub, '*': operator.mul, '/': Div}
    lc = parseTree.getLeftChild()
    rc = parseTree.getRightChild()
    if lc and rc:
        fn = opers[parseTree.getRootVal()]
        return fn(calcu(lc), calcu(rc))
    else:
        return parseTree.getRootVal()
