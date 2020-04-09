import java.util.ArrayList;
import java.util.List;

public class Leetcode22 {
    /* Leetcode 22 Generate Parentheses */

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     */
    //这题感觉之前应该做过，然后Leetcode每日一题又遇到了一次
    //这次应该是第二次做了- -但是第一时间还是没想起来该用DFS
    //感觉可能确实是DFS的运用还不怎么熟练
    //解法：回溯算法（即DFS）
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        dfs(n,n,"");
        return ans;
    }

    void dfs(int left, int right, String curr) {
        if(left == 0 && right == 0){
            ans.add(curr);
            return;
        }
        if(left > 0){
            dfs(left-1,right,curr+"(");
        }
        if(right > left){
            dfs(left,right-1,curr+")");
        }
    }
}