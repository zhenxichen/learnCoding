public class Leetcode8 {
    /* Leetcode 8 String to Integer (atoi) */

    /**
     * Implement atoi which converts a string to an integer.
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
     * Then, starting from this character, takes an optional initial plus or minus sign followed by
     * as many numerical digits as possible, and interprets them as a numerical value.
     * The string can contain additional characters after those that form the integral number,
     * which are ignored and have no effect on the behavior of this function.
     * If the first sequence of non-whitespace characters in str is not a valid integral number,
     * or if no such sequence exists because either str is empty or it contains only whitespace characters,
     * no conversion is performed.
     * If no valid conversion could be performed, a zero value is returned.
     * Note:
     * Only the space character ' ' is considered as whitespace character.
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
     * [−231,  231 − 1]. If the numerical value is out of the range of representable values,
     * INT_MAX (231 − 1) or INT_MIN (−231) is returned.
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        String case1 = "4193 with words";
        System.out.println(s.myAtoi(case1));
    }
}

class Solution {
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int index = 0;
        int ans = 0;
        boolean neg = false;
        while (index < n && chars[index] == ' ') {  //丢弃无用的开头空格字符
            index++;
        }
        if (index == n) {
            return 0;
        }
        if (!Character.isDigit(chars[index])) {
            if (chars[index] == '-') {
                neg = true;
                index++;
            } else if (chars[index] == '+') {
                neg = false;
                index++;
            } else {
                return 0;
            }
        }
        for (int i = index; i < n; i++) {
            if (!Character.isDigit(chars[i])) {
                break;
            }
            int digit = chars[i] - '0';
            if (!neg && ans > (Integer.MAX_VALUE - digit) / 10) {
                return Integer.MAX_VALUE;
            }
            if (neg && ans > (Integer.MAX_VALUE - digit) / 10) {
                return Integer.MIN_VALUE;
            }
            ans = ans * 10 + digit;
        }
        return neg ? -ans : ans;
    }
}