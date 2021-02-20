public class LeetCode69 {
    /* LeetCode 69 x 的平方根 */
    /**
     * 实现 int sqrt(int x) 函数。
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：二分搜索
class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}