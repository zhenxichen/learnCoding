import java.util.*;

public class LeetCode316 {
    /* LeetCode 316 去除重复字母 */

    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
     * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * 示例 1：
     * 输入：s = "bcabc"
     * 输出："abc"
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();     // 维护一个单调栈
        HashSet<Character> hashSet = new HashSet<>();       // 记录栈中有的元素（用HashSet将时间复杂度降至O(1)）
        Map<Character, Integer> last = new HashMap<>();     // 记录每个元素最后出现的位置
        for(int i = 0; i < s.length(); i++) {
            last.put(s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!hashSet.contains(c)) {  // 目前栈中暂时没有这个字符
                while (!stack.isEmpty() && c < stack.peek() &&
                        last.get(stack.peek()) > i) {
                    hashSet.remove(stack.pop());
                }
                hashSet.add(c);
                stack.add(c);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Character c: stack) {
            stringBuilder.append(c.charValue());
        }
        return stringBuilder.toString();
    }
}