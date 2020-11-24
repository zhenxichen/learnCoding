package DP;

public class LeetCode91 {
    /* LeetCode 91 解码方法 */
    /**
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     * 题目数据保证答案肯定是一个 32 位的整数。
     * 示例 1：
     * 输入：s = "12"
     * 输出：2
     * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2：
     * 输入：s = "226"
     * 输出：3
     * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     * 示例 3：
     * 输入：s = "0"
     * 输出：0
     */
    public static void main(String[] args) {
        Solution91_DP solution = new Solution91_DP();
    }
}

class Solution91_DP {
    public int numDecodings(String s) {
        int n = s.length();
        char charAt0 = s.charAt(0);
        if(charAt0 == '0') { return 0; }
        int[] dp = new int[n];
        // 填充边界条件
        dp[0] = 1;
        if(n > 1) {
            dp[1] = 1;
            char charAt1 = s.charAt(1);
            switch(charAt0) {
                case '2': {
                    if(charAt1 >= '1' && charAt1 <= '6') {
                        dp[1] = 2;
                    }
                    break;
                }
                case '1': {
                    if(charAt1 != '0') {
                        dp[1] = 2;
                    }
                    break;
                }
                case '0': {
                    return 0;
                }
                default: {
                    if(charAt1 == '0') {
                        return 0;
                    }
                }
            }
        }
        // 填充dp数组
        for(int i = 2; i < n; i++) {
            char before = s.charAt(i - 1);
            char curr = s.charAt(i);
            switch(before) {
                case '2': {
                    if(curr >= '1' && curr <= '6') {
                        // 可与前一个数字共同表示一个字母
                        // dp[i - 1]表示单独表示字母的情况数
                        // dp[i - 2]表示与前一个数字共同表示一个字母的情况数
                        dp[i] = dp[i - 1] + dp[i - 2];
                    } else if(curr == '0') {
                        // 只能与前一个数字共同表示一个字母
                        dp[i] = dp[i - 2];
                    } else {
                        // 只能自己表达一个字母
                        dp[i] = dp[i - 1];
                    }
                    break;
                }
                case '1': {
                    if(curr == '0') {
                        dp[i] = dp[i - 2];
                    } else {
                        dp[i] = dp[i - 1] + dp[i - 2];
                    }
                    break;
                }
                default: {
                    // 前一个字符不是1或2，则无法与当前字符共同表示一个字母
                    if(curr == '0') {
                        return 0;
                    }
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
