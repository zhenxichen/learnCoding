import java.util.ArrayList;
import java.util.List;

public class LeetCode118 {
    /* LeetCode 118 杨辉三角 */
    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 示例:
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if(j == 0 || j == i) {
                    row.add(1);
                } else {
                    List<Integer> rowBefore = ans.get(i - 1);
                    row.add(rowBefore.get(j - 1) + rowBefore.get(j));
                }
            }
            ans.add(row);
        }
        return ans;
    }
}