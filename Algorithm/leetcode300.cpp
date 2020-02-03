/* Leetcode 300 Longest Increasing Subsequence */
/* Given an unsorted array of integers, find the length of longest increasing subsequence. */
//Note:
//	There may be more than one LIS combination, it is only necessary for you to return the length.
//	Your algorithm should run in O(n2) complexity.
//Follow up: Could you improve it to O(n log n) time complexity?

//解题思路：动态规划
//子问题设置：以ak(k=0,1,...,n-1)为终点的上升子序列最大长度

#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	int lengthOfLIS(vector<int>& nums) {
		int n = nums.size();
		if (n == 0) { return 0; }
		int maxLength = 1;
		vector<int> dp(n, 1);					//dp[i]表示以nums[i]为终点的上升子序列最大长度
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = max(dp[i], dp[j] + 1);
					maxLength = max(dp[i], maxLength);
				}
			}
		}
		return maxLength;
	}
};		//时间复杂度O(n^2)

int main() {
	Solution s;
	vector<int> v;
	v.push_back(1); v.push_back(3); v.push_back(6);
	v.push_back(7); v.push_back(9); v.push_back(4);
	v.push_back(10); v.push_back(5); v.push_back(6);
	s.lengthOfLIS(v);
	return 0;
}
