#ifndef _DECODE_H_
#define _DECODE_H_

#include <cstdio>

bool decode();					//解压函数
int* readStat(char* filename);	//读出字符频率表
void fileDecoding(char* filename, char* targetName, RTable table, int maxLength);
string fileReading(char* filename);				//将文件中的char型数以二进制字符串形式读入

#endif // !_DECODE_H_
