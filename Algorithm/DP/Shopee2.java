public class Shopee2 {
    /**
     * 将整数n分成k份，且每份不能为空，任意两种分法不能相同(不考虑顺序)。
     * 例如: n = 7, k = 3, 下面三种分法被认为是相同的：
     * 1, 1, 5; 1, 5, 1; 5, 1, 1。
     * 问有多少种不同的分法。
     * 输入: n, k (6 < n < 200, 1 < k < 7)
     * 输出: 一个整数 (所有可能的分法总数)
     */
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        int ans = s.divide(7, 3);
        System.out.println(ans);
    }
}

class Solution2 {
    /**
     * Note: 类名、方法名、参数名已经指定，请勿修改
     *
     * @param n int整型 整数n
     * @param k int整型 分为k份
     * @return int整型
     */
    public int divide(int n, int k) {
        // write code here
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i][j] = dp[i - j][j] + dp[i - 1][j - 1];
            }
        }
        return dp[n][k];
    }
}
