#include <iostream>

using namespace std;

void Qsort(int arr[], int low, int high);
void swap(int &x, int &y);

int main() {
	int arr[] = { 1,3,5,7,4,2,9,0,6,16,71,66,80,93,95 };
	Qsort(arr, 0, 14);
	for (int i = 0; i < 14; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
	system("pause");
	return 0;
}

void Qsort(int arr[], int low, int high) {
	if (low >= high) { return; }
	
	int key = arr[low];		//取第一个数作为key
	int i = low;			//从左往右寻找比key大的数
	int j = high + 1;		//从右往左寻找比key小的数

	while (true) {
		while (arr[++i] < key) {
			if (i == high) { break; }
		}
		while (arr[--j] > key) {
			if (j == low) { break; }
		}
		if (i >= j) { break; }						//两者相交，即已经全部进行交换
		swap(arr[i], arr[j]);						//将两个数进行交换
	}
	swap(arr[low], arr[j]);							//将key放至中间（左边都比key小，右边都比key大）
													//arr[j]比key小，arr[i]比key大，因此应将arr[j]放至开头
	Qsort(arr, low, j - 1);							//注意参数应为j-1,j+1,如果以j为参数会导致Stack overflow
	Qsort(arr, j + 1, high);
}



void swap(int &x, int &y) {
	int temp = x;
	x = y;
	y = temp;
}