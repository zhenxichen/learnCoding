public class NC1 {
    /* NC 1 大数加法 */
    /**
     * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
     * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        // write code here
        int m = s.length();
        int n = t.length();
        s = new StringBuffer(s).reverse().toString();
        t = new StringBuffer(t).reverse().toString();
        if (m < n) {
            for (int i = m; i < n; i++) {
                s += "0";
            }
            m = n;
        } else {
            for (int i = n; i < m; i++) {
                t += "0";
            }
            n = m;
        }
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int sum = Character.getNumericValue(s.charAt(i)) + Character.getNumericValue(t.charAt(i));
            sum += carry;
            carry = sum / 10;
            sum = sum % 10;
            sb.append(sum);
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}