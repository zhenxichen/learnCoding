#include "BinNode.h"
#include <iostream>

using namespace std; 

template <typename T> class BinTree {
protected:
	int _size;
	BinNodePosi(T) _root;
	virtual int updateHeight(BinNodePosi(T) x);					//更新x的高度
	void updateHeightAbove(BinNodePosi(T) x);					//更新x及其祖先的高度
public:
	int size() const { return _size; }							//const表示不允许函数修改类的成员
	bool empty() const { return !_root; }
	BinNodePosi(T) root() { return _root; }
};

template <typename T> int BinTree<T>::updateHeight(BinNodePosi(T) x) {
	return x->height = stature(x->lChild) > stature(x->rChild) ?
		stature(x->lChild) + 1 : stature(x->rChild) + 1;
}

template <typename T> void BinTree<T>::updateHeightAbove(BinNodePosi(T) x) {
	while (x) {
		if (x->height == updateHeight(x)) {						//若高度未变
			break;												//则退出循环
		}
		x = x->parent;											//遍历其祖先
	}
}

template <typename T> void preTrav(BinNodePosi(T) pRoot) {		//二叉树的前序遍历（递归实现）
	if (!pRoot) { return; }
	/*do something*/
	cout << pRoot->data << endl;
	
	preTrav(pRoot->lChild);
	preTrav(pRoot->rChild);
}

template <typename T> void inTrav(BinNodePosi(T) pRoot) {		//二叉树的中序遍历（递归实现）
	if (!pRoot) { return; }
	inTrav(pRoot->lChild);
	/*do something*/
	cout << pRoot->data << endl;
	inTrav(pRoot->rChild);
}

template <typename T> void postTrav(BinNodePosi(T) pRoot) {		//二叉树的后续遍历（递归实现）
	if (!pRoot) { return; }
	postTrav(pRoot->lChild);
	postTrav(pRoot->rChild);
	/*do something*/
	cout << pRoot->data << endl;
}

