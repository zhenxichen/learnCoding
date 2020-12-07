public class LeetCode53 {
    /* LeetCode 53 最大子序和 */
    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */
    public static void main(String[] args) {
        Solution_DP dp = new Solution_DP();
        Solution_DP2 dp2 = new Solution_DP2();
        Solution_Greedy greedy = new Solution_Greedy();
        Solution_DC dc = new Solution_DC();
    }
}

// 解法四：分治算法
// 分为三种情况：最大子序和在左边、在右边和跨过中间
// 时间复杂度: O(nlogn) 空间复杂度: O(logn)
class Solution_DC {
    public int maxSubArray(int[] nums) {
        return divide(nums, 0, nums.length - 1);
    }

    int divide(int[] nums, int left, int right) {
        if(left >= right) {
            return nums[left];
        }
        int n = nums.length;
        int mid = (left + right) / 2;
        int leftMax = divide(nums, left, mid - 1);
        int rightMax = divide(nums, mid + 1, right);
        int midMax = crossMax(nums, left, right, mid);
        return Math.max(leftMax, Math.max(rightMax, midMax));
    }

    // 计算穿过中心点的最大值
    int crossMax(int[] nums, int left, int right, int mid) {
        // 从中心点开始向左右两边遍历
        // 找出包含中心点的最大的两侧子串
        int sum = 0;
        int leftMax = 0;    // 如果小于0就直接不加左侧子串
        // 向左侧遍历
        for(int i = mid - 1; i >= left; i--) {
            sum += nums[i];
            leftMax = Math.max(sum, leftMax);
        }
        sum = 0;
        int rightMax = 0;
        for(int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightMax = Math.max(sum, rightMax);
        }
        return nums[mid] + leftMax + rightMax;
    }
}

// 解法三：贪心算法
// 当前数大于0时就加入原来的，否则新起一个
class Solution_Greedy {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int currMax = 0;
        for(int i = 0; i < nums.length; i++) {
            currMax += nums[i];
            max = Math.max(max, currMax);
            if(currMax < 0) {
                currMax = 0;
            }
        }
        return max;
    }
}

// 解法二：动态规划（使用滚动数组优化）
// 时间复杂度：O(n) 空间复杂度：O(1)
class Solution_DP2 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int currMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currMax = Math.max(currMax + nums[i], nums[i]);
            max = Math.max(currMax, max);
        }
        return max;
    }
}

// 解法一：动态规划
// 时间复杂度：O(n) 空间复杂度：O(n)
class Solution_DP {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];  // 记录以i为结尾的子数组的最大和
        dp[0] = nums[0];    // 因为子数组至少有一个元素，所以n一定不为0
        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            max = Math.max(dp[i], max);
        }
        return max;
    }
}