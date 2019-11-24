#include <iostream>

using namespace std;

void inSort(int arr[],int length);

int main() {
	int arr[] = { 1,5,9,8,6,2,3 };
	inSort(arr, 7);
	for (int i = 0; i < 7; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
	system("pause");
	return 0;
}

void inSort(int arr[],int length) {
	for (int i = 1; i < length; i++) {
		int key = arr[i];
		int j = 0;
		for (j = i - 1; j >= 0 && arr[j] > key; j--) {
			arr[j + 1] = arr[j];
		}
		arr[j + 1] = key;
	}
}