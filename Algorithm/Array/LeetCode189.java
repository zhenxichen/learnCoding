import java.util.Arrays;

public class LeetCode189 {
    /* LeetCode 189 旋转数组 */
    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     */

    // 解法一：环状替换
    public static class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;          // 如果k > n，就只需要移动k%n位
            int count = gcd(k, n);
            for (int start = 0; start < count; start++) {
                int curr = start;
                int prev = nums[start];
                for (;;) {
                    int next = (curr + k) % n;
                    int temp = nums[next];      // 存储下一个位置的值
                    nums[next] = prev;          // 将当前值替换到下一个位置
                    prev = temp;
                    curr = next;
                    if (curr == start) {
                        break;
                    }
                }
            }
        }

        public int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }

    // 解法二：三次旋转数组
    // 数组倒数k个元素会移动到数组头部
    public static class ReverseSolution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            // -->--->  k = 3
            reverse(nums, 0, n - 1);        // <---<--
            reverse(nums, 0, k - 1);        // -->-<--
            reverse(nums, k, n - 1);              // -->-->-
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }

    // 2021春招字节跳动一面时现场想出的解法
    // 原地旋转，时间复杂度为O(KN)
    // LeetCode上提交会在最后一个用例超时
    static class KNSolution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            // 每次旋转一步，旋转k次
            for (int i = 0; i < k; i++) {
                int last = nums[n - 1];     // 记录最后一位数
                // 将前面的数依次后移
                for (int j = n - 1; j > 0; j--) {
                    nums[j] = nums[j - 1];
                }
                nums[0] = last;
            }
        }
    }

    public static void main(String[] args) {
        KNSolution kns = new KNSolution();
        int[] arr = new int[]{1,2,3,4,5,6,7};
        kns.rotate(arr, 3);    // should be [5,6,7,1,2,3,4]
        System.out.println(Arrays.toString(arr));
        Solution s = new Solution();
        arr = new int[]{1,2,3,4,5,6,7};
        s.rotate(arr, 3);    // should be [5,6,7,1,2,3,4]
        System.out.println(Arrays.toString(arr));
        ReverseSolution rs = new ReverseSolution();
        arr = new int[]{1,2,3,4,5,6,7};
        rs.rotate(arr, 3);    // should be [5,6,7,1,2,3,4]
        System.out.println(Arrays.toString(arr));
    }
}
