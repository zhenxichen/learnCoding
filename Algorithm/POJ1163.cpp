/* POJ 1163 The Triangle */

#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int D[100][100] = { -1 };
	int maxSum[100][100] = { 0 };
	int n = 0;
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i + 1; j++) {
			cin >> D[i][j];
		}
	}
	for (int i = 0; i < n; i++) {
		maxSum[n - 1][i] = D[n - 1][i];
	}
	for (int i = n - 2; i >= 0; i--) {
		for (int j = 0; j < i + 1; j++) {
			maxSum[i][j] = max(maxSum[i + 1][j], maxSum[i + 1][j + 1]) + D[i][j];
		}
	}
	cout << maxSum[0][0] << endl;
	return 0;
}