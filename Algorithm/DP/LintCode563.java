public class LintCode563 {
    /* LintCode 563 背包问题 V */
    /**
     * 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数,
     * 正整数 target 表示背包的大小, 找到能填满背包的方案数。
     * 每一个物品只能使用一次
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：动态规划
// 题型：动态规划 0/1背包问题
// 递推公式：dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]]
// dp[i - 1][j]表示不考虑第i个物品时的方法数
// dp[i - 1][j - nums[i]]表示拿上第i个物品后的方法数
class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] nums, int target) {
        // write your code here
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 0; i < n; i++) {
            for(int j = target; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
