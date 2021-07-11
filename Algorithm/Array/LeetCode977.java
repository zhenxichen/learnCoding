import java.util.Arrays;

public class LeetCode977 {
    /* LeetCode 977 有序数组的平方 */
    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * 示例 1：
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     * 示例 2：
     * 输入：nums = [-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     */
    // 解法：双指针
    // 时间复杂度：O(n)
    public static class Solution {
        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            int left = -1, right = n;
            // 将左指针移动到最后一个负数，右指针移动到第一个非负数
            for (int i = 0; i < n; i++) {
                if (nums[i] < 0) {
                    left = i;
                } else {
                    right = i;
                    break;
                }
            }
            // 将两个指针分别向两侧移动，依次序填充新数组
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                if (left < 0) {
                    ans[i] = nums[right] * nums[right];
                    right++;
                    continue;
                }
                if (right >= n) {
                    ans[i] = nums[left] * nums[left];
                    left--;
                    continue;
                }
                if (-nums[left] < nums[right]) {
                    ans[i] = nums[left] * nums[left];
                    left--;
                } else {
                    ans[i] = nums[right] * nums[right];
                    right++;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ans1 = s.sortedSquares(new int[]{-4,-1,0,3,10});  // should be [0,1,9,16,100]
        System.out.println(Arrays.toString(ans1));
        int[] ans2 = s.sortedSquares(new int[]{-5,-3,-2,-1});   // should be [1,4,9,25]
        System.out.println(Arrays.toString(ans2));
    }
}
