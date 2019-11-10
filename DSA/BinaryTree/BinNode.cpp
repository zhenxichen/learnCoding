#include "BinNode.h"
#include <queue>

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

template <typename T> template <typename VST> void BinNode<T>::travLevel(VST &visit) {
	queue<BinNodePosi(T)> q;
	q.push(this);
	while (!q.empty()) {
		BinNodePosi(T) x = q.front();
		q.pop();
		visit(x->data);
		if (HasLChild(x)) {
			q.push(x->lChild);
		}
		if (HasRChild(x)) {
			q.push(x->rChild);
		}
	}
}

template <typename T> template <typename VST> void BinNode<T>::travPre(BinNodePosi(T) x, VST &visit) {
	if (!x) { return; }
	visit(x->data);
	travPre(x->lChild, visit);
	travPre(x->rChild, visit);
}

template <typename T> template <typename VST> void BinNode<T>::travIn(BinNodePosi(T) x, VST &visit) {
	if (!x) { return; }
	travPre(x->lChild, visit);
	visit(x->data);
	travPre(x->rChild, visit);
}

template <typename T> template <typename VST> void BinNode<T>::travPost(BinNodePosi(T) x, VST &visit) {
	if (!x) { return; }
	travPre(x->lChild, visit);
	travPre(x->rChild, visit);
	visit(x->data);
}

template <typename T> BinNodePosi(T) BinNode<T>::succ() {
	BinNodePosi(T) s = this;
	if (rChild) {		//如有rChild，直接后继必在右子树中
		s = rc;
		while (HasLChild(*s)) {
			s = s->lChild;
		}
	}
	else {
		while (IsRChild(*s)) {
			s = s->parent;
		}
		s = s->parent;
	}
	return s;
}

