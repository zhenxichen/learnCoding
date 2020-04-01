import java.util.ArrayList;
import java.util.List;

public class Leetcode1111 {
    /* Leetcode 1111 Maximum Nesting Depth of Two Valid Parentheses Strings */

    /**
     * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only,
     * and:
     * It is the empty string, or
     * It can be written as AB (A concatenated with B), where A and B are VPS's, or
     * It can be written as (A), where A is a VPS.
     * We can similarly define the nesting depth depth(S) of any VPS S as follows:
     * depth("") = 0
     * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
     * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
     * For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2),
     * and ")(" and "(()" are not VPS's.
     * Given a VPS seq, split it into two disjoint subsequences A and B,
     * such that A and B are VPS's (and A.length + B.length = seq.length).
     * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
     * Return an answer array (of length seq.length) that encodes such a choice of A and B:
     * answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist,
     * you may return any of them.
     */
    //直观想法：通过栈来进行括号匹配
    //进阶：由于栈中只需要存储左括号，因此，实际上我们并不需要维护一个栈，而只需要用一个变量来模拟栈的大小即可
    //要使拆分后最大嵌套深度最小，即要使A和B的嵌套深度尽可能接近
    //=> 将括号按嵌套深度奇偶来进行分配(?)
    //其实这题为什么要通过深度奇偶性来进行分配我确实是不太懂，只能当作一种进行平分的常用策略来理解了吗？
    //不过确实理解了的一点是通过深度来进行分配确实是可以保证括号的有效性的
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        // List<Integer> ans = new ArrayList<>(); 不应使用List 因为toArray只能转为Integer数组，无法转为int数组
        int n = seq.length();
        int[] ans = new int[n];
        int depth = 0;
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                ans[i] = depth % 2;     //奇数则分配给1，偶数则分配给0
            } else {
                ans[i] = depth % 2;
                depth--;
            }
        }
        return ans;
    }
}
