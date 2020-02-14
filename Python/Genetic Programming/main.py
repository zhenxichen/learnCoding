import init
import random

from genetic import Genetic


def main():
    pop = init.initPop(300)
    itera = 100     #迭代次数
    each = 50       #每轮迭代的遗传计算次数
    tplt = "{:8}\t{:8}\t{:8}"
    gen = Genetic()
    print(tplt.format("Gen", "Avg", "Best"))
    for i in range(1,itera):
        pop = gen.evaluate(pop)
        gen.printGen(pop, i)
        for j in range(1,each):
            rand = random.random()
            if rand > 0.7:              #变异率设为0.3
                ind = gen.select(pop)
                pop.remove(ind)
                ind.mark = 2
                ind.gene = gen.mutate(ind.gene, 4)
                pop.append(ind)
            else:
                pop = gen.mate(pop)
            


main()
