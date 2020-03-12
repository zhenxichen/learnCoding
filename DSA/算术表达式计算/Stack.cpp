#include "Stack.h"
#include <cstdio>
#include <iostream>

using namespace std;

/*做的这个栈在删除数据时只是去修改栈顶的位置，并没有将原有的数据修改回0*/

template<typename T> Stack<T>::Stack() {
	topPos = -1;		//设定栈顶位置为-1（即空栈）
	memset(arr, 0, STACKSIZE);
}

template<typename T> void Stack<T>::push(T data) {
	arr[++topPos] = data;
}

template<typename T> T Stack<T>::pop() {
	if (topPos == -1) {								//如果栈为空栈则提示
		printf("No element in current stack.");
		return -1;
	}
	return arr[topPos--];
}

template<typename T> T Stack<T>::top() const {
	if (topPos == -1) {
		printf("No element in current stack.");
		return -1;
	}
	return arr[topPos];
}

template<typename T> bool Stack<T>::empty() const{
	if (topPos == -1) { return true; }
	return false;
}

template<typename T> int Stack<T>::size() const {
	return topPos + 1;
}

template<typename T> void Stack<T>::clear(){
	topPos = -1;
}
