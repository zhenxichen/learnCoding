import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode46 {
    /* LeetCode 46 全排列 */
    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        dfs(nums, ans, combine, set);
        return ans;
    }

    void dfs(int[] nums, List<List<Integer>> ans,
             List<Integer> combine, Set<Integer> set) {
        if (combine.size() == nums.length) {     // 若已加满
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 将当前位置加入List
        for (int i = 0; i < nums.length; i++) {     // 循环分别遍历选择每个可选数的情况
            if (set.contains(nums[i])) {
                continue;
            }
            combine.add(nums[i]);
            set.add(nums[i]);
            dfs(nums, ans, combine, set);
            set.remove(nums[i]);
            combine.remove(combine.size() - 1);
        }
    }
}
