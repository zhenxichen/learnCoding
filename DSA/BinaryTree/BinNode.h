#ifndef _BINNODE_H_
#define _BINNODE_H_

#include <iostream>

#define BinNodePosi(T) BinNode<T>*
#define stature(p) ((p)?(p)->height:-1)

#define IsRoot(x) (!((x).parent))
#define IsLChild(x) (!IsRoot(x) && ( &(x) == (x).parent->lChild ))
#define IsRChild(x) (!IsRoot(x) && ( &(x) == (x).parent->rChild ))
#define HasParent(x) (!IsRoot(x))
#define HasLChild(x) ((x).lChild)
#define HasRChild(x) ((x).rChild)
#define HasChild(x) ( HasLChild(x) || HasRChild(x) )
#define HasBothChild(x) (HasLChild(x) && HasRChild(x))
#define IsLeaf(x) (!HasChild(x))
#define sibling(p) (IsLChild(*(p))?(p)->parent->rChild:(p)->parent->lChild)
#define uncle(x) ( IsLChild(*((x)->parent))?(x)->parent->parent->rChild:(x)->parent->parent->lChild)
#define FromParentTo(x) (IsRoot(x)? _root:(IsLChild(x)?(x).parent->lChild:(x).parent->rChild))

template <typename T> struct BinNode {
	BinNodePosi(T) parent, lChild, rChild;
	T data;
	int size();
	int height;
	BinNode():
		parent(NULL),lChild(NULL),rChild(NULL),height(0){ }
	BinNode(T e, BinNodePosi(T) p = NULL, BinNodePosi(T) lc = NULL,
		BinNodePosi(T) rc = NULL, int h = 0):
		data(e),parent(p),lChild(lc),rChild(rc),height(h){ }
	BinNodePosi(T) insertAsLC(T const &);
	BinNodePosi(T) insertAsRC(T const &);
	BinNodePosi(T) succ();									//(中序遍历意义下)当前节点的直接后继
	template <typename VST> void travLevel(VST & visit);		//子树层次遍历
	template <typename VST> void travPre(BinNodePosi(T) x, VST & visit);			//子树先序遍历
	template <typename VST> void travIn(BinNodePosi(T) x, VST & visit);				//子树中序遍历
	template <typename VST> void travPost(BinNodePosi(T) x, VST & visit);			//子树后序遍历
};

#endif // !_BINNODE_H_

