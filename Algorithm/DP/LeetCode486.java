import java.util.Arrays;

public class LeetCode486 {
    /* LeetCode 486 预测赢家 */

    /**
     * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，
     * 然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。
     * 直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
     * 提示：
     * 1 <= 给定的数组长度 <= 20.
     * 数组里所有分数都为非负数且不会大于 10000000 。
     * 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 解法：动态规划
// dp(i, j)表示数组仅剩(i,j)段时，先手方与后手方的分差
// dp(i, j) = max{nums[i]-dp(i+1,j), nums[j]-dp(i,j-1)}
// 可优化：可将二维dp优化为一维dp
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j],
                        nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}
