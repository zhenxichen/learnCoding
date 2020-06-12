#	listen.py

import socket

from search import search
from load import getIPList

def listen_query():
	s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)	#建立UDP socket
	s.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
	port = 16380		# UDP监听端口
	ipList = getIPList()			# 读取的本地已知IP列表

	s.bind(('', port))
	while True:
		# 持续监听，直到收到quit消息
		datacode, address = s.recvfrom(65535)
		data = datacode.decode('utf-8')
		dataarr = data.split(' ')
		if dataarr[0] == 'query':
			#continue
			filename = dataarr[1]
			ip = dataarr[2]
			ttl = int(dataarr[3])-1  #设置ttl的目的主要是防止两个互相知道对方存在的节点重复查询
			filepath = search(filename)
			message = 'query ' + filename + ' ' + ip \
				+ ' ' + ttl
			if filepath == None:	#未找到，则转发
				if ttl == 0:
					continue
				else:
					for ipaddress in ipList:
						s.sendto(message.encode('utf-8'), (ipaddress, port))
			else:		#找到，则回传带有文件路径的ACK信息
				ACK_PORT = 16382
				message = 'ack ' + filepath
				s.sendto(message.encode('utf-8'), (ip, ACK_PORT))
		elif dataarr[0] == 'quit':
			break
		else:
			continue

def listen_ack(ack_port) :
	# 因为需求中要求是对第一个发回ACK的设备进行连接
	# 因此，对ACK端口的监听应该是一次性的
	s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
	s.bind(('', ack_port))
	data, address = s.recvfrom(65535)
	message = data.decode('utf-8').split(' ')








