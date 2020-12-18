import java.util.HashMap;
import java.util.Map;

public class LeetCode389 {
    /* LeetCode 389 找不同 */
    /**
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     */
    public static void main(String[] args) {
        Solution_HashMap solution_hashMap = new Solution_HashMap();
        Solution_Array solution_array = new Solution_Array();
        Solution_Sum solution_sum = new Solution_Sum();
        Solution solution = new Solution();
    }
}

// 解法三：位运算
// 时间复杂度：O(n) 空间复杂度：O(1)
class Solution {
    public char findTheDifference(String s, String t) {
        // 根据异或运算的性质
        // 异或运算满足交换律，且a^a=0, 0^b=b
        // 因此，最后的结果即为多出的那个数
        char ans = 0;
        for(char c: s.toCharArray()) {
            ans ^= c;
        }
        for(char c: t.toCharArray()) {
            ans ^= c;
        }
        return ans;
    }
}

// 解法二：求和
// 将两端字符串的字符之和相减，差值即为相差的那个字符（因为题目已经告知只有一处偏差）
// 时间复杂度：O(n) 空间复杂度：O(1)
// 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
// 内存消耗：36.8 MB, 在所有 Java 提交中击败了74.71%的用户
class Solution_Sum {
    public char findTheDifference(String s, String t) {
        int sum = 0;
        for(char c: s.toCharArray()) {
            sum += c;
        }
        for(char c: t.toCharArray()) {
            sum -= c;
        }
        sum = 0 - sum;
        return (char)sum;
    }
}

// 解法一优化：计数（数组）
// 因为字符串只包含大写和小写字母，因此可以用数组来进行计数
// 时间复杂度：O(n) 空间复杂度：O(1)
// 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
// 内存消耗：36.8 MB, 在所有 Java 提交中击败了82.27%的用户
class Solution_Array {
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];
        for(char c: s.toCharArray()) {
            count[c - 'a']++;
        }
        for(char c: t.toCharArray()) {
            count[c - 'a']--;
            if(count[c - 'a'] < 0) {
                return c;
            }
        }
        return '0';
    }
}

// 解法一：计数（哈希表）
// 时间复杂度：O(n) 空间复杂度：O(n)
// 执行用时：14 ms, 在所有 Java 提交中击败了10.31%的用户
// 内存消耗：36.9 MB, 在所有 Java 提交中击败了53.45%的用户
class Solution_HashMap {
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c: s.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            count++;
            map.put(c, count);
        }
        for(char c: t.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            count--;
            if(count == -1) { return c; }
            map.put(c, count);
        }
        return '0';
    }
}