import java.util.ArrayList;
import java.util.List;

public class LeetCode228 {
    /* LeetCode 228 汇总区间 */

    /**
     * 给定一个无重复元素的有序整数数组 nums 。
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。
     * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     * 示例 1：
     * 输入：nums = [0,1,2,4,5,7]
     * 输出：["0->2","4->5","7"]
     * 解释：区间范围是：
     * [0,2] --> "0->2"
     * [4,5] --> "4->5"
     * [7,7] --> "7"
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        int begin = nums[0];
        int end = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                end = nums[i];
            } else {
                if (begin == end) {
                    ans.add(String.valueOf(begin));
                } else {
                    String str = begin + "->" + end;
                    ans.add(str);
                }
                begin = nums[i];
                end = nums[i];
            }
        }
        if (begin == end) {
            ans.add(String.valueOf(begin));
        } else {
            ans.add(begin + "->" + end);
        }
        return ans;
    }
}
