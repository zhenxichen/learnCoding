import java.util.*;

public class LeetCode22 {
    /* LeetCode 22 括号生成 */
    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        s.generateParenthesis(3);
    }
}

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", n, 0, 0);
        return ans;
    }

    void backtrack(List<String> ans, String curr, int n, int left, int right) {
        if (left == n && right == n) {
            ans.add(curr);
            return;
        }
        if (left < n) {
            backtrack(ans, curr + "(", n, left + 1, right);
        }
        if (left > right && right < n) {
            backtrack(ans, curr + ")", n, left, right + 1);
        }
    }
}