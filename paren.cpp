#include <iostream>
#include <stack>

using namespace std;

bool paren(const char exp[],int n);

int main() {
	char c[30];
	cin >> c;
	bool b = paren(c, 30);
	system("pause");
	return 0;
}

bool paren(const char exp[],int n) {
	stack<char> s;
	for (int i = 0; i < n; i++) {
		if (exp[i] == '(' || '[' || '{') {
			s.push(exp[i]);
		}
		else if(exp[i] == ')'||']'||'}'){
			char top = s.top();
			if (top == '{') {
				if (exp[i] == '}') { return true; }
				else return false;
			}
			else if (top == '[') {
				if (exp[i] == ']') return true;
				else return false;
			}
			else {
				if (exp[i] == ')') return true;
				else return false;
			}
		}
	}
}