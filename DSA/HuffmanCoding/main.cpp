#include "huffman.h"
#include "encode.h"
#include "decode.h"


int main() {
	int mode = 0;			//需要进行的操作模式（1为压缩，2为解压）
	cout << "请输入要进行的操作：" << endl;
	cout << "（1）压缩" << endl;
	cout << "（2）解压" << endl;
	scanf("%d", &mode);
	switch (mode) {
	case 2:decode(); break;
	case 1:encode(); break;
	default:printError(1);
	}
	system("pause");
	return 0;
}