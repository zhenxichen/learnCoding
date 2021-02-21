import java.util.*;

public class NC121 {
    /* NC 121 字符串的排列 */
    /**
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     */
    //
    // 注：本题是LeetCode 47的升级版
    // 题目链接：https://leetcode-cn.com/problems/permutations-ii/
    // 解法链接：https://github.com/xuanqizi/learnCoding/blob/master/Algorithm/Back%20Track/LeetCode47.java
    //
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> ans = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return ans;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        char[] strArray = str.toCharArray();
        Arrays.sort(strArray);
        backtrack(strArray, ans, stringBuilder, set);
        Collections.sort(ans);
        return ans;
    }

    public void backtrack(char[] str, ArrayList<String> ans,
                          StringBuilder sb, Set<Integer> set) {
        int n = str.length;
        if (sb.length() == n) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }
            if (i > 0 && str[i] == str[i - 1] && !set.contains(i - 1)) {
                // 为了避免重复，需要保证相同字母的顺序与字符数组中相同
                continue;
            }
            // 此处分为两种分支，一种是进行下列操作（将当前字符加入字符串）
            // 另一种是进入下一次循环，而没有加入当前字符
            set.add(i);
            sb.append(str[i]);
            backtrack(str, ans, sb, set);
            sb.delete(sb.length() - 1, sb.length());
            set.remove(i);
        }
    }
}