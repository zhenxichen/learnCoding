/* Leetcode 64 Minimum Path Sum */
//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//每次只能向下或者向右移动一步。
//思路：只需给出结果->DP

#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

class Solution {
public:
	int minPathSum(vector<vector<int>>& grid) {
		int m = grid.size();				//m行
		int n = grid[0].size();				//n列
		for (int i = 1; i < n; i++) {		//修改第0行
			grid[0][i] = grid[0][i - 1] + grid[0][i];
		}
		for (int i = 1; i < m; i++) {		//修改第0列
			grid[i][0] = grid[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				grid[i][j] = grid[i][j] + min(grid[i - 1][j], grid[i][j - 1]);
			}
		}
		return grid[m - 1][n - 1];
	}
};

int main() {
	Solution s;
	vector<int> v1 = { 1,2,5 };
	vector<int> v2 = { 3,2,1 };
	vector<vector<int>> v;
	v.push_back(v1); v.push_back(v2);
	cout << s.minPathSum(v) << endl;
	system("pause");
	return 0;
}

//grid[i][j]=grid[i][j]+min{grid[i-1][j],grid[i][j-1]}