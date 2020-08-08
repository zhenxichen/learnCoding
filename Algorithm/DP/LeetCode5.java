public class LeetCode5 {
    /* LeetCode 5 最长回文子串 */
    // tags: 动态规划

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 示例 1：
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 提示：
     * 1. How can we reuse a previously computed palindrome to compute a larger palindrome?
     * 2. If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestPalindrome("abcda");
    }
}

// 解法：动态规划
// 状态转移方程: dp(i, j) = {dp(i+1, j-1) || j-i < 3} && {s[i] == s[j]}
// 边界条件:
class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        boolean[][] dp = new boolean[length][length];
        int maxLength = 1;
        int begin = 0;

        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLength) {
                    begin = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }
}
