import java.util.Arrays;

public class NC91 {
    /* NC 91 最长递增子序列 */
    /**
     * 给定数组arr，设长度为n，输出arr的最长递增子序列。（如果有多个答案，请输出其中字典序最小的）
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        s.LIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
    }
}

// 解法：贪心 + 二分搜索
// 时间复杂度：O(NlogN)
class Solution {
    /**
     * retrun the longest increasing subsequence
     * @param arr int整型一维数组 the array
     * @return int整型一维数组
     */
    public int[] LIS (int[] arr) {
        // write code here
        int n = arr.length;
        if (n <= 1) {
            return arr;
        }
        int[] maxLen = new int[n];      // 记录以i为结尾的元素的LIS长度
        int[] tail = new int[n];    // tail[i]表示长度为i + 1的子序列的最小的最后一位数
        tail[0] = arr[0];
        maxLen[0] = 1;
        int end = 0;        // 记录tail数组最后一个已赋值的索引
        for (int i = 1; i < n; i++) {
            if (arr[i] > tail[end]) {       // 若当前数大于最后一个索引，则直接赋值给新的索引
                tail[++end] = arr[i];       // 此时说明出现了更长的LIS
                maxLen[i] = end + 1;
            } else {        // 否则，就更新前面的tail值
                // 通过二分搜索找到第一个大于arr[i]的索引
                int left = 0, right = end;
                int index = end + 1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (tail[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        index = mid;
                        right = mid - 1;
                    }
                }
                tail[index] = arr[i];   // 更新index位置的tail值
                maxLen[i] = index + 1;
            }
        }
        int[] ans = Arrays.copyOfRange(tail, 0, end + 1);
        for (int i = arr.length-1, j = end + 1; j > 0; --i) {
            if (maxLen[i] == j) {
                ans[--j] = arr[i];
            }
        }
        return ans;
    }
}