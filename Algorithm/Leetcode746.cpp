/* Leetcode 746 Min Cost Climbing Stairs */
/**
* On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
* Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, 
* and you can either start from the step with index 0, or the step with index 1.
*/
//Note:	1.cost will have a length in the range [2, 1000].
//		2.Every cost[i] will be an integer in the range [0, 999].

#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	int minCostClimbingStairs(vector<int>& cost) {
		int dp[1001];				//dp[i]表示以i为终点所需的最小cost
		int n = cost.size();
		dp[0] = cost[0];
		dp[1] = cost[1];
		for (int i = 2; i < n; i++) {
			dp[i] = cost[i] + min(dp[i - 1], dp[i - 2]);
		}
		return min(dp[n - 1], dp[n - 2]);
	}
};