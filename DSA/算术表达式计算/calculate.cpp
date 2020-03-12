#include "Stack.h"
#include "Stack.cpp"
#include "calculate.h"
#include <iostream>
#include <string>
#include <sstream>

using namespace std;

bool paren(const char* string,int len) {
	Stack<char> s = Stack<char>();										//用来存放括号的栈
	for (int i = 0; i < len; i++) {
		if (string[i] == '(') {				//如果该字符为左括号，压栈
			s.push(string[i]);							
		}
		else if (string[i] == ')') {		//如果该字符为右括号
			if (s.empty()) { return false; }			//若栈空，则不匹配
			s.pop();									//否则，弹出栈顶括号
		}
	}
	return s.empty() ? true : false;				//遍历结束后，若栈为空，则全部匹配，若非空，则未匹配
}

bool checkInput(const char* string, int len) {
	if (!paren(string,len)) {
		cout << "输入表达式中括号不匹配" << endl;
		system("pause");
		return false;
	}
	if (!checkChar(string, len)) { 
		cout << "输入表达式中含有非法符号" << endl;
		system("pause");
		return false;
	}
	if (!checkOpt(string, len)) {
		cout << "输入表达式格式错误" << endl;
		system("pause");
		return false;
	}
	return true;
}

bool checkChar(const char* string, int len) {
	char validChar[] = { '0','1','2','3','4','5','6','7'
						,'8','9','+','-','*','/','#','.','(',')' };
	bool valid = true;
	for (int i = 0; i < len; i++) {
		valid = false;
		for (int j = 0; j < 18; j++) {
			if (string[i] == validChar[j]) {
				valid = true;
				break;
			}
		}
		if (!valid) { return false; }
	}
	return true;
}

bool checkOpt(const char* string, int len) {
	bool isOpt = false;
	for (int i = 0; i < len; i++) {
		if (IsOpt(string[i])) {
			if (isOpt) {							//若上一个字符也为操作符
				return false;
			}
			isOpt = true;
		}
		else {
			isOpt = false;
		}
	}
	return true;
}

void toRPN(const char* exp, int len, string& rpn) {									
	Stack<char> optr = Stack<char>();				//操作符栈
	
	optr.push('z');									//压入一个优先级为0的字符,解决一开始的对比问题

	for (int i = 0; i < len; i++) {
		if (isDigit(exp[i])) {
			rpn += exp[i];							//如果是数字，则直接加在字符串后
		}
		else {
			rpn += " ";								//读到符号加空格（便于区分逆波兰式中的两个数）
			int order = orderBetween(optr.top(), exp[i]);
			if (order == -1) {		//栈顶运算符比当前运算符级别低(或栈顶为左括号)
				optr.push(exp[i]);								//则进栈
			}
			else if (order == 0) {	//若读到右括号
				while (optr.top() != '(') {
					rpn += optr.pop();							//弹出直到‘(’为止
				}
				optr.pop();										//将左括号也弹出
			}
			else if (order == 1) {
				rpn += optr.pop();
				optr.push(exp[i]);
			}
		}
	}
	while (optr.top() != 'z') {									//将操作符栈中所有剩余操作符弹出
		rpn += optr.pop();
	}
}

bool isDigit(char c) {
	const char digit[] = { '0','1','2','3','4','5',
							'6','7','8','9','.' };			//未处理：小数点计算
	for (int i = 0; i < 11; i++) {
		if (digit[i] == c) {
			return true;
		}
	}
	return false;
}

int orderBetween(const char top, const char cur) {
	/*若当前优先级大于栈顶，则返回-1，如cur为右括号，返回0，否则返回1*/
	if (cur == ')') {
		return 0;
	}
	if (top == '(') { return -1; }
	return getPri(cur) > getPri(top) ? -1 : 1;
}

int getPri(char c) {
	if (c == 'z') {
		return 0;
	}
	if (c == '+' || c == '-') {
		return 1;
	}
	if (c == '*' || c == '/') {
		return 2;
	}
	if (c == '#') {
		return 3;
	}
	if (c == '(') { return 4; }
	return 1;
}

float calcu(float opnd1, char op, float opnd2) {
	switch (op) {
	case '+':return opnd1 + opnd2;
	case '-':return opnd1 - opnd2;
	case '*':return opnd1 * opnd2;
	case '/':return opnd1 / opnd2;
	default:exit(-1);
	}
}

float rpnCal(string rpn) {
	Stack<float> opnd = Stack<float>();				//操作数栈
	string str;										//用以暂存数
	float opnd1 = 0.0;
	float opnd2 = 0.0;
	for (size_t i = 0; i < rpn.size(); i++) {
		if (isDigit(rpn[i])) {
			str += rpn[i];
		}
		else {
			if (!str.empty()) {
				readNumber(str, opnd);
				str.clear();
			}
			if (rpn[i] == '#') {									//遇到一元操作符
				opnd1 = opnd.pop();
				opnd1 = 0.0 - opnd1;
				opnd.push(opnd1);
			}
			else if (rpn[i] != ' ') {													//二元运算符
				opnd2 = opnd.pop();										//先退出的放右边，后退出的放左边
				opnd1 = opnd.pop();
				char optr = rpn[i];
				opnd.push(calcu(opnd1, optr, opnd2));					//不能opnd.push(opnd.pop(),optr,opnd.pop())
																		//具体我也不知道为什么
			}
		}
	}
	return opnd.pop();
}

float readNumber(string str, Stack<float> &opnd) {
	stringstream ss;
	float number = 0.0;
	ss << str;
	ss >> number;
	opnd.push(number);
	return number;
}