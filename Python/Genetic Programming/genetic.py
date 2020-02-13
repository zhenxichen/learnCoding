# genetic.py
'''目标函数：y=x^2-3*x+6 转为解析树后为4层'''

import operator
import math
import heapq
import numpy

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
        #population = []
        points = [-2,-1,0,1,2,3]
        fitness = []            #用于记录每个个体的fitness
        for ind in population:
            se = ((calcu(ind.gene,x)-x**2+3*x-6)**2 for x in points)
            ind.fitness = math.fsum(se)/len(points)
            fitness.append(ind.fitness)
        stand1 = heapq.nlargest(len(fitness)/10,fitness)[-1]
        stand2 = heapq.nsmallest(len(fitness)/10,fitness)[-1]
        for ind in population:
            if ind.fitness < stand2:                #报错：TypeError: '<' not supported between instances of 'float' and 'list'
                ind.mark = 1       #将最优个体标为1，进行copy操作
            elif ind.fitness > stand1:
                ind.mark = -1      #将表现最差的个体标为-1，进行delete操作
            else:
                ind.mark = 0       #其余个体标为0，进行mutate或mate操作
        return population
    
    def printGen(self,population,gen):
        tplt = "{:8}\t{:8}\t{:8}"
        fitness = []
        for ind in population:
            fitness.append(ind.fitness)
        avg = numpy.mean(fitness)
        best = numpy.min(fitness)
        print(tplt.format(gen,avg,best))

        



#计算解析树所代表的式子的值
def calcu(parseTree,xValue):
    opers = {'+': operator.add, '-': operator.sub, '*': operator.mul, '/': Div}
    lc = parseTree.getLeftChild()
    rc = parseTree.getRightChild()
    if lc and rc:
        fn = opers[parseTree.getRootVal()]  
        return fn(calcu(lc,xValue), calcu(rc,xValue))  
    else:
        op = parseTree.getRootVal()
        if op == 'x':
            op = xValue
        return op



#Bugs:
#(1) line 75 Error: rootVal为int型
# 解决方法：full方法 terminalSet -> primitiveSet
#(2) line 76 Error: unsupported operand type(s) for *: 'BinTree' and 'BinTree'
# 问题在于：插入二叉树时参数为值，而非子树 
# 解决方法：修改二叉树插入操作的参数
#(3) line 48 TypeError: '<' not supported between instances of 'float' and 'list'
# 问题在于：将nlargest方法的返回值误认为是一个实数，但实际上是一个列表
# 修改line45 为 stand1 = heapq.nlargest(len(fitness)/10,fitness)[-1]