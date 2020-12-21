public class LeetCode746 {
    /* LeetCode 746 使用最小花费爬楼梯 */
    /**
     * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     * 示例 1:
     * 输入: cost = [10, 15, 20]
     * 输出: 15
     * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
     * 注意：
     * 1. cost 的长度将会在 [2, 1000]。
     * 2. 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int ans = solution1.minCostClimbingStairs(new int[]{0, 1, 1, 0});
        System.out.println(ans);
        Solution solution = new Solution();
    }
}

// 优化：动态优化（滚动数组）
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int first = cost[0];
        int second = cost[1];
        for(int i = 2; i < n; i++) {
            int curr = Math.min(first, second) + cost[i];
            first = second;
            second = curr;
        }
        return Math.min(first, second);
    }
}

// 解法：动态规划
class Solution1 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
        return Math.min(dp[n - 2], dp[n - 1]);
    }
}
