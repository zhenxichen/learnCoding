/* Leetcode 120 Triangle */
/**
* Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
* Note:
* Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	int minimumTotal(vector<vector<int>>& triangle) {
		int n = triangle.size();
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				//P(i,j)=triangle[i][j]+min{P(i+1,j),P(i+1,j+1)}
				triangle[i][j] += min(triangle[i + 1][j], triangle[i + 1][j + 1]);
			}
		}
		return triangle[0][0];
	}
};