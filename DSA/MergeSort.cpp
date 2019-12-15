#include <iostream>
#include <list>

using namespace std;

void mergeSort(int arr[], int temp[], int left, int right);			//жи(nlogn)

int main() {
	int arr[] = { 36,20,17,13,28,14,23,15 };
	int temp[8] = { 0 };
	mergeSort(arr, temp, 0, 7);
	for (int i = 0; i < 8; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
	system("pause");
	return 0;
}

void mergeSort(int arr[], int temp[], int left, int right) {
	if (left == right) { return; }
	int mid = (left + right) / 2;
	mergeSort(arr, temp, left, mid);
	mergeSort(arr, temp, mid + 1, right);
	for (int i = left; i <= right; i++) {
		temp[i] = arr[i];
	}
	int i1 = left;
	int i2 = mid + 1;
	for (int i = left; i <= right; i++) {
		if (i1 == mid + 1) {
			arr[i] = temp[i2++];
		}
		else if (i2 > right) {
			arr[i] = temp[i1++];
		}
		else if (temp[i1] < temp[i2]) {
			arr[i] = temp[i1++];
		}
		else {
			arr[i] = temp[i2++];
		}
	}
}