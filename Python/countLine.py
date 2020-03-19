#打算写一个统计代码行数的小工具
import os

def count(fileName):
    count = 0
    for line in open(fileName, 'rb').readlines():
        count += 1
    return count

if __name__ == '__main__':
    total = 0
    for root, dirs, files in os.walk('D:\learnCoding'):
        for file in files:
            total += count(os.path.join(root, file))
    print(total)
