import init
import random
import numpy
import matplotlib

from genetic import Genetic
from matplotlib import pyplot

random.seed(666)

def main():
    pop = init.initPop(300)
    itera = 100     #迭代次数
    each = 30       #每轮迭代的遗传计算次数
    tplt = "{:8}\t{:8}\t{:8}"
    gen = Genetic()
    bests = []       #用以存储每代最优值
    print(tplt.format("Gen", "Avg", "Best"))
    for i in range(1,itera):
        pop = gen.evaluate(pop)
        bests.append(gen.printGen(pop, i))
        for j in range(1,each):
            rand = random.random()
            if rand < 0.7:              #变异率设为0.3
                ind = gen.select(pop)
                pop.remove(ind)
                ind.mark = 2
                ind.gene = gen.mutate(ind.gene, 4)
                pop.append(ind)
            else:
                pop = gen.mate(pop)
    res = numpy.array(bests)
    x = numpy.arange(1,99)
    y = res[x]
    pyplot.title('GP result')
    pyplot.xlabel('Gen')
    pyplot.ylabel('Best')
    pyplot.plot(x,y)
    pyplot.show()    
            
main()
