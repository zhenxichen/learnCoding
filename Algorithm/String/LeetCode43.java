public class LeetCode43 {
    /* LeetCode 43 字符串相乘 */

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * 示例 1:
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 说明：
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.multiply("123", "456");
    }
}

// 解法： 数学（模拟列竖式）
// 用数组保存中间运算结果
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n = num2.length();
        int m = num1.length();
        int[] ansArr = new int[m + n];
        int carry = 0;
        // 模拟竖式运算
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int number1 = num1.charAt(j) - '0';
                int number2 = num2.charAt(i) - '0';
                int num = number1 * number2 + carry;
                carry = num / 10;
                ansArr[i + j + 1] += num % 10;
            }
            if (carry != 0) {
                ansArr[i] += carry;
                carry = 0;
            }
        }
        // 处理数组中大于10的数
        for (int i = ansArr.length - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        StringBuilder ans = new StringBuilder();
        if (ansArr[0] != 0) {
            ans.append(ansArr[0]);
        }
        for (int i = 1; i < ansArr.length; i++) {
            ans.append(ansArr[i]);
        }
        return ans.toString();
    }
}
