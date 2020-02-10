/* Leetcode 931 Minimum Falling Path Sum */
// Given a square array of integers A, we want the minimum sum of a falling path through A.
// A falling path starts at any element in the first row, and chooses one element from each row.  
// The next row's choice must be in a column that is different from the previous row's column by at most one.

#include <algorithm>
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
	int minFallingPathSum(vector<vector<int>>& A) {
		int m = A.size();
		int n = A[0].size();
		int best;
		for (int i = m - 2; i >= 0; i--) {
			//A[i][j]=min(A[i+1][j],A[i+1][j-1],A[i+1][j+1])
			for (int j = 0; j < n; j++) {
				best = A[i + 1][j];
				if (j > 0) {
					best = min(best, A[i + 1][j - 1]);
				}
				if (j + 1 < n) {
					best = min(best, A[i + 1][j + 1]);
				}
				A[i][j] += best;
			}
		}
		best = A[0][0];
		for (int i = 1; i < n; i++) {
			if (best > A[0][i]) { best = A[0][i]; }
		}
		return best;
	}
};

int main() {
	Solution s;
	vector<int> v1 = { 1,2,3 };
	vector<int> v2 = { 4,5,6 };
	vector<int> v3 = { 7,8,9 };
	vector<vector<int>> v;
	v.push_back(v1); v.push_back(v2); v.push_back(v3);
	cout << s.minFallingPathSum(v) << endl;
	return 0;
}
