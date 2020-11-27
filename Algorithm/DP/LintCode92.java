package DP;

public class LintCode92 {
    /* LintCode 92 背包问题 */
    /**
     * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
     * 样例
     * 样例 1:
     * 	输入:  [3,4,8,5], backpack size=10
     * 	输出:  9
     * 样例 2:
     * 	输入:  [2,3,5,7], backpack size=12
     * 	输出:  12
     * 挑战:
     * O(n x m) 的时间复杂度 and O(m) 空间复杂度
     */
    public static void main(String[] args) {
        LintCode92_Solution1 solution1 = new LintCode92_Solution1();
        solution1.backPack(10, new int[]{3, 4, 8, 5});
        LintCode92_Solution solution = new LintCode92_Solution();
    }
}

// 优化空间复杂度为O(n)
class LintCode92_Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        int[] dp = new int[m + 1];      // 背包空间为j时最多能装多少
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {   // 必须从后往前
                dp[j] = Math.max(dp[j], dp[j - A[i]] + A[i]);   // 因为物品只有一个，从前往后会重复计算
            }
        }
        return dp[m];
    }
}

// 时间复杂度O(n*m) 空间复杂度O(n*m)
class LintCode92_Solution1 {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        int n = A.length;
        int[][] dp = new int[n][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[0][i] = (A[0] >= i ? 0 : A[0]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j >= A[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i]] + A[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][m];
    }
}
