from clang.cindex import Index
from clang.cindex import TranslationUnit
from clang.cindex import Config
from clang.cindex import Cursor
from clang.cindex import Token
import asciitree

FILE_PATH = 'D:\\scu-dc\\src\\main\\resources\\test.c'
CLANG_LIB = 'C:\\Program Files\\LLVM\\bin\\libclang.dll'


# 函数作为ASCII Tree的参数
def node_chidren(node):
	return (c for c in node.get_children() if c.location.file.name == FILE_PATH)

def print_node(node):
	text = node.spelling or node.displayname
	kind = str(node.kind)[str(node.kind).index('.') + 1:]
	return '{} {}'.format(kind, text)

if __name__ == "__main__":
	Config.set_library_file(CLANG_LIB)
	index = Index.create()
	args = []
	translationUnit = index.parse(FILE_PATH, args=args)	#通过Clang生成Parse Tree
	# print(asciitree.draw_tree(translationUnit.cursor, node_chidren, print_node))
	# 打印token
	for token in translationUnit.get_tokens(extent=translationUnit.cursor.extent):
		print(token.kind, token.spelling)
	
	# 打印诊断信息
	for diag in translationUnit.diagnostics:
		print(diag)
	



