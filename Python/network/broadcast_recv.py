import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)

PORT = 16380

s.bind(('', PORT))

print("receving...")
while True:
    data, address = s.recvfrom(65535)
    print('{} from {}'.format(data.decode('utf-8'), address))