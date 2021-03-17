import java.util.*;

public class LeetCode139 {
    /* LeetCode 139 单词拆分 */
    /**
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：动态规划
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];      // dp[i]表示第i个字符之前（0~i-1）可以被切割为若干个单词
        dp[0] = true;
        Set<String> set = new HashSet<>();
        for (String word: wordDict) {       // 用HashSet来存储单词列表
            set.add(word);
        }
        // 递推公式：dp[i] = dp[j] & checks(j, i)
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}