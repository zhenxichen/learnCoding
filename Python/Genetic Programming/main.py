import init
from genetic import Genetic


def main():
    pop = init.initPop(10)
    itera = 100  #迭代100轮
    tplt = "{:8}\t{:8}\t{:8}"
    gen = Genetic()
    print(tplt.format("Gen", "Avg", "Best"))
    for i in range(1):
        pop = gen.evaluate(pop)
        gen.printGen(pop, i)


main()
