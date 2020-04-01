#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif // !WIN32_LEAN_AND_MEAN

#include <Windows.h>
#include <WinSock2.h>
#include <WS2tcpip.h>
#include <iphlpapi.h>
/** When the Iphlpapi.h header file is required, 
*   the #include line for the Winsock2.h header this file should be placed 
*   before the #include line for the Iphlpapi.h header file.
*/
#include <stdio.h>

#define DEFAULT_PORT "27015"
#define DEFAULT_BUFLEN 512

#pragma comment(lib, "Ws2_32.lib")

int main(int argc, char* argv[]) {
	WSADATA wsaData;
	int iResult;
	//Initialize Winsock
	iResult = WSAStartup(MAKEWORD(2, 2), &wsaData);
	if (iResult != 0) {
		printf("WSAStartup failed: %d\n", iResult);
		return 1;
	}
	//Declare an addrinfo object
	struct addrinfo *result = NULL,
		*ptr = NULL,
		hints;								//定义两个指针，一个变量
	ZeroMemory(&hints, sizeof(hints));		//用0来填充一块内存
	hints.ai_family = AF_UNSPEC;			//The address family is unspecified.
	hints.ai_socktype = SOCK_STREAM;		//Use TCP
	hints.ai_protocol = IPPROTO_TCP;

	//Call the getaddrinfo function
	iResult = getaddrinfo(argv[1], DEFAULT_PORT, &hints, &result);
	if (iResult != 0) {
		printf("getaddrinfo failed: %d\n", iResult);
		return 1;
	}

	//Create a SOCKET object
	SOCKET ConnectSocket = INVALID_SOCKET;

	//Call the socket function
	ptr = result;
	ConnectSocket = socket(ptr->ai_family, ptr->ai_socktype, ptr->ai_protocol);

	//Check for errors
	if (ConnectSocket == INVALID_SOCKET) {
		printf("Error at socket(): %ld\n", WSAGetLastError());
		freeaddrinfo(result);
		WSACleanup();
		return 1;
	}

	//Connect to server
	iResult = connect(ConnectSocket, ptr->ai_addr, (int)ptr->ai_addrlen);
	if (iResult == SOCKET_ERROR) {
		closesocket(ConnectSocket);
		ConnectSocket = INVALID_SOCKET;
	}
	freeaddrinfo(result);
	if (iResult == INVALID_SOCKET) {
		printf("Unable to connect to server!\n");
		WSACleanup();
		return 1;
	}

	//Send an initial buffer
	const char *sendbuf = "this is a test";
	iResult = send(ConnectSocket, sendbuf, (int)strlen(sendbuf), 0);
	if (iResult == SOCKET_ERROR) {
		printf("send failed: %d\n", WSAGetLastError());
		closesocket(ConnectSocket);
		WSACleanup();
		return 1;
	}
	printf("Bytes Sent: %ld\n", iResult);

	//shutdown the connection for sending since no more data will be sent
	iResult = shutdown(ConnectSocket, SD_SEND);
	if (iResult == SOCKET_ERROR) {
		printf("shutdown failed: %d\n", WSAGetLastError());
		closesocket(ConnectSocket);
		WSACleanup();
		return 1;
	}

	//Receive data until the server closes the connection
	int recvbuflen = DEFAULT_BUFLEN;
	char recvbuf[DEFAULT_BUFLEN];
	do {
		iResult = recv(ConnectSocket, recvbuf, recvbuflen, 0);
		if (iResult > 0) {
			printf("Bytes received: %d\n", iResult);
		}
		else if (iResult == 0) {
			printf("Connection closed\n");
		}
		else {
			printf("recv failed: %d\n", WSAGetLastError());
		}
	} while (iResult > 0);

	//clean up
	closesocket(ConnectSocket);
	WSACleanup();

	return 0;
}

// 以上内容主要来自Microsoft官方文档：
// https://docs.microsoft.com/en-us/windows/win32/winsock/getting-started-with-winsock
// 大致总结步骤如下：
// 初始化WinSock => 定义addrinfo对象 => 调用getaddrinfo函数
// => 创建SOCKET对象 => 调用socket函数 => 检查是否出现错误
// => 连接至服务器 => 释放addrinfo
// => 发送数据 => 关闭发送连接
// => 接收数据 => 关闭连接并清除