import java.util.*;

public class LeetCode49 {
    /* LeetCode 49 字母异位词分组 */
    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution2 solution2 = new Solution2();
    }
}

// 解法：字符串排序+哈希表
// 时间复杂度：O(nklogk) n: 字符串数量 k:字符串最大长度
// 空间复杂度：O(nk)
// 执行用时：6 ms, 在所有 Java 提交中击败了98.81%的用户
// 内存消耗：41.2 MB, 在所有 Java 提交中击败了92.84%的用户
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();   // 由于数组没有重写hashcode()和equals()方法
            Arrays.sort(charArray);                 // 因此不能作为HashMap的key
            String sorted = new String(charArray);  // String的构造方法可用char数组构造String
            List<String> list = map.getOrDefault(sorted, null);
            if (list == null) {
                list = new ArrayList<>();
                list.add(str);      // 插入未排序过的字符串
                map.put(sorted, list);
                ans.add(list);
            } else {
                list.add(str);
            }
        }
        return ans;
    }
}

// 特殊解法：质数相乘
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<Long, List<String>> map = new HashMap<>();
        int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
                47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        // 不同质数的乘积必定是不同结果，因此可用作key
        for (String str : strs) {
            Long multiply = 1L;
            for (char c : str.toCharArray()) {
                multiply *= primes[c - 'a'];
            }
            List<String> list = map.getOrDefault(multiply, null);
            if (list == null) {
                list = new ArrayList<>();
                list.add(str);      // 插入未排序过的字符串
                map.put(multiply, list);
                ans.add(list);
            } else {
                list.add(str);
            }
        }
        return ans;
    }
}