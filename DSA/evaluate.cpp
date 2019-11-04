#include <iostream>
#include <stack>
#include <sstream>

//存在BUG

using namespace std;

float evaluate(char* S, char* &RPN);
float readNumber(char* &S, stack<float> opnd);
bool isDigit(char c);
int orderBetween(char top, char c);
float calcu(char op, float opnd);
float calcu(float opnd1, char op, float opnd2);

float evaluate(char* S, char* &RPN) {
	stack<float> opnd;		//运算数栈
	stack<char> optr;		//运算符栈
	
	optr.push('\0');		
	while (!optr.empty()) {
		if (isDigit(*S)) {
			readNumber(S, opnd);
		}
		else {
			switch (orderBetween(optr.top(), *S)) {
			case 2:optr.pop(); S++; break;
			case 1: {
				char op = optr.top();
				optr.pop();
				if ('!' == op) {
					float top = opnd.top();
					opnd.pop();
					opnd.push(calcu(op, top));
				}
				else {
					float pOpnd2 = opnd.top();
					opnd.pop();
					float pOpnd1 = opnd.top(); opnd.pop();
					opnd.push(calcu(pOpnd1, op, pOpnd2));
				}
			}//case 1
					break;
			case -1:optr.push(*S); S++; break;
			default:exit(-1);
			}//switch
		}
	}

	return opnd.top();
}

float readNumber(char* &S, stack<float> opnd) {
	char string[10];
	float number = 0.0;
	int i = 0;
	stringstream ss;

	while (isDigit(*S)) {
		string[i++] = *S;
		S++;
	}
	string[i] = '\0';
	ss << string;
	ss >> number;
	opnd.push(number);
	
	return number;
}

bool isDigit(char c) {
	const char digit[] = { '0','1','2','3','4','5',
							'6','7','8','9','.' };
	for (int i = 0; i < 10; i++) {
		if (digit[i] == c) {
			return true;
		}
	}
	return false;
}

int orderBetween(char top, char c) {
	const int pri[9][9] = {
		//		当前运算符
		//		+    -   *   /   ^   !   (   )   \0
				1,	1,	-1,	-1,	-1,	-1,	-1,	1,	1,
		/*栈*/	1,	1,	-1,	-1,	-1,	-1,	-1,	-1,	-1,
		/*顶*/	1,	1,	1,	1,	-1,	-1,	-1,	1,	1,
		/*运*/	1,	1,	1,	1,	-1,	-1,	-1,	1,	1,
		/*算*/	1,	1,	1,	1,	1,	-1,	-1,	1,	1,
		/*符*/	1,	1,	1,	1,	1,	1,	0,	1,	1,
				-1,	-1,	-1,	-1,	-1,	-1,	-1, 2,  0,
				0,	0,	0,	0,	0,	0,	0,	0,	0,
				-1,	-1,	-1,	-1,	-1,	-1,	-1, 0,	2
	};
	int i = 0;
	int j = 0;
	switch (top) {
	case '+':i = 0; break;
	case '-':i = 1; break;
	case '*':i = 2; break;
	case '/':i = 3; break;
	case '^':i = 4; break;
	case '!':i = 5; break;
	case '(':i = 6; break;
	case ')':i = 7; break;
	case '\0':i = 8; break;
	default:break;
	}
	switch (c) {
	case '+':j = 0; break;
	case '-':j = 1; break;
	case '*':j = 2; break;
	case '/':j = 3; break;
	case '^':j = 4; break;
	case '!':j = 5; break;
	case '(':j = 6; break;
	case ')':j = 7; break;
	case '\0':j = 8; break;
	default:break;
	}
	return pri[i][j];
}

float calcu(char op, float opnd) {
	float ret = 1;
	if (op == '!') {
		for (int i = 1; i <= opnd; i++) {
			ret *= i;
		}
	}
	return ret;
}

float calcu(float opnd1, char op, float opnd2) {
	switch (op) {
	case '+':return opnd1 + opnd2;
	case '-':return opnd1 - opnd2;
	case '*':return opnd1 * opnd2;
	case '/':return opnd1 / opnd2;
	case '^':return pow(opnd1, opnd2);
	default:exit(-1);
	}
}
