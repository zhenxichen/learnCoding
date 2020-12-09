import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LeetCode47 {
    /* LeetCode 47 全排列 II */
    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> state = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums);      // 需要进行排序，将相同数字放在一起
        dfs(ans, state, nums, set);
        return ans;
    }

    void dfs(List<List<Integer>> ans, List<Integer> state,
             int[] nums, HashSet<Integer> set) {
        int n = nums.length;
        if (state.size() == n) {
            ans.add(new ArrayList<>(state));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }
            // 为了不重复，我们要求相同数字的选择顺序与数组中的顺序相同
            if (i > 0 && nums[i] == nums[i - 1] && !set.contains(i - 1)) {
                continue;
            }
            state.add(nums[i]);
            set.add(i);
            dfs(ans, state, nums, set);
            set.remove(i);
            state.remove(state.size() - 1);
        }
    }
}
