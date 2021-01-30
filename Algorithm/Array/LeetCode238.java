public class LeetCode238 {
    /* LeetCode 238 除自身以外数组的乘积 */
    /**
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output,
     * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     */
    // 比较直观的解法是首先获得所有数的乘积，然后除以对应位置的数即可
    // 但是题目不允许使用除法
    // 因此需要思考其他解法
    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        s1.productExceptSelf(new int[]{1, 2, 3, 4});
        Solution s = new Solution();
    }
}

// 优化解法：用输出数组来表示L R
// 时间复杂度：O(n)  1 ms
// 空间复杂度：不包括输出数组，则为O(1)  49.2 MB
// 内存消耗反而更大了hhh
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        // 先乘入前缀数乘积
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        // 再乘入后缀数乘积
        int post = 1;
        for (int j = n - 1; j >= 0; j--) {
            ans[j] *= post;
            post *= nums[j];
        }
        return ans;
    }
}

// 解法一：左右侧前缀数组
// 时间复杂度：O(n)  2 ms
// 空间复杂度：O(n)  48.4 MB
class Solution1 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] L = new int[n];       // 记录每个位置的前缀乘积
        int[] R = new int[n];       // 记录每个位置的后缀乘积
        L[0] = 1;
        R[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        for (int j = n - 2; j >= 0; j--) {
            R[j] = nums[j + 1] * R[j + 1];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = L[i] * R[i];
        }
        return nums;
    }
}