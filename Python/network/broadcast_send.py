import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

PORT = 16380
network = '255.255.255.255'
message = 'Hello Everyone!'

s.sendto(message.encode('utf-8'), (network, PORT))