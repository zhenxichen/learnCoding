#通过DEAP库实现目标函数为y=x^3-2*x的符号回归

import operator
import math
import random
import numpy as np

from deap import algorithms
from deap import base
from deap import tools
from deap import creator
from deap import gp

#自定义安全版除法
def Div(a,b):
    if(abs(b)<0.001):
        return 1
    return a/b

#初始化Primitive Set
pset = gp.PrimitiveSet("MAIN",1)
pset.addPrimitive(operator.add, 2)
pset.addPrimitive(operator.sub, 2)
pset.addPrimitive(operator.mul, 2)
pset.addPrimitive(Div,2)
pset.addPrimitive(operator.neg, 1)
pset.addPrimitive(math.cos, 1)
pset.addPrimitive(math.sin, 1)
pset.renameArguments(ARG0='x')

#定义问题
creator.create('FitnessMin',base.Fitness,weights=(-1.0,)) #最小值问题
creator.create('Individual',gp.PrimitiveTree,fitness = creator.FitnessMin)

toolbox = base.Toolbox()
toolbox.register("expr",gp.genHalfAndHalf,pset = pset,min_=1,max_=2)
toolbox.register("individual",tools.initIterate,creator.Individual,toolbox.expr) #初始化个体
toolbox.register("population",tools.initRepeat,list,toolbox.individual) #初始化族群
toolbox.register("compile",gp.compile,pset=pset)

#定义评价函数
def eva(individual,points):
    func = toolbox.compile(expr = individual)
    #destine function is: y=x^3-2*x
    se = ((func(x)-x**3+2*x)**2 for x in points)
    return math.fsum(se)/len(points),        #返回平均方差(,表示以元组形式)

toolbox.register("evaluate",eva,points=[x/10. for x in range(-10,10)])   #注册评价函数
toolbox.register("select",tools.selTournament,tournsize=3)               #注册育种选择操作（tournament select）
toolbox.register("mate", gp.cxOnePoint)                                  #注册交叉操作
toolbox.register("expr_mut", gp.genFull, min_=0, max_=2)                 
toolbox.register("mutate", gp.mutUniform, expr=toolbox.expr_mut, pset=pset) #注册突变操作
#限制交叉或突变所得到的个体的高度
toolbox.decorate("mate", gp.staticLimit(key=operator.attrgetter("height"), max_value=15))   
toolbox.decorate("mutate", gp.staticLimit(key=operator.attrgetter("height"), max_value=15))

def main():
    random.seed(666)                        #使操作可重现
    pop = toolbox.population(n=50)
    hof = tools.HallOfFame(3)               #将表现最好的5个个体保存下来
    fit = tools.Statistics(lambda ind: ind.fitness.values)
    mstats = tools.MultiStatistics(fitness=fit)
    mstats.register("Avg",np.mean)
    mstats.register("Min",np.min)

    pop,log = algorithms.eaSimple(pop,toolbox,0.7,0.05,100,stats=mstats,halloffame=hof,verbose=True)
    #上方为DEAP库自带的简单进化算法
    #三个数字参数分别为交叉率、变异率和代数（这些不太懂，随便填了）
    #两个返回值分别为最终的population以及统计数据Logbook
    return 0

main()