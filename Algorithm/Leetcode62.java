public class Leetcode62 {
    /* Leetcode 62 Unique Paths */

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time.
     * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there?
     */
    // 解题思路：动态规划
    // dp[i][j]表示（i,j）到终点(m-1,n-1)的可能路径数
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //设置边界条件
        dp[m - 1][n - 1] = 1;
        for (int i = 0; i < m - 1; i++) {
            dp[i][n - 1] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[m - 1][i] = 1;
        }
        //loop
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        //result
        return dp[0][0];
    }
}

