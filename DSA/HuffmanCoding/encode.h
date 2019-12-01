#ifndef _ENCODE_H_
#define _ENCODE_H_

#include <cstdio>

bool encode();					//压缩函数
bool printStat(int* stat, char* targetFile);							//将频率表存入压缩文件中
void fileEncoding(char* textFile, HuffTable table,char* targetFile);		//根据编码表将编码对文件进行编码
void fileWriting(char* targetFile, string output);						//将编码结果存入文件中


#endif // !_ENCODE_H_

