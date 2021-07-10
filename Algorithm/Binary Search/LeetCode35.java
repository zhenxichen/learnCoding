public class LeetCode35 {
    /* LeetCode 35 搜索插入位置 */
    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     * 注：题目中未注明，但判题中要求相同数字插入在最前面。
     */

    public static class Solution {
        public int searchInsert(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            int ans = 0;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                // 由于实际要求相同数字插入在最前面，因此这里不能加=
                // 否则会导致ans记录到后面的坐标
                if (nums[mid] < target) {
                    // 由于ans的默认值是0，需要在小于target时进行记录
                    // 这样，没有记录到任何小于target的值时返回0
                    ans = mid + 1;
                    left = mid + 1;
                } else {
                    // 如果要在这一分支中记录ans，则应该将ans的初始值记录为n
                    // ans = mid;
                    right = mid - 1;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int ans1 = s.searchInsert(new int[]{1,3,5,6}, 2);   // should be 1
        System.out.println(ans1);
        int ans2 = s.searchInsert(new int[]{1,3,5,6}, 5);   // should be 2
        System.out.println(ans2);
        int ans3 = s.searchInsert(new int[]{1,3,5,6}, 7);   // should be 4
        System.out.println(ans3);
    }
}
