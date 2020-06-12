import socket

from query import query
from query import send_quit
from listen import listen_ack

class Client:
	ack_port = 0

	def __init__(self):
		self.ack_port = 16382

	def run(self):
		print('--欢迎使用P2P文件分享系统--')
		print('输入get 文件名来向网络中的设备搜索文件')
		print('或输入quit退出')
		while True:
			command = input('>>')
			if command == 'quit':
				#退出程序
				send_quit()
				return
			else:
				commands = command.split(' ')
				if commands[0] == 'get':
					if len(commands) == 1:
						print('请在get之后输入要搜索的文件名')
						continue
					filename = commands[1]
					query(filename)
					filepath, address = listen_ack(self.ack_port)
					

				else:
					print('指令{}无效'.format(commands[0]))
					continue

