#include "huffman.h"
#include "encode.h"

bool encode() {
	char filename[30];
	char targetName[30];
	cout << "请输入待压缩的文件名：";
	cin >> filename;
	cout << "请输入压缩文件名：";
	cin >> targetName;
	int* stat = statistics(filename);				//统计字符出现频率
	HuffForest* forest = initForest(stat);			//初始化Huffman森林
	HuffTree* tree = buildTree(forest);				//建立Huffman树
	HuffTable table;
	table = buildTable(table, tree->root(), "");	//建立编码表
	if (!printStat(stat, targetName)) {
		return false;
	}
	fileEncoding(filename, table, targetName);
	cout << "压缩成功" << endl;
	return true;
}

bool printStat(int* stat, char* targetFile) {
	ofstream out;
	out.open(targetFile,ios::out);
	for (int i = 0; i < N_CHAR ; i++) {
		if (stat[i] > 0) {
			out << (char)i;
			out << stat[i];
		}
	}
	out << '^';				//标记频率表部分结束
	out.close();
	return true;
}

void fileEncoding(char* textFile, HuffTable table, char* targetFile) {
	ifstream infile;				//读入文件流
	infile.open(textFile, ios::in);
	string data;
	string output;
	char ch;
	while (infile.get(ch)) {		//此处不使用 infile >> data 的原因是这种方法无法读取空格
		data += ch;
	}
	for (size_t len = data.length(), i = 0; i < len; i++) {
		output += table[data[i]];
	}
	fileWriting(targetFile, output);
}

void fileWriting(char* targetFile, string output) {
	ofstream outfile;
	outfile.open(targetFile, ios::app);
	const int len = output.length();
	outfile << len;
	char data = 0;						//将output每8位转化为char型
	unsigned char and_bit[] = { 0x7F, 0xBF, 0xDF, 0xEF, 0xF7, 0xFB, 0xFD, 0xFE };
										//分别对应01111111 -> 11111110
	unsigned char or_bit[] = { 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01 };
										//分别对应10000000 -> 00000001
	int i = 0;
	for (i = 0; i < len; i++) {
		if (output[i] == '0') {
			data &= and_bit[i % 8];		//将char的第 i%8 位设为0
		}
		else {
			data |= or_bit[i % 8];		//将char的第 i%8 位设为1
		}
		if (i % 8 == 7) {
			outfile << data;
			data = 0;
		}
	}
	if (i - 1 % 8 != 7) {
		outfile << data;
	}
	outfile.close();
}