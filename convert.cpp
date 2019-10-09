#include <iostream>
#include <stack>

using namespace std;

stack<char> convert(int n, int base);

int main() {
	stack<char> des;
	int n = 0, base = 0;
	cin >> n;
	cin >> base;
	des = convert(n, base);
	while (!des.empty()) {
		cout << des.top();
		des.pop();
	}
	cout << endl;
	system("pause");
	return 0;
}

stack<char> convert(int n, int base) {
	const char digit[] = {'0','1','2','3','4','5','6','7',
		'8','9','A','B','C','D','E','F',};
	stack<char> stk;
	while (n > 0) {
		stk.push(digit[n%base]);
		n /= base;
	}
	return stk;
}