public class Leetcode72 {
    /* Leetcode 72 Edit Distance */

    /**
     * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
     * You have the following 3 operations permitted on a word:
     * 1. Insert a character
     * 2. Delete a character
     * 3. Replace a character
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (n == 0 || m == 0) {
            return n + m;
        }
        int dp[][] = new int[n + 1][m + 1];     //dp[i][j]表示word1的前i个字母到word2的前j个字母的编辑距离
        //设置边界条件
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = i;
        }
        //转换公式
        //最后一个字母相同时：dp[i][j] = min(dp[i-1][j-1],dp[i-1][j]+1,dp[i][j-1])
        //最后一个字母不同时：dp[i][j] = 1 + min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = dp[i - 1][j];
                int bottom = dp[i][j - 1];
                int left_bottom = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    left_bottom--;
                }
                dp[i][j] = 1 + Math.min(left_bottom, Math.min(left, bottom));
            }
        }
        return dp[n][m];
    }
}
