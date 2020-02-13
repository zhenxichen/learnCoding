class Stack:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def top(self):
        return self.items[len(self.items) - 1]

    def size(self):
        return len(self.items)


class BinTree:
    def __init__(self, root=None, depth = 1):
        self.root = root
        self.lChild = None
        self.rChild = None

    def insertLeft(self, newNode):
        if self.lChild == None:
            self.lChild = newNode

    def insertRight(self, newNode):
        if self.rChild == None:
            self.rChild = newNode
        

    def getRightChild(self):
        return self.rChild

    def getLeftChild(self):
        return self.lChild

    def setRootVal(self, obj):
        self.root = obj

    def getRootVal(self):
        return self.root
    



#2020/2/12  目标：完成所需的基本数据结构（解析树和栈） (solved)
#2020/2/12  目标：完成初始化种群相关的函数            (solved)
#2020/2/13  目标：完成评价函数                       (solved)
#2020/2/13  目标：修改grow和full方法的bug            (solved)
#2020/2/13  目前效果：可以输出第一代的数据