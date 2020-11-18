public class LeetCode55 {
    /* LeetCode 55 跳跃游戏 */

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     */

    public static void main(String[] args) {
        Solution_55 solution = new Solution_55();
        solution.canJump(new int[] {3,2,1,0,4});    //false
    }
}

class Solution_55 {
    public boolean canJump(int[] nums) {
        int maxPos = 0;
        int end = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if(i == end) {
                end = maxPos;   // 每一步走到下一步能走到最远处的位置
            }
            if(i > end) {       // 若当前位置无法从到达，直接返回false
                return false;   // 该位置无法到达，则后续肯定也无法到达
            }
        }
        return maxPos > n - 2;
    }
}
