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
	if (!fp) {
		printError(2);
	}
	while (true) {
		fscanf(fp, "%c", &c);
		if (c == EOF) {
			printError(3);
		}
		if (c == '^') {					
			break;
		}
		fscanf(fp, "%d", &sta);
		stat[c] = sta;
	}
	return stat;
}

void fileDecoding(char* filename, char* targetName, RTable table, int maxLength) {
	ofstream outfile;
	outfile.open(targetName, ios::out);
	string data;
	data = fileReading(filename);
	string code;
	for (size_t len = data.length(), i = 0; i < len; i++) {
		for (;;) {
			code += data[i];
			if (table.count(code)) {		//找到编码对应的字符
				char c = table[code];
				outfile << c;
				break;
			}
			if (i >= len) {
				break;
			}
			i++;
		}
		code.clear();
	}
	outfile.close();
}

string fileReading(char* filename) {
	ifstream infile;
	infile.open(filename, ios::in);
	string data;
	char ch = 0;
	int inte = 0;
	int length = 0;
	int count = 0;				//读取的编码数
	unsigned char and_bit[] = { 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01 };
	for (;;) {
		infile.get(ch);
		if (ch == '^') { break; }
		infile >> inte;
	}
	infile >> length;			//总编码长度
	while (infile.get(ch)) {
		for (int i = 0; i < 8; i++) {
			if (and_bit[i] & ch) {
				data += "1";
			}
			else {
				data += "0";
			}
			count++;
			if (count == length) {
				break;
			}
		}
		if (count == length) {
			break;
		}
	}
	infile.close();
	return data;
}
