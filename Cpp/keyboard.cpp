/*keyboard.cpp*/

//本来是试图用来按怪猎的烧锅炉的（按着实在是太累了
//可惜这方法好像不太顶的样子

#include <Windows.h>

using namespace std;

void keyboardEvent1(int n);					//通过keybd_event函数

int main() {
	keyboardEvent1(10000);
	return 0;
}

void keyboardEvent1(int n) {
	for (int i = 0; i < n; i++) {
		keybd_event('A', 0, 0, 0);
		keybd_event('A', 0, KEYEVENTF_KEYUP, 0);
		Sleep(500);
		keybd_event('W', 0, 0, 0);
		keybd_event('W', 0, KEYEVENTF_KEYUP, 0);
		Sleep(500);
		keybd_event('D', 0, 0, 0);
		keybd_event('D', 0, KEYEVENTF_KEYUP, 0);
		Sleep(500);
	}
}