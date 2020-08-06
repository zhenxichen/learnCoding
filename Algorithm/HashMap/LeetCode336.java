import java.util.*;

public class LeetCode336 {
    /* LeetCode 336 回文对 */
    // tags: 哈希表 字典树 manacher算法

    /**
     * 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     * 示例 1：
     * 输入：["abcd","dcba","lls","s","sssll"]
     * 输出：[[0,1],[1,0],[3,2],[2,4]]
     * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"});
    }
}

// 解法：哈希表 枚举前后缀
// 另解：字典树 manacher (以后再做)
class Solution {
    List<String> wordsRev = new ArrayList<>();
    Map<String, Integer> indices = new HashMap<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        int length = words.length;
        for (int i = 0; i < length; i++) {
            wordsRev.add(new StringBuffer(words[i]).reverse().toString());
        }
        for (int i = 0; i < length; i++) {
            indices.put(wordsRev.get(i), i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int n = word.length();
            if (n == 0) {
                continue;
            }
            for (int j = 0; j <= n; j++) {      // 因为需要考虑完整的字符串，因此需要考虑j = n
                // 遍历当前字符串的所有子串
                // 若左侧子串与已有字符串构成回文，则当前字符串放在左侧（如： tab|aa bat）(|表示j分割)
                // 若右侧子串与已有字符串构成回文，则当前字符串放在右侧 (如：tab bb|bat)
                // 因为我们已经将所有字符串存入表中，所以只需要考虑对长的字符串取子串然后进行比对
                // 而不需要对相对较短的字符串进行操作
                if (isPalindrome(word, j, n - 1)) {     // 若当前字符串中有部分满足回文
                    // 则将剩余部分与已有的字符串进行比对
                    // 当j = n时，则为将整个字符串与已有字符串进行比对
                    int right = findWord(word, 0, j - 1);
                    if (right != -1 && right != i) {
                        ans.add(Arrays.asList(i, right));   // i放在左边，right为返回的对应右侧字符串位置
                   }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    int left = findWord(word, j, n - 1);
                    if(left != -1 && left != i) {
                        ans.add(Arrays.asList(left, i));
                    }
                }
            }
        }
        return ans;
    }

    public boolean isPalindrome(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    public int findWord(String s, int left, int right) {
        return indices.getOrDefault(s.substring(left, right + 1), -1);
    }
}
