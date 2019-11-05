#ifndef _STACK_H_
#define _STACK_H_

#define STACKSIZE 100

template<typename T> class Stack {
protected:
	int topPos;					//栈顶所在位置
	T arr[STACKSIZE];			//用以存储数据的数组
public:
	Stack();					//构造函数
	void push(T data);
	T top() const;
	T pop();
	bool empty() const;
	int size() const;
	void clear();
};

#endif
