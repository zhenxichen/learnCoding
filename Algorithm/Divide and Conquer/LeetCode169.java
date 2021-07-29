import java.util.HashMap;
import java.util.Map;

public class LeetCode169 {
    /* LeetCode 169 多数元素 */
    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 n/2 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */

    // 解法一：HashMap
    // 时间复杂度：O(n) 空间复杂度：O(n)
    public static class HashMapSolution {
        public int majorityElement(int[] nums) {
            int n = nums.length;
            Map<Integer, Integer> times = new HashMap<>();
            for (int num : nums) {
                times.put(num, times.getOrDefault(num, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : times.entrySet()) {
                if (entry.getValue() > n / 2) {
                    return entry.getKey();
                }
            }
            return -1;
        }
    }

    // 解法二：分治算法
    // 时间复杂度：O(nlogn) 空间复杂度：O(logn)
    public static class DCSolution {
        public int majorityElement(int[] nums) {
            return major(nums, 0, nums.length - 1);
        }

        private int major(int[] nums, int left, int right) {
            // 唯一的数一定是这一段的众数
            if (left == right) {
                return nums[left];
            }

            // 查找两侧的众数
            int mid = left + ((right - left) >> 1);
            int leftMajor = major(nums, left, mid);
            int rightMajor = major(nums, mid + 1, right);

            // 如果两侧众数相同，直接返回该结果
            if (leftMajor == rightMajor) {
                return leftMajor;
            }

            // 否则，对两侧的众数进行比较
            int leftCount = countInRange(nums, leftMajor, left, right);
            int rightCount = countInRange(nums, rightMajor, left, right);

            return leftCount > rightCount ? leftMajor : rightMajor;
        }

        private int countInRange(int[] nums, int num, int left, int right) {
            int count = 0;
            for (int i = left; i <= right; i++) {
                count += (nums[i] == num ? 1 : 0);
            }
            return count;
        }
    }

    // 解法三：Boyer-Moore排序算法
    // 时间复杂度：O(n) 空间复杂度：O(1)
    public static class Solution {
        public int majorityElement(int[] nums) {
            int count = 0;
            int candidate = 0;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }
            return candidate;
        }
    }
}
