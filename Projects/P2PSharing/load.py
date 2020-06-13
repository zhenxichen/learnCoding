#	load.py

def getIPList():
	ipList = []
	for line in open('iplist.txt'):
		ipList.append(line)
	return ipList