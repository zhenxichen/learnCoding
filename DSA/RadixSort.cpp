/*Radix Sort 基数排序*/

#include <iostream>

using namespace std;

void radixSort(int arr[],int n);
int maxBit(int arr[],int n);

int main() {
	int arr[] = { 27,91,1,97,17,23,84,28,72,5,67,25 };
	radixSort(arr, 12);
	for (int i = 0; i < 12; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
	system("pause");
	return 0;
}

void radixSort(int arr[],int n) {
	int d = maxBit(arr, n);
	int count[10];
	int *temp = new int[n];
	int radix = 1;
	for (int i = 0; i < d; i++, radix *= 10) {
		int j = 0;
		for (j = 0; j < 10; j++) {			//将计数数组初始化为0
			count[j] = 0;
		}
		for (j = 0; j < n; j++) {
			int k = ((arr[j] / radix) % 10);
			count[k]++;							//记录每个桶中的元素数量
		}
		for (j = 1; j < 10; j++) {				//将数量累加，换算得到对应的数组位置
			count[j] += count[j - 1];
		}
		for (j = n - 1; j >= 0; j--) {			//必须从后往前
			int k = (arr[j] / radix) % 10;
			temp[count[k] - 1] = arr[j];
			count[k]--;
		}
		for (j = 0; j < n; j++) {
			arr[j] = temp[j];
		}
	}
	delete[] temp;
}

int maxBit(int arr[],int n) {
	int max = arr[0];
	int count = 0;
	for (int i = 0; i < n; i++) {				//遍历数组，找到最大的一个数
		if (arr[i] > max) {
			max = arr[i];
		}
	}
	while (max > 0) {
		max /= 10;
		count++;
	}
	return count;
}