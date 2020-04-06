public class Leetcode583 {
    /* Leetcode 583 Delete Operation for Two Strings */
    /**
     * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
     * where in each step you can delete one character in either string.
     * Example 1:
     * Input: "sea", "eat"
     * Output: 2
     * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
     */
    //解法：动态规划
    //类似问题：Leetcode 72
    public static void main(String[] args){
        Solution s = new Solution();
    }
}

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
                } else {
                    dp[i][j] = Math.min(2 + dp[i - 1][j - 1], 1 + Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[n][m];
    }
}
