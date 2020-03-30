public class LeetcodeJ62 {
    /* Leetcode 剑指Offer 62*/

    /**
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
     * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     */
    //直接想法：循环队列（会超时）
    //=> 正解：数学解（Josephus problem）
    //若已知f(n-1,m)，则f(n,m)的结果即为m % n后再报数f(n-1,m)个
    //由此可知：f(n,m) = (m + f(n-1,m)) % n
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int lastRemaining(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = lastRemaining(n - 1, m);
        return (m + x) % n;
    }
}
