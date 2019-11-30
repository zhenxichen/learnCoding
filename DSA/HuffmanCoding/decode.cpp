#include "huffman.h"
#include "decode.h"
#include <fstream>

bool decode() {
	char filename[30];
	char targetName[30];
	cout << "请输入待解压的文件名：";
	cin >> filename;
	cout << "请输入目标文件名：";
	cin >> targetName;
	int* stat = readStat(filename);
	HuffForest* forest = initForest(stat);
	HuffTree* tree = buildTree(forest);
	RTable table;
	int maxLength = 0;
	table = buildTable(table, tree->root(), "", maxLength);
	fileDecoding(filename, targetName, table, maxLength);
	cout << "解压成功，文件名为：" << targetName << endl;
	return true;
}

int* readStat(char* filename) {
	char c;			//用以读取字符
	int sta;			//用以读取频率值
	int* stat = new int[N_CHAR];		//记录频率的数组
	FILE* fp = fopen(filename, "r");
	while (true) {
		fscanf(fp, "%c", &c);
		if (c == '\n') {					
			break;
		}
		fscanf(fp, "%d", &sta);
		if (sta == '\n') {
			break;
		}
		stat[c - 0x20] = sta;
	}
	return stat;
}

void fileDecoding(char* filename, char* targetName, RTable table,int maxLength) {
	ifstream infile;
	ofstream outfile;
	infile.open(filename, ios::in);
	outfile.open(targetName,ios::out);
	string data;
	string code;
	getline(infile, data);			//读取之前的统计信息（再此处无效）
	getline(infile, data);			//读取存储的编码
	for (size_t len = data.length(), i = 0; i < len; i++) {
		for (;;) {
			code += data[i];
			if (table.count(code)) {		//找到编码对应的字符
				char c = table[code];
				outfile << c;
				break;
			}
			i++;
		}
		code.clear();
	}
	infile.close();
	outfile.close();
}
