package DP;

public class LintCode125 {
    /* LintCode 125 背包问题 II */
    /**
     * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
     * 问最多能装入背包的总价值是多大?
     * 样例
     * 样例 1:
     * 输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
     * 输出: 9
     * 解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
     * 挑战
     * O(nm) 空间复杂度可以通过, 不过你可以尝试 O(m) 空间复杂度吗?
     * 注意事项:
     * 1. A[i], V[i], n, m 均为整数
     * 2. 你不能将物品进行切分
     */
    public static void main(String[] args) {
        LintCode125_Solution solution = new LintCode125_Solution();
    }
}

// 解法：动态规划
class LintCode125_Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // write your code here
        int[] dp = new int[m + 1];      // dp[i]表示容量为i时的最大Value
        int n = A.length;
        for(int i = 0; i < n; i++) {
            for(int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
            }
        }
        return dp[m];
    }
}
