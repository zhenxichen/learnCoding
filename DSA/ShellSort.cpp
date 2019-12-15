#include <iostream>

void shellSort(int arr[], int n);

int main() {
	int arr[] = { 6,5,9,8,1,2,3,7,13,16 };
	shellSort(arr, 10);
	for (int i = 0; i < 10; i++) {
		std::cout << arr[i];
		if (i < 9) {
			std::cout << " ";
		}
		else {
			std::cout << std::endl;
		}
	}
	system("pause");
	return 0;
}

void shellSort(int arr[], int n) {
	int increment[] = { 8,4,2,1 };						//增量数组
	int gap = 0;
	for (int i = 0; i < 4; i++) {
		gap = increment[i];
		for (int j = 0; j < gap; j++) {
			for (int k = gap + j; k < n; k += gap) {
				if (arr[k] < arr[k - gap]) {
					int temp = arr[k];					//暂存arr[k]的值，用于插入到正确位置
					int w;								//提前定义，使w的作用域扩大到k循环
					for (w = k - gap; w >= 0 && arr[w] > temp;
						w -= gap) {
						arr[w + gap] = arr[w];			//向后移，参考插入排序
					}
					arr[w + gap] = temp;				//将先前arr[k]的值插入到该位置
				}
			}
		}
	}
}
