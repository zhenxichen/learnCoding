/*keyboard.cpp*/

//本来是试图用来按怪猎的烧锅炉的（按着实在是太累了
//可惜这方法好像不太顶的样子

#include <Windows.h>

using namespace std;

void keyboardEvent1(int n);					//通过keybd_event函数
void keyboardEvent2(int n);					//补充填写了硬件扫描码

int main() {
//	keyboardEvent1(10000);
	keyboardEvent2(10000);
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

void keyboardEvent2(int n) {
	for (int i = 0; i < n; i++) {
		keybd_event('A', 0x1e, 0, 0);
		Sleep(10);
		keybd_event('A', 0x9e, KEYEVENTF_KEYUP, 0);
		Sleep(500);
		keybd_event('W', 0x11, 0, 0);
		Sleep(10);
		keybd_event('W', 0x91, KEYEVENTF_KEYUP, 0);
		Sleep(500);
		keybd_event('D', 0x20, 0, 0);
		Sleep(10);
		keybd_event('D', 0xa0, KEYEVENTF_KEYUP, 0);
		Sleep(1000);
	}
	//在参数中补充填写了硬件扫描码后，第一轮模拟按键可以生效了（可惜每次都只有第一轮有效
	//想要有效必须切回控制台再切回去- -
}

