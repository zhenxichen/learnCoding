public class LeetCode342 {
    /* LeetCode 342 4的幂 */
    /**
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isPowerOfFour(5));
        System.out.println(s.isPowerOfFour(16));
    }
}

class Solution {
    public boolean isPowerOfFour(int n) {
        while (n > 1) {
            if (n % 4 != 0) { return false; }
            n /= 4;
        }
        if (n == 1) {
            return true;
        }
        return false;
    }
}
