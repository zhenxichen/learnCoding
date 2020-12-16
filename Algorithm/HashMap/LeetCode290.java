import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class LeetCode290 {
    /* LeetCode 290 单词规律 */
    /**
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
     * 示例 1:
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     * 示例 2:
     * 输入: pattern = "abba", str = "dog cat cat fish"
     * 输出: false
     * 示例 3:
     * 输入: pattern = "abba", str = "dog dog dog dog"
     * 输出: false
     * （注意：字符串和单词必须一一对应）
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.wordPattern("abba", "dog dog dog dog"));    // false
        solution.wordPattern("abba", "dog cat cat dog");    // true
        solution.wordPattern("jquery", "jquery");
    }
}

class Solution {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        int i = 0;
        for (char c : pattern.toCharArray()) {
            if (i > s.length()) {      // 说明pattern的字符数大于s的单词数
                return false;
            }
            int j = i;
            while (j < s.length() && s.charAt(j) != ' ') {
                j++;
            }
            String str = s.substring(i, j);
            if (!map.containsKey(c)) {
                map.put(c, str);
            } else {
                if (!map.get(c).equals(str)) {
                    return false;
                }
            }
            if (!map2.containsKey(str)) {
                map2.put(str, c);
            } else {
                if (map2.get(str).charValue() != c) {
                    return false;
                }
            }
            i = j + 1;
        }
        if (i < s.length() + 1) {  // 说明s的单词数多于pattern的字符数
            return false;
        }
        return true;
    }
}