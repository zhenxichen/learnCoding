from clang.cindex import Index
from clang.cindex import TranslationUnit
from clang.cindex import Config
from clang.cindex import Cursor
from clang.cindex import Token
from clang.cindex import CursorKind

CLANG_LIB = 'C:\\Program Files\\LLVM\\bin\\libclang.dll'	# filepath of 'libclang.dll'
FILE_PATH = 'D:\\astparser\\docs\\test.c'		# 生成AST的源码文件的位置

def traverse(node: Cursor):
	for child in node.get_children():
		traverse(child)

	print(node.kind, node.displayname)
	

def main():
	Config.set_library_file(CLANG_LIB)
	index = Index.create()
	translationUnit = index.parse(FILE_PATH)
	root = translationUnit.cursor
	traverse(root)


if __name__ == "__main__":
	main()