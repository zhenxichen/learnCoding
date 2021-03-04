public class LeetCode11 {
    /* LeetCode 11 盛最多水的容器 */
    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int n = height.length;
        int left = 0, right = n - 1;
        while (left < right) {
            ans = Math.max((right - left) * Math.min(height[left], height[right]), ans);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}