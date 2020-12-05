import java.util.ArrayList;
import java.util.List;

public class LeetCode78 {
    /* LeetCode 78 子集 */
    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * 示例:
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
}

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(nums, 0, ans, combine);
        return ans;
    }

    void dfs(int[] nums, int index, List<List<Integer>> ans,
             List<Integer> combine) {
        if(index == nums.length) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 区分添加当前数和不添加当前数的情况
        // 1. 跳过当前数
        dfs(nums, index + 1, ans, combine);
        // 2. 选择当前数
        combine.add(nums[index]);
        dfs(nums, index + 1, ans, combine);
        combine.remove(combine.size() - 1);
    }
}
