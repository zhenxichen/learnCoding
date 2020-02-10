/* Leetcode 322 Coin Change */
//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
//如果没有任何一种硬币组合能组成总金额，返回 -1。

#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	int coinChange(vector<int>& coins, int amount) {
		const int MAX = 0x3f3f3f3f;
		vector<int> dp(amount + 1, MAX);
		int n = coins.size();
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < n; j++) {
				if (i >= coins[j]) {
					dp[i] = min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		if (dp[amount] > amount) {
			return -1;
		}
		return dp[amount];
	}
};