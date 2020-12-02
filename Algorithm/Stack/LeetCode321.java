import java.util.Stack;

public class LeetCode321 {
    /* LeetCode 321 拼接最大数 */
    /**
     * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
     * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     * 说明: 请尽可能地优化你算法的时间和空间复杂度。
     * 示例 1:
     * 输入:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * 输出:
     * [9, 8, 6, 5, 3]
     * 链接：https://leetcode-cn.com/problems/create-maximum-number
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxNumber(new int[]{6, 7}, new int[]{6, 0, 2}, 5);
        solution.maxNumber(new int[]{9,1,2,5,8,3}, new int[]{3,4,6,5}, 5);
    }
}

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        int n = nums1.length;
        int m = nums2.length;
        int start = Math.max(0, k - m);
        int end = Math.min(k, n);
        for(int i = start; i <= end; i++) {
            int k1 = i;
            int k2 = k - i;
            int[] sub1 = maxSubsequence(nums1, k1);
            int[] sub2 = maxSubsequence(nums2, k2);
            int[] sub = merge(sub1, sub2);
            if(compare(sub, ans, 0, 0)) {
                ans = sub;
            }
        }
        return ans;
    }

    int[] maxSubsequence(int[] arr, int k) {
        int n = arr.length;
        int[] stack = new int[k];       // 用数组模拟单调栈
        int top = -1;
        int remain = n - k;     // 表示最多还可以删除的数的数量
        for(int i = 0; i < n; i++) {
            while (top >= 0 && stack[top] < arr[i] && remain > 0) {
                top--;      // 出栈
                remain--;   // 剩余可删除数-1
            }
            if (top < k - 1) {      // 若栈未满
                stack[++top] = arr[i];      // 入栈
            } else {    // 若栈已满，且不符合上述出栈条件
                remain--;   // 跳过该数
            }
        }
        return stack;
    }

    int[] merge(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        int i = 0;
        int j = 0;
        int count = 0;
        int[] res = new int[n + m];
        while (i < n && j < m) {
            if(compare(arr1, arr2, i, j)) {
                res[count++] = arr1[i++];
            } else {
                res[count++] = arr2[j++];
            }
        }
        while(i < n) {
            res[count++] = arr1[i++];
        }
        while(j < m) {
            res[count++] = arr2[j++];
        }
        return res;
    }

    boolean compare(int[] arr1, int[] arr2, int i, int j) {   // 若arr1表示的数大于或等于arr2表示的数，返回true
        int n = arr1.length;
        int m = arr2.length;
        while(i < n && j < m) {
            if(arr1[i] > arr2[j]) {
                return true;
            } else if(arr1[i] < arr2[j]) {
                return false;
            } else {
                i++;
                j++;
            }
        }
        if(i < n) {  // 即arr2遍历到了最后一位
            return true;
        }
        if(j < m) {  // arr1遍历到了最后一位，而arr2没有
            return false;
        }
        return true;
    }
}
