public class LeetCode34 {
    /* LeetCode 34 在排序数组中查找元素的第一个和最后一个位置 */
    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
     * 找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 进阶：
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n == 0) { return new int[] {-1, -1}; }
        int left = 0;
        int right = n - 1;
        int mid = 0;
        int lower = -1;
        int higher = -1;
        // 求第一个位置
        while(left <= right) {
            mid = (left + right) / 2;
            if(nums[mid] == target) {
                right = mid - 1;
                lower = mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 求第二个位置
        left = 0;
        right = n - 1;
        while(left <= right) {
            mid = (left + right) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] == target) {
                left = mid + 1;
                higher = mid;
            } else {
                left = mid + 1;
            }
        }
        return new int[] {lower, higher};
    }
}