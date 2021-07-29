public class LeetCode136 {
    /* LeetCode 136 只出现一次的数字 */
    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     */

    public static class Solution {
        public int singleNumber(int[] nums) {
            // 将所有数进行异或运算，两个相同的数会相互抵消，最终剩余的就是只出现一次的数
            int ans = nums[0];
            for (int i = 1; i < nums.length; i++) {
                ans ^= nums[i];
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.singleNumber(new int[]{2, 2, 1});     // should be 1
    }
}
