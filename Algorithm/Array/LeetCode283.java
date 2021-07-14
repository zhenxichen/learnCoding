import java.util.Arrays;

public class LeetCode283 {
    /* LeetCode 283 移动零 */
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * 1. 必须在原数组上操作，不能拷贝额外的数组。
     * 2. 尽量减少操作次数。
     */

    // 解法：双指针
    static class Solution {
        public void moveZeroes(int[] nums) {
            int n = nums.length;
            int left = 0;       // 左指针指向当前已经处理好的序列
            int right = 0;      // 右指针不断向右移动
            while (right < n) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ans = new int[]{0,1,0,3,12};
        s.moveZeroes(ans);               // should be [1,3,12,0,0]
        System.out.println(Arrays.toString(ans));
        ans = new int[]{1};
        s.moveZeroes(ans);
        System.out.println(Arrays.toString(ans));
    }
}
