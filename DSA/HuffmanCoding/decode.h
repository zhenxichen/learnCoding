#ifndef _DECODE_H_
#define _DECODE_H_

#include <cstdio>

bool decode();					//解压函数
int* readStat(char* filename);	//读出字符频率表
void fileDecoding(char* filename, char* targetName, RTable table, int maxLength);

#endif // !_DECODE_H_
