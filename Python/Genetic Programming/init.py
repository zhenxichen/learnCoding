import random

from basicDS import BinTree

# Primitive Set = [+,-,*,/]   Terminal Set = [0,1,2,3,4,5,6,7,8,9,x]

primitiveSet = ['+', '-', '*', '/']
terminalSet = [3, 6, 9, 'x']  #删减Terminal Set中的部分数字，稍微提高准确性


class Individual:
    def __init__(self):
        self.gene = BinTree(' ')  #用二叉树保存公式（中序遍历解析）
        self.mark = 0
        self.fitness = 0.0

    #Full方法
    def full(self, depth):  #运用full方法填充二叉树
        tree = BinTree()
        if depth == 1:
            tree.setRootVal(terminalSet[random.randint(
                0, 3)])  #最后一层添加Terminal Set中的元素
            return tree
        else:
            tree.setRootVal(primitiveSet[random.randint(0, 3)])  #用递归实现full方法
            tree.insertLeft(self.full(depth - 1))
            tree.insertRight(self.full(depth - 1))
        return tree

    #Grow方法
    def grow(self, depth):
        tree = BinTree()
        if depth == 1:
            tree.setRootVal(terminalSet[random.randint(0, 3)])
            return tree
        else:
            rand = random.randint(-4, 3)
            if rand >= 0:
                tree.setRootVal(primitiveSet[rand])
                tree.insertLeft(self.grow(depth - 1))
                tree.insertRight(self.grow(depth - 1))
            else:
                rand = abs(rand) - 1
                tree.setRootVal(terminalSet[rand])
            return tree


#初始化种群的函数
def initPop(size):
    population = []  #用list存储Individuals
    for i in range(size):
        ind = Individual()
        if random.random() > 0.5:  #0.5的几率采用full方法
            ind.gene = ind.full(4)
        else:
            ind.gene = ind.grow(4)
        population.append(ind)
    return population


#目前需要解决的难点：
# 1. 如何将公式转化为编码或树           （solved）
# 2. 如何实现交换以及变异操作（对二叉树进行操作）
# 3. 如何将特定数值代入x中以进行计算拟合程度    (solved)