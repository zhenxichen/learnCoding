#include "huffman.h"

void printError(int situ) {
	if (situ == 1) {
		cout << "没有与输入对应的操作" << endl;
	}
	exit(-1);
}

HTNode* HTNode::insertAsLC(HuffChar const & c) {
	lChild = new HTNode(c, NULL, NULL, this);
	return lChild;
}

HTNode* HTNode::insertAsRC(HuffChar const & c) {
	rChild = new HTNode(c, NULL, NULL, this);
	return rChild;
}

int* statistics(char* textFile) {
	int* freq = new int[N_CHAR];
	memset(freq, 0, sizeof(int)*N_CHAR);
	ifstream infile;
	char ch;
	infile.open(textFile);
	while (infile.get(ch)) {
		if (ch - 0x20 < N_CHAR) {
			freq[ch - 0x20]++;
		}
	}
	infile.close();
	return freq;
}

HuffForest* initForest(int* statistics) {
	HuffForest* forest = new HuffForest;
	for (char i = 0; i < N_CHAR; i++) {
		if (statistics[i] > 0) {				//若该字符的权值大于0
			forest->push_back(new HuffTree);
			forest->back()->insertAsRoot(HuffChar(0x20 + i, statistics[i]));
		}
	}
	return forest;
}

void HuffTree::insertAsRoot(HuffChar ch) {
	_size = 1;
	_root = new HTNode(ch);
}

HuffTree* findMin(HuffForest* forest) {
	HuffTree* minTree = forest->front();
	HuffForest::iterator it = forest->begin();
	for (it = forest->begin(); it != forest->end(); it++) {
		if ((*it)->root()->ch < minTree->root()->ch) {
			minTree = *it;
		}
	}
	forest->remove(minTree);
	return minTree;
}

HTNode* HuffTree::attachAsLC(HuffTree* & t) {
	_root->lChild = t->root();
	_root->lChild->parent = _root;
	return _root;
}

HTNode* HuffTree::attachAsRC(HuffTree* & t) {
	_root->rChild = t->root();
	_root->rChild->parent = _root;
	return _root;
}

HuffTree* buildTree(HuffForest* forest) {
	while (forest->size() > 1) {				//循环到仅剩最后一个哈夫曼树为止
		HuffTree* t1 = findMin(forest);			
		HuffTree* t2 = findMin(forest);
		int weight1 = t1->root()->ch.weight;
		int weight2 = t2->root()->ch.weight;
		HuffTree* newTree = new HuffTree;
		newTree->insertAsRoot(HuffChar('^', weight1 + weight2));
		newTree->attachAsLC(t1);
		newTree->attachAsRC(t2);
		forest->push_back(newTree);
	}
	return forest->front();
}

HuffTable buildTable(HuffTable table, HTNode* node,string code) {
	if (IsLeaf(*node)) {
		table[node->ch.ch] = code;
	}
	if (HasLChild(*node)) {
		table = buildTable(table, node->lChild, code + "0");
	}
	if (HasRChild(*node)) {
		table = buildTable(table, node->rChild, code + "1");
	}
	return table;
}

RTable buildTable(RTable table, HTNode* node, string code, int & len) {
	if (IsLeaf(*node)) {
		table[code] = node->ch.ch;
		if (code.length() > len) {
			len = code.length();
		}
	}
	if (HasLChild(*node)) {
		table = buildTable(table, node->lChild, code + "0", len);
	}
	if (HasRChild(*node)) {
		table = buildTable(table, node->rChild, code + "1", len);
	}
	return table;
}

