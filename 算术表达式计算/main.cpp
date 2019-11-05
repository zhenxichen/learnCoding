#include <cstdio>
#include <iostream>
#include "Stack.h"
#include "Stack.cpp"
#include "calculate.h"
#include <cstring>
#include <string>

using namespace std;

int main() {
	char exp[100];
	string rpn;
	while (true) {
		rpn.clear();
		cout << "exp<<";
		cin >> exp;
		if (strcmp(exp,"quit")==0) {
			break;
		}
		if (!checkInput(exp, strlen(exp))) {
			continue;
		}
		toRPN(exp, strlen(exp), rpn);
		cout << "RPN:" << rpn << endl;
		cout << "Answer:" << rpnCal(rpn) << endl;
	}
	return 0;
}