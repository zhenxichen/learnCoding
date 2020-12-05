import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode39 {
    /* LeetCode 39 组合总和 */
    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
    }
}

// 优化解法：回溯算法 + 剪枝
// 1. 如果target减去一个数为负数，那么减去比他大的数也一定为负数
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, combine, ans, 0);
        return ans;
    }

    void dfs(int[] candidates, int target, List<Integer> combine,
             List<List<Integer>> ans, int index) {
        if (index == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        if (target - candidates[index] < 0) {   // 剪枝，若当前小于0，则之后肯定也小于0
            return;
        }
        // 跳过当前
        dfs(candidates, target, combine, ans, index + 1);
        // 选择当前
        combine.add(candidates[index]);
        dfs(candidates, target - candidates[index],
                combine, ans, index);
        combine.remove(combine.size() - 1);
    }
}

// 解法一：回溯算法，无剪枝
class Solution1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, combine, ans, 0);
        return ans;
    }

    void dfs(int[] candidates, int target, List<Integer> combine,
             List<List<Integer>> ans, int index) {
        if(index == candidates.length) {
            return;
        }
        if(target == 0) {
            ans.add(new ArrayList<>(combine));      // 这里需要新建一个对象，因为后面会删除之前combine中的数
            return;
        }
        // 分两种情况搜索：跳过当前和选择当前
        // 1. 跳过当前
        dfs(candidates, target, combine, ans, index + 1);
        // 2. 选择当前
        if(target >= candidates[index]) {
            combine.add(candidates[index]);
            // 为防止重复的情况(如[2, 3, 2]和[2, 2, 3])，按顺序遍历
            // 每轮搜索只搜索index之后的节点
            dfs(candidates, target - candidates[index], combine, ans, index);
            combine.remove(combine.size() - 1);     // 删除进行的尝试（如果符合也已经加入到了ans中）
        }
    }
}
