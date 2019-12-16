#include "path.h"

int main() {
	GraphMatrix<string, string> map;
	setMap(map);
	int begin = 0;
	int end = 0;
	int inputRes = 0;
	printPlace();
	inputRes = inputNum(begin, end);
	if (checkInput(begin, end, inputRes)) {
		findPath(map, begin - 1, end - 1);
	}
	system("pause");
	return 0;
}