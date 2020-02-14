# genetic.py
'''目标函数：y=x^2-3*x+6 转为解析树后为4层'''

import operator
import math
import heapq
import numpy
import random

from basicDS import Stack
from basicDS import BinTree
from init import Individual
from init import primitiveSet
from init import terminalSet


#安全版除法，防止程序崩溃（借鉴gplearn库中的除法）
def Div(a, b):
    if abs(b) < 0.001:
        return 1
    else:
        return a / b


#遗传过程中的操作
class Genetic:
    #选择函数
    def select(self,pop):   #随机选择一个标记为0的个体
        while True:
            rand = random.randint(0,len(pop)-1)
            if pop[rand].mark == 0:
                return pop[rand]

    #交叉操作函数
    def mate(self, pop):
        #实现思路：(2/14)随机选择两个树的子结点，然后进行交换
        #需要考虑的问题：是否需要同位交换？
        ind1, ind2 = selectTwo(pop)
        pop.remove(ind1)
        pop.remove(ind2)
        t1 = ind1.gene
        t2 = ind2.gene
        #*************#
        #两树之间的交叉操作

        #*************#
        ind1.gene = t1
        ind2.gene = t2
        ind1.mark = 2           #用标记2表示已经进行过操作
        ind2.mark = 2
        pop.append(ind1)
        pop.append(ind2)
        return pop

    #突变操作函数：
    def mutate(self, tree, depth):
        #调用方法：ind.gene = genetic.mutate(ind.gene, 4)
        #实现思路：递归，每轮生成随机数来决定访问左子树、右子树或直接操作根结点
        tree = BinTree()
        if depth == 1:
            rand = random.randint(0, 3)
            tree.setRootVal(terminalSet[rand])
            return tree
        else:
            lc = tree.getLeftChild()
            rc = tree.getRightChild()
            rand = random.randint(0, 5)
            if rand < 2 and lc:  #1/3概率
                tree.insertLeft(self.mutate(lc, depth - 1))  #对左子树操作
            elif rand > 3 and rc:  #1/3概率
                tree.insertRight(self.mutate(rc, depth - 1))  #对右子树操作
            else:  #1/3概率
                ind = Individual()
                tree = ind.grow(depth)  #用grow方法重新生成深度为depth的子树
            return tree

    #评价函数（对种群中的每个个体进行评价）
    def evaluate(self, population):
        #population = []
        points = [-2, -1, 0, 1, 2, 3]
        fitness = []  #用于记录每个个体的fitness
        for ind in population:
            se = ((calcu(ind.gene, x) - x**2 + 3 * x - 6)**2 for x in points)
            ind.fitness = math.fsum(se) / len(points)
            fitness.append(ind.fitness)
        stand1 = heapq.nlargest(len(fitness) / 10, fitness)[-1]
        stand2 = heapq.nsmallest(len(fitness) / 10, fitness)[-1]
        for ind in population:
            if ind.fitness < stand2:  #报错：TypeError: '<' not supported between instances of 'float' and 'list'
                ind.mark = 1  #将最优个体标为1，进行copy操作
            elif ind.fitness > stand1:
                ind.mark = -1  #将表现最差的个体标为-1，进行delete操作
            else:
                ind.mark = 0  #其余个体标为0，进行mutate或mate操作
        return population

    def printGen(self, population, gen):
        tplt = "{:8}\t{:8}\t{:8}"
        fitness = []
        for ind in population:
            fitness.append(ind.fitness)
        avg = numpy.mean(fitness)
        best = numpy.min(fitness)
        print(tplt.format(gen, avg, best))


#计算解析树所代表的式子的值
def calcu(parseTree, xValue):
    opers = {'+': operator.add, '-': operator.sub, '*': operator.mul, '/': Div}
    lc = parseTree.getLeftChild()
    rc = parseTree.getRightChild()
    if lc and rc:
        fn = opers[parseTree.getRootVal()]
        return fn(calcu(lc, xValue), calcu(rc, xValue))
    else:
        op = parseTree.getRootVal()
        if op == 'x':
            op = xValue
        return op

#选择参加mate操作的两个个体
def selectTwo(pop):
        while True:
            rand1 = random.randint(0, len(pop) - 1)
            rand2 = random.randint(0, len(pop) - 1)
            if rand1 == rand2:  #重新随机
                continue
            if pop[rand1].mark == 0 and pop[rand2].mark == 0:
                return pop[rand1], pop[rand2]


#Bugs:
#(1) line 75 Error: rootVal为int型
# 解决方法：full方法 terminalSet -> primitiveSet
#(2) line 76 Error: unsupported operand type(s) for *: 'BinTree' and 'BinTree'
# 问题在于：插入二叉树时参数为值，而非子树
# 解决方法：修改二叉树插入操作的参数
#(3) line 48 TypeError: '<' not supported between instances of 'float' and 'list'
# 问题在于：将nlargest方法的返回值误认为是一个实数，但实际上是一个列表
# 修改line45 为 stand1 = heapq.nlargest(len(fitness)/10,fitness)[-1]