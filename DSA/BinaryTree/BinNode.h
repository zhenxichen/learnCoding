#ifndef _BINNODE_H_
#define _BINNODE_H_

#define BinNodePosi(T) BinNode<T>*
#define stature(p) ((p)?(p)->height:-1)

template <typename T> struct BinNode {
	BinNodePosi(T) parent, lChild, rChild;
	T data;
	int size();
	int height;
	template <typename T> BinNode<T>(T const &e, BinNode<T> parent);
	BinNodePosi(T) insertAsLC(T const &);
	BinNodePosi(T) insertAsRC(T const &);
	BinNodePosi(T) succ();									//(中序遍历意义下)当前节点的直接后继
	template <typename VST> void travLevel(VST &);			//子树层次遍历
	template <typename VST> void travPre(VST &);			//子树先序遍历
	template <typename VST> void travIn(VST &);				//子树中序遍历
	template <typename VST> void travPost(VST &);			//子树后序遍历
};

#endif // !_BINNODE_H_

