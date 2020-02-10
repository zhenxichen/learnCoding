/* LintCode 92 Backpack*/
/**
* Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
*/

#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	/**
	 * @param m: An integer m denotes the size of a backpack
	 * @param A: Given n items with size A[i]
	 * @return: The maximum size
	 */
	int backPack(int m, vector<int> &A) {
		// write your code here
		int n = A.size();
		if (n == 0 || m <= 0) { return 0; }
		vector<vector<int>> dp(n, vector<int>(m + 1, 0));	//dp[i][j]表示前i件物品装进容量为j的背包中
		for (int i = 0; i <= m; i++) {
			dp[0][i] = (i >= A[0] ? A[0] : 0);
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				int temp = dp[i - 1][j];
				if (j >= A[i]) {
					temp = max(temp, dp[i - 1][j - A[i]] + A[i]);
				}
				dp[i][j] = temp;
			}
		}
		return dp[n - 1][m];
	}
};