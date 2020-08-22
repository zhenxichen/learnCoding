public class LeetCodeM16_16 {
    /* LeetCode 面试题 16.16. 部分排序 */
    /**
     * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
     * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，
     * 若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public int[] subSort(int[] array) {
        int n = array.length;
        boolean[] dp = new boolean[n];      // 记录这个数是否处于它正确的位置（大于所有前面的数，小于所有后面的数）
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, array[i]);
            dp[i] = (max <= array[i]);      // 若i前面有比他大的数，设dp[i]为false
        }
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, array[i]);
            dp[i] = (dp[i] && (min >= array[i]));        // 若i后面有比他小的数，设dp[i]为false
        }
        int begin = -1;
        int end = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] == false) {
                begin = i;
                break;
            }
        }
        for (int j = n - 1; j >= 0; j--) {
            if (dp[j] == false) {
                end = j;
                break;
            }
        }
        return new int[]{begin, end};
    }
}