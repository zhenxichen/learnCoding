public class LeetCode978 {
    /* LeetCode 978 最长湍流子数组 */
    /**
     * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
     * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
     * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     * 返回 A 的最大湍流子数组的长度。
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9});
        Solution solution = new Solution();
    }
}

// 优化解法：一次遍历
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int max = 1;
        int begin = 0;
        for(int i = 1; i < n; i++) {
            // Integer.compare() 若前者小于后者，返回-1，相等返回0，前者大于后者返回1
            int compare = Integer.compare(arr[i - 1], arr[i]);
            // 若满足条件，则继续扩展块
            if (i == n - 1 || compare * Integer.compare(arr[i], arr[i + 1]) >= 0) {
                if(compare != 0) {      // 防止arr[1]和arr[0]相等造成的错误
                    max = Math.max(max, i - begin + 1); // 如[9, 9] [9, 9, 9]等
                }
                begin = i;  // 否则，将当前块记录，然后以该点为开头尝试新的块
                // 需要注意的是，这里以i为开头，而非i+1
                // 因为可能存在持续单调的情况，如7 6 5
            }
        }
        return max;
    }
}

// 解法一：两次遍历
class Solution1 {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int i = 0;      // 左指针
        int j = 0;      // 右指针
        int max = 1;
        // 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]
        while(j < n - 1) {
            if((j % 2 == 0 && arr[j] < arr[j + 1]) ||
                    (j % 2 == 1 && arr[j] > arr[j + 1])) {
                j++;
                max = Math.max(max, j - i + 1);
            } else {
                i = j + 1;
                j = i;
            }
        }
        // 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]
        i = 0;
        j = 0;
        while(j < n - 1) {
            if((j % 2 == 1 && arr[j] < arr[j + 1]) ||
                    (j % 2 == 0 && arr[j] > arr[j + 1])) {
                j++;
                max = Math.max(max, j - i + 1);
            } else {
                i = j + 1;
                j = i;
            }
        }
        return max;
    }
}
