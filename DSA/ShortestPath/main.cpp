#include "path.h"

int main() {
	GraphMatrix<string, string> map = setMap();
	int begin = 0;
	int end = 0;
	printPlace();
	cout << "ÇëÊäÈëÆðµã±àºÅ£º";
	cin >> begin;
	cout << "ÇëÊäÈëÖÕµã±àºÅ£º";
	cin >> end;
	findPath(map, begin, end);
	return 0;
}