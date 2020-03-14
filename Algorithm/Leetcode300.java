import java.util.Arrays;

public class Leetcode300 {
    /* Leetcode 300 Longest Increasing Subsequence */

    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     * Note:
     * There may be more than one LIS combination, it is only necessary for you to return the length.
     * Your algorithm should run in O(n2) complexity.
     */
    public static void main(String[] args) {
        //这题之前应该确实是用C++做过，然后还记得大致的方向应该是DP
        //不过具体的就不怎么记得了
        //然后这次又刚好在Leetcode的每日一题上刷到了，而且刚好又很久没写JAVA了感觉（前几天都在写前端）
        // 所以我觉得确实可以再做一下
        Solution solution = new Solution();
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        solution.lengthOfLIS(arr);
    }
}

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];          //dp[i]表示以第i个字符为结尾的LIS
        int ret = 0;
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {       //dp[n-1]不一定是最大值
            if (dp[i] > ret) {
                ret = dp[i];
            }
        }
        return ret;
    }
}