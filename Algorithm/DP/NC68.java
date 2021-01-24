public class NC68 {
    /* NC 68 跳台阶 */
    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution solution = new Solution();
    }
}

// 优化解法：滚动数组
class Solution {
    public int JumpFloor(int target) {
        if (target == 0 || target == 1) { return 1; }
        int first = 1;
        int second = 1;
        for(int i = 2; i <= target; i++) {
            int temp = second;
            second = first + second;
            first = temp;
        }
        return second;
    }
}

// 解法一：动态规划
class Solution1 {
    public int JumpFloor(int target) {
        if (target == 0 || target == 1) { return 1; }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }
}