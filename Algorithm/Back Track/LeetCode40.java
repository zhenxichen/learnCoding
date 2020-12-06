import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode40 {
    /* LeetCode 40 组合总和 II */
    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * 说明：
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
    }
}

// 思路与LeetCode 39基本一致
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, ans, combine);
        return ans;
    }

    void dfs(int[] candidates, int target, int index,
             List<List<Integer>> ans, List<Integer> combine) {
        if(target == 0) {   // 先判断是否符合要求，再判断是否超界
            ans.add(new ArrayList<>(combine));
            return;
        }
        if(index == candidates.length) {    // 否则最大一个数等于target时会遗漏单选这个数的情况
            return;
        }
        if(target < 0) {
            return;
        }
        for(int i = index; i < candidates.length; i++) {    // 表示以第i个数开始遍历
            // 在这一轮进行dfs则说明选择了第i个数，而往下一轮遍历则是考虑没选择这个数的情况
            if(candidates[i] > target) {    // 剪枝
                break;
            }
            if(i > index && candidates[i] == candidates[i - 1]) {   // 防止出现重复结果
                continue;
            }
            combine.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1,
                    ans, combine);
            combine.remove(combine.size() - 1);
        }
    }
}