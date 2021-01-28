import java.util.HashMap;
import java.util.Map;

public class LeetCode560 {
    /* LeetCode 560 和为K的子数组 */

    /**
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：前缀和 + 哈希表
// 要使[i, ..., j]的和为k,那么i和j的前缀和需要满足pre[j]-pre[i-1]=k
// 那么，要知道以j为最后一项的，满足和为k的子数组有多少个，就只需要知道在j之前有多少个前缀和p = pre[j]-k
// 因此，我们用哈希表存储<前缀和，出现次数>的键值对
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int n = nums.length;
        int sum = 0;
        int count = 0;
        mp.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            count += mp.getOrDefault(sum - k, 0);
            mp.put(sum, mp.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}