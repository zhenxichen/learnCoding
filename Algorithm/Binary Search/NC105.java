public class NC105 {
    /* NC 105 二分查找 */
    /**
     * 请实现有重复数字的升序数组的二分查找。
     * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
     * 示例 1:
     * 输入: 5,4,[1,2,4,4,5]
     * 返回值: 3
     * 说明: 输出位置从1开始计算
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        int ans = s.upper_bound_(10, 7, new int[]{1, 3, 4, 4, 6, 6, 6, 7, 8, 11});
        System.out.println(ans);
    }
}

class Solution {
    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public int upper_bound_ (int n, int v, int[] a) {
        // write code here
        int left = 0, right = n - 1;
        int upper = -1;         // 标记比v大的数的位置
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] > v) {
                right = mid - 1;
                upper = mid;
            } else if (a[mid] < v) {
                left = mid + 1;
            } else {
                while (a[mid] == a[mid - 1]) { mid--; }
                return mid + 1;
            }
        }
        while (upper > 0 && a[upper] == a[upper - 1]) {
            upper--;
        }
        return upper >= 0 ? upper + 1: n + 1;
    }
}