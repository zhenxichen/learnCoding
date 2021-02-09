public class NC19 {
    /* NC 19 子数组的最大累加和问题 */
    /**
     * 给定一个数组arr，返回子数组的最大累加和
     * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
     * 题目保证没有全为负数的数据
     * [要求]
     * 时间复杂度为O(n)，空间复杂度为O(1)
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：一维DP（滚动数组）
class Solution {
    /**
     * max sum of the subarray
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxsumofSubarray (int[] arr) {
        // write code here
        int maxSum = Integer.MIN_VALUE;        // 存储全局最大和
        int max = Integer.MIN_VALUE;        // 用来存储以当前位置为结尾的子数组的最大和
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(max + arr[i], arr[i]);
            maxSum = Math.max(maxSum, max);
        }
        return maxSum;
    }
}