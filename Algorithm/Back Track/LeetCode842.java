import java.util.ArrayList;
import java.util.List;

public class LeetCode842 {
    /* LeetCode 842 将数组拆分成斐波那契序列 */
    /**
     * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
     * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
     * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
     * F.length >= 3；
     * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
     * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
     * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
     * 示例 1：
     * 输入："123456579"
     * 输出：[123,456,579]
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 题目与之前做的几种回溯算法的主要区别是
// 只需要返回一种情况
// 因此，我们可以选用带boolean返回值backtrack
class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        return backtrack(0, S, ans) ? ans: new ArrayList<>();
    }

    boolean backtrack(int index, String s, List<Integer> ans) {
        int n = s.length();
        int m = ans.size();
        if (index == n) {
            return ans.size() >= 3;
        }
        long numL = 0;
        for (int i = index; i < n; i++) {   // 向后遍历
            if (i > index && s.charAt(index) == '0') {   // 长度不为1且首位为0
                return false;
            }
            numL = numL * 10 + Integer.valueOf(s.substring(i, i + 1));
            if (numL > Integer.MAX_VALUE) {  // 判断是否溢出
                return false;   // 若当前数溢出，往后遍历也必定溢出，因此直接返回false
            }
            int num = (int) numL;
            if (m < 2 || numL == ans.get(m - 1) + ans.get(m - 2)) {
                ans.add(num);
                if (backtrack(i + 1, s, ans)) {     // 若继续遍历可以生成Fibonacci数组
                    return true;    // 则返回该数组
                }
                ans.remove(ans.size() - 1);     // 否则，删除该数，继续进行遍历
            }
        }
        return false;
    }
}