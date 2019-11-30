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
	for (int i = 0x20; i < N_CHAR + 0x20; i++) {
		if (stat[i - 0x20] > 0) {
			out << (char)i;
			out << stat[i - 0x20];
		}
	}
	out << endl;				//标记频率表部分结束
	out.close();
	return true;
}

void fileEncoding(char* textFile, HuffTable table, char* targetFile) {
	ifstream infile;				//读入文件流
	ofstream outfile;				//写入文件流
	infile.open(textFile, ios::in);
	outfile.open(targetFile, ios::app);
	string data;
	string output;
	char ch;
	while (infile.get(ch)) {		//此处不使用 infile >> data 的原因是这种方法无法读取空格
		data += ch;
	}
	for (size_t len = data.length(), i = 0; i < len; i++) {
		output += table[data[i]];
	}
	outfile << output;
}

//HashTable的建立存在问题--两次编码结果不同