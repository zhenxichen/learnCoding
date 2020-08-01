import java.util.HashSet;

public class Leetcode3 {
    /* LeetCode 3 无重复字符的最长子串 */
    // tag: Sliding Window

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.lengthOfLongestSubstring("abcabcbb");      // Use Case 1
    }
}

// 通过滑动窗口，每轮将右指针向右移动一位
// 若右指针与窗口内字符重复，则将左指针右移，直到不重复为止
// 判断重复的方法：通过HashSet
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;      // 当前为止的最大长度
        HashSet<Character> hashSet = new HashSet<>();
        int left = 0;
        for (int i = 0; i < s.length(); i++) {     // i表示右指针
            char c = s.charAt(i);
            if (hashSet.contains(c)) {
                // 滑动窗口的前提是该窗口在先前情况下没有重复
                // 因此直接从HashSet中去掉左指针对应的字符即可
                while(hashSet.contains(c)) {
                    hashSet.remove(s.charAt(left));
                    left ++;
                }
                hashSet.add(c);
            } else {
                hashSet.add(c);
                int currLength = i - left + 1;
                maxLength = maxLength < currLength ? currLength : maxLength;
            }
        }
        return maxLength;
    }
}
