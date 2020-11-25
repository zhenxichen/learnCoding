package DP;

public class LeetCode213 {
    /* LeetCode 213 打家劫舍 II */
    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
     * 示例 1：
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 链接： https://leetcode-cn.com/problems/house-robber-ii/
     */
    public static void main(String[] args) {
        Solution213_DP solution = new Solution213_DP();
    }
}

class Solution213_DP {
    // 本题与LeetCode 198的区别在于，这里的房屋是环状的
    // 因此，我们需要分类讨论选择第一间房屋和最后一间房屋的两种情况
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) { return nums[0]; }
        if(n == 2) { return Math.max(nums[0], nums[1]); }
        int[] dp = new int[n - 1];
        // 先计算偷窃第一间的情况
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        int ans = dp[n - 2];
        // 再计算偷窃最后一间的情况
        dp[0] = nums[1];
        dp[1] = Math.max(nums[1], nums[2]);
        for(int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i + 1]);
        }
        ans = Math.max(ans, dp[n - 2]);
        return ans;
    }
}
