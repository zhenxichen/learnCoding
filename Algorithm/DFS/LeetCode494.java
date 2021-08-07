public class LeetCode494 {
    /* LeetCode 494 目标和 */
    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     */
    // Shopee一面（8.7）面试算法题，有DFS和DP两种解法（面试上写出的是DFS解）

    // 解法一：DFS
    // 时间复杂度：O(2^n)
    class SolutionDFS {
        int count;
        int target;

        public int findTargetSumWays(int[] nums, int target) {
            count = 0;
            this.target = target;
            dfs(nums, 0, 0);
            return count;
        }

        private void dfs(int[] arr, int index, int sum) {
            if (index == arr.length) {
                if (sum == target) {
                    count++;
                }
                return;
            }
            dfs(arr, index + 1, sum + arr[index]);
            dfs(arr, index + 1, sum - arr[index]);
        }
    }

    // 解法二：DP
    class SolutionDP {
        public int findTargetSumWays(int[] nums, int target) {
            // 记录数组元素和为sum，负数元素和为neg
            // 则有 (sum-neg)-neg = target
            // 可得 neg = (sum - target) / 2
            int n = nums.length;
            // 求和计算所有元素的和与目标值的差值
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += nums[i];
            }
            int diff = (int) (sum - target);
            // 如果差值小于0则无法达到，而差值为奇数，则neg非整数，也无法达到
            if (diff < 0 || diff % 2 != 0) {
                return 0;
            }
            int neg = diff / 2;
            // 求出neg后，问题就简化为选n个元素，使其和为neg
            // 定义dp数组， dp[i][j]表示在前i个元素中选取元素，使结果为j的方案数
            // 如果 j < nums[i]，则不能选nums[i]，dp[i][j] = dp[i - 1][j]
            // 如果 j >= nums[i]，则考虑选择和不选择两种情况
            // dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]]
            int[][] dp = new int[n + 1][neg + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= neg; j++) {
                    if (j < nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    }
                }
            }
            return dp[n][neg];
        }
    }
}
