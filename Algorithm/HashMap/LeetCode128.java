import java.util.HashSet;
import java.util.Set;

public class LeetCode128 {
    /* LeetCode 128 最长连续序列 */
    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */

    // 解法：哈希表
    class Solution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> numSet = new HashSet<>();
            for (int num : nums) {
                numSet.add(num);
            }
            int longest = 0;
            for (int num : numSet) {
                // 如果集合中存在num - 1，那么以num为开头的一定不会是最长的
                // 可以进行剪枝操作
                if (numSet.contains(num - 1)) {
                    continue;
                }

                int length = 0;
                int curr = num;
                while (numSet.contains(curr)) {
                    curr++;
                    length++;
                }
                longest = Math.max(length, longest);
            }
            return longest;
        }
    }
}
