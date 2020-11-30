package DP;

public class LintCode562 {
    /* LintCode 562 背包问题 IV */
    /**
     * 给出 n 个物品, 以及一个数组, nums[i]代表第i个物品的大小, 保证大小均为正数并且没有重复,
     * 正整数 target 表示背包的大小, 找到能填满背包的方案数。
     * 每一个物品可以使用无数次
     * 样例1
     * 输入: nums = [2,3,6,7] 和 target = 7
     * 输出: 2
     * 解释:
     * 方案有:
     * [7]
     * [2, 2, 3]
     */
}

// 动态规划：完全背包问题
class LintCode562_Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] nums, int target) {
        // write your code here
        int[] dp = new int[target + 1];     // dp[i]表示容量为i的背包的装满方式
        int n = nums.length;
        dp[0] = 1;
        // 完全背包问题，物品可以无限次使用
        for(int i = 0; i < n; i++) {
            // 我们可以进行正向遍历
            // 因为我们不需要去考虑前面的dp[j - nums[i]]是否已经使用了物品i了
            for(int j = nums[i]; j <= target; j++) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}