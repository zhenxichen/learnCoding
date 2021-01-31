public class LeetCode33 {
    /* LeetCode 33 搜索旋转排序数组 */
    /**
     * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
     * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 提示：
     * · 1 <= nums.length <= 5000
     * · -10^4 <= nums[i] <= 10^4
     * · nums 中的每个值都 独一无二
     * · nums 肯定会在某个点上旋转
     * · -10^4 <= target <= 10^4
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // mid的两侧至少有一侧是有序的
            if (nums[left] <= nums[mid]) {   // 左侧有序
                if (nums[left] <= target && target <= nums[mid]) {      // 若target在左侧范围内，则在左侧查找
                    right = mid - 1;
                } else {        // 若target不在左侧的范围内，则在右侧查找
                    left = mid + 1;
                }
            } else {                         // 右侧有序
                if (nums[mid] <= target && target <= nums[right]) {     // 若target在右侧范围内，则在右侧查找
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}