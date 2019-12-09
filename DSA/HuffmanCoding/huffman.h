#ifndef _HUFFMAN_H_
#define _HUFFMAN_H_

#define _SILENCE_STDEXT_HASH_DEPRECATION_WARNINGS
#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <cstdlib>
#include <string>
#include <list>
#include <hash_map>
#include <fstream>
#include <streambuf>

#define N_CHAR ((0x80))

#define IsChar(x) ((x) >= 0x20)
#define HasLChild(x) ((x).lChild)
#define HasRChild(x) ((x).rChild)
#define IsLeaf(x) (!( HasLChild(x) || HasRChild(x) ))

using namespace std;

struct HuffChar {
public:
	char ch;					//字符
	int weight;					//频率
	HuffChar(char c, int w) : ch(c), weight(w) { };
	bool operator< (HuffChar const & hc) { return weight < hc.weight; }		//重载运算符
	bool operator> (HuffChar const & hc) { return weight > hc.weight; }
	bool operator== (HuffChar const & hc) { return weight == hc.weight; }
	bool operator!= (HuffChar const & hc) { return weight != hc.weight; }
};

struct HTNode {								//哈夫曼树的结点
public:
	HTNode* lChild;
	HTNode* rChild;
	HTNode* parent;
	HuffChar ch;
	HTNode(HuffChar c) :
		ch(c), lChild(NULL), rChild(NULL), parent(NULL){ }
	HTNode(HuffChar c,HTNode* lc,HTNode* rc, HTNode* p):
		ch(c),lChild(lc),rChild(rc),parent(p){ }
	HTNode* insertAsLC(HuffChar const & c);
	HTNode* insertAsRC(HuffChar const & c);
	bool operator< (HTNode const & n) { return ch.weight < n.ch.weight; }
	bool operator> (HTNode const & n) { return ch.weight > n.ch.weight; }
	bool operator== (HTNode const & n) { return ch.weight == n.ch.weight; }
	bool operator!= (HTNode const & n) { return ch.weight != n.ch.weight; }
};

struct HuffTree {							//哈夫曼树
private:
	HTNode* _root;
	int _size;
public:
	HuffTree() :_root(NULL), _size(0){ }
	HTNode* root() { return _root; }
	void insertAsRoot(HuffChar ch);
	HTNode* attachAsLC(HuffTree* & t);		//将哈夫曼树接到根的左侧
	HTNode* attachAsRC(HuffTree* & t);		//将哈夫曼树接到根的右侧
};

typedef list<HuffTree*> HuffForest;			//哈夫曼森林
typedef stdext::hash_map<char,string> HuffTable;		//哈夫曼编码表
typedef stdext::hash_map<string, char> RTable;			//反转哈夫曼编码表


void printError(int situ);		//输出错误信息
int* statistics(char* textFile);	//统计字符出现频率,返回频率数组
HuffForest* initForest(int* statistics);		//初始化哈夫曼森林
HuffTree* buildTree(HuffForest* forest);		//建立哈夫曼树
HuffTree* findMin(HuffForest* forest);			//找出哈夫曼森林中根的权值最小的数
HuffTable buildTable(HuffTable table, HTNode* node,string code);		//建立编码表
RTable buildTable(RTable table, HTNode* node, string code, int & len);		//建立编码表,并获取最长编码长度

#endif
