import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Leetcode76 {
    /* LeetCode 76 最小覆盖子串 */
    // tag: Sliding Window

    /**
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     * 示例：
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     * 说明：
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minWindow("BANC", "ABC");
    }
}

// 初步想法： 通过滑动窗口法，每步将右指针向右移动一位
//  若窗口中不包含所有内容，则将窗口右指针右移，若包含全部内容，则左移，直到不包含所有内容
//  此时，记录下最短的字符串
//  然后再将右指针右移动，直到最后一次，右指针无法再右移时，返回最短子串
// 超出时间限制
// 对判断方法进行优化之后可以勉强通过时间限制
// 执行用时：272 ms, 在所有 Java 提交中击败了5.01%的用户
// 内存消耗：40.7 MB, 在所有 Java 提交中击败了28.29%的用户
// 因此这种算法仍需要进行一定的优化
class Solution {
    // 目标字符串中会存在重复值
    // 因此不能使用HashSet
    // HashSet<Character> hashSet = new HashSet<>();

    // 因为窗口中可能存在重复值
    // 需要添加一个HashMap来存储窗口中的字符个数
    HashMap<Character, Integer> count = new HashMap<>();
    // 添加一个HashMap来存储目标所需的字符个数
    HashMap<Character, Integer> target = new HashMap<>();

    public String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        String ans = "";
        int length = s.length();
        initTargetMap(t);
        while(right < length) {
            while (!containAll()) {
                if(right >= length) {
                    break;
                }
                char currChar = s.charAt(right);
                // hashSet.add(currChar);
                // getOrDefault，即若表中含有此key，则返回value，否则返回默认值（此处设为0）
                count.put(currChar, count.getOrDefault(currChar, 0) + 1);
                right ++;
            }
            while(containAll()) {
                if(right > length || left >= length) {
                    // 因为substring方法获取的子串不包含右参数对应的那个字符
                    // 所以判断条件中不加等号
                    break;
                }
                String curr = s.substring(left, right);
                if(ans.isEmpty() || curr.length() < ans.length()) {
                    // 若无答案，则直接写入
                    // 若有答案，则判断当前的是否比原有字符串短
                    ans = curr;
                }
                char leftChar = s.charAt(left);
                int num = count.getOrDefault(leftChar, 1);
                num --;
                count.put(leftChar, num);
                // if(num < 1) {   // 若删除掉的是窗口中的最后一个该字符，则从HashSet中移除该字符
                    // hashSet.remove(leftChar);
                // }
                left ++;
            }
        }
        return ans;
    }

    // 将判定算法改写为通过对哈希表的EntrySet进行遍历
    boolean containAll() {
        Iterator iterator = target.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Character key = (Character) entry.getKey();
            Integer value = (Integer) entry.getValue();
            if(count.getOrDefault(key, 0) < value) {
                return false;
            }
        }
        return true;
    }

    void initTargetMap(String t) {
        int length = t.length();
        for(int i = 0; i < length; i++) {
            char key = t.charAt(i);
            target.put(key, target.getOrDefault(key, 0) + 1);
        }
    }
}
