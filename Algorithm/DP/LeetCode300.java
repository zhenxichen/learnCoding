public class LeetCode300 {
    /* LeetCode 300 最长上升子序列 */

    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * 示例:
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     * 1. 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 2. 你算法的时间复杂度应该为 O(n2) 。
     * Follow up: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }
}

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        int ans = 0;
        int[] dp = new int[n];      //dp表示以i为末尾的LIS长度
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}