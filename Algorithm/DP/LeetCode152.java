public class LeetCode152 {
    /* LeetCode 152 乘积最大子数组 */
    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 示例 1:
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     */
    public static void main(String[] args) {

    }
}

// 优化：滚动数组
// 我们发现，i的值只与i-1有关，且不需要其他历史数据
// 因此，我们可以只存储i和i-1的数据
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        int maxF = nums[0];     // 计算i为结尾的子数组最大值
        int minF = nums[0];
        int max = nums[0];
        for(int i = 1; i < n; i++) {
            int min_before = minF;      // 存储i-1的数据
            int max_before = maxF;
            maxF = Math.max(max_before * nums[i], Math.max(min_before * nums[i], nums[i])); // 计算当前i的数据
            minF = Math.min(min_before * nums[i], Math.min(max_before * nums[i], nums[i]));
            max = Math.max(max, maxF);
        }
        return max;
    }
}

// 解法：动态规划
// 需要同时保存最大值和最小值，因为最小值乘以一个负数可能变为最大值
class Solution1 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0) { return 0; }
        int[] maxF = new int[n];    // 记录以i为结尾的子数组最大值
        int[] minF = new int[n];
        int max = nums[0];
        maxF[0] = nums[0];
        minF[0] = nums[0];
        for(int i = 1; i < n; i++) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(minF[i - 1] * nums[i], nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(maxF[i - 1] * nums[i], nums[i]));
            max = Math.max(maxF[i], max);
        }
        return max;
    }
}
