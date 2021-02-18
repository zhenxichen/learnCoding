public class NC127 {
    /* NC 127 最长公共子串 */
    /**
     * 给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
     */
    public static void main(String[] args) {

    }
}

class Solution {
    /**
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS (String str1, String str2) {
        // write code here
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        maxIndex = i;
                    }
                }
            }
        }
        if (max <= 0) {
            return "-1";
        }
        return str1.substring(maxIndex - max, maxIndex);
    }
}