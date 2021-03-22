public class LeetCode191 {
    /* LeetCode 191 位1的个数 */
    /**
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
     */
    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        Solution s = new Solution();
    }
}

// 解法二：位运算优化
// 可以通过n & (n - 1) 来获得将最低位的1变为0的结果
class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}

// 解法一：逐位检查
class Solution1 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }
}