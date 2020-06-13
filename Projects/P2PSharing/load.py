#	load.py

def getIPList():
	ipList = []
	try:
		for line in open(".iplist"):
			ipList.append(line)
	except:
		return []
	return ipList