public class LeetCode31 {
    /* LeetCode 31 Next Permutation */

    /**
     * Implement next permutation, which rearranges numbers into the lexicographically
     * next greater permutation of numbers.
     * If such an arrangement is not possible,
     * it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
     * The replacement must be in place and use only constant extra memory.
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nextPermutation(new int[]{5, 4, 7, 5, 3, 2});
    }
}

// 解法：
// 1. 找到a[k]，使得k是最大的满足a[k]<a[k+1]的索引，若不存在，则直接翻转整个数组
// 2. 找到a[j]，使得j是最大的满足a[j]>a[k]的索引
// 3. 若找到j，则叫唤a[k]和a[j]，并翻转nums[k:]
class Solution {
    // the replacement must be in place
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
