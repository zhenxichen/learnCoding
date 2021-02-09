import java.util.*;

public class NC41 {
    /* NC 41 找到数组最长的无重复子数组 */
    /**
     * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：滑动窗口 + HashSet
class Solution {
    /**
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxLength (int[] arr) {
        // write code here
        Set<Integer> set = new HashSet<>();
        int maxLength = 1;
        int n = arr.length;
        int left = 0, right = 0;    // 滑动窗口的左右指针
        while (right < n - 1) {
            int next = arr[++right];
            while (set.contains(next)) {
                set.remove(arr[left++]);
            }
            set.add(next);
            maxLength = Math.max(right - left + 1, maxLength);
        }
        return maxLength;
    }
}