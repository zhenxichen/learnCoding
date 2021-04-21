import os

# 统计单个文件的行数和空行数
def count(filename: str) -> (int, int):
    total_count = 0
    empty_count = 0
    for line in open(filename, 'rb').readlines():
        total_count += 1
        if isEmpty(line):
            empty_count += 1
    return total_count, empty_count

# 判断代码行是否为空
def isEmpty(line: str) -> bool:
    if len(line.strip()) == 0:      # strip()函数的作用是清除前后的空格和换行符
        return True
    return False

# 用于统计一个目录下所有文件的行数和空行数
def countDir(filepath: str) -> (int, int):
    total_count = 0
    empty_count = 0
    for root, dirs, files in os.walk(filepath):
        for file in files:
            total, empty = count(os.path.join(root, file))
            total_count += total
            empty_count += empty
    return total_count, empty_count


if __name__ == '__main__':
    print('请输入待统计的文件或文件夹路径：')
    filename = input()
    if os.path.isdir(filename):
        total, empty = countDir(filename)
        print('本项目的代码总行数为：', total)
        print('本项目的空行数为：', empty)
    elif os.path.isfile(filename):
        total, empty = count(filename)
        print('本文件的代码总行数为：', total)
        print('本文件的空行数为：', empty)
    else: 
        print('输入的文件路径错误')



