#include "BinNode.h"

using namespace std;

template <typename T> BinNodePosi(T) BinNode<T>::insertAsLC(const T & e) {
	return lChild = new BinNode(e, this);
}
template <typename T> BinNodePosi(T) BinNode<T>::insertAsRC(const T & e) {
	return rChild = new BinNode(e, this);
}
template <typename T> int BinNode<T>::size() {
	int s = 1;			//计入本身
	if (lChild) {
		s += lChild.size();
	}
	if (rChild) {
		s += rChild.size;
	}
	return s;
}
