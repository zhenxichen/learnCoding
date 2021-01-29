public class LeetCode1014 {
    /* LeetCode 1014 最佳观光组合 */
    /**
     * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
     * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
     * 返回一对观光景点能取得的最高分。
     * 提示：
     * 1. 2 <= A.length <= 50000
     * 2. 1 <= A[i] <= 1000
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int max = A[0] + 0;     // 在遍历的过程中，只需要贪心地记录j之前出现的最大的A[i]+i
        int ans = 0;
        for (int j = 1; j < A.length; j++) {
            ans = Math.max(ans, max + A[j] - j);
            max = Math.max(max, A[j] + j);
        }
        return ans;
    }
}