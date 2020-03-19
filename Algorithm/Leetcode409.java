import java.util.HashMap;

public class Leetcode409 {
    /* Leetcode 409 Longest Palindrome */
    /**
     * Given a string which consists of lowercase or uppercase letters,
     * find the length of the longest palindromes that can be built with those letters.
     * This is case sensitive, for example "Aa" is not considered a palindrome here.
     * Note:
     * Assume the length of given string will not exceed 1,010.
     */
    //个人想法：用哈希表存储各个字母的数量
    //最终结果即为所有偶数值+最大奇数
    //第一次错误，问题所在：并不是没有采用的奇数就一定要全部舍弃，可以舍去一个剩下当成偶数
    Solution1 s1 = new Solution1();
    //改进算法：改用数组进行存储
    Solution s = new Solution();
}


class Solution {    //2ms
    public int longestPalindrome(String s) {
        //看了题解,题解运用的是贪心算法，我这边没有用贪心，但是借鉴了题解中运用数组存储的方式
        int[] count = new int[128];     //改用数组存储（大幅提高运行效率）
        int max = 0;
        boolean odd = false;
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        for (int i = 65; i < 91; i++) {
            if (count[i] % 2 == 0) {
                max += count[i];
            } else {
                odd = true;
                max += (count[i] - 1);
            }
        }
        for (int i = 97; i < 123; i++) {
            if (count[i] % 2 == 0) {
                max += count[i];
            } else {
                odd = true;
                max += (count[i] - 1);
            }
        }
        if (odd) {
            max += 1;
        }
        return max;
    }
}


class Solution1 {       //21ms
    //第一次做的方法，使用哈希表来存储各个字母的数量
    //运行效率较低
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int len = s.length();
        int max = 0;
        boolean haveOdd = false;
        for (int i = 0; i < len; i++) {
            char key = s.charAt(i);
            if (map.get(key) == null) {
                map.put(key, 0);
            }
            int value = map.get(key);
            value++;
            map.remove(key);
            map.put(key, value);
        }
        for (Integer value : map.values()) {
            if (value % 2 == 0) {
                max += value;
            } else {
                haveOdd = true;
                max += value - 1;
            }
        }
        if (haveOdd) {
            max++;
        }
        return max;
    }
}
