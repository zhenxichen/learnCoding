#include <iostream>

using namespace std;

void quickSort(int arr[], int low,int high);

int main() {
	int arr[] = { 72,6,57,88,85,42,83,73,48,60 };
	quickSort(arr, 0, 9);
	for (int i = 0; i < 10; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
	system("pause");
	return 0;
}

void quickSort(int arr[], int low, int high) {
	if (low >= high) { return; }
	int i = low;
	int j = high;
	int key = arr[low];
	while (i < j) {
		while (i < j && arr[j] >= key) {
			j--;
		}
		arr[i] = arr[j];
		while (i < j && arr[i] <= key) {
			i++;
		}
		arr[j] = arr[i];
	}
	arr[i] = key;
	quickSort(arr, low, i);
	quickSort(arr, i + 1, high);
}