import java.util.Stack;

public class Leetcode42 {
    /* Leetcode 42 Trapping Rain Water */

    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it is able to trap after raining.
     * Example:
     * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     */
    //暴力解：遍历数组-> 每个位置能装的水=左右侧最高高度的较低值-当前高度
    //改进算法：栈的应用(单调栈)
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution solution = new Solution();
    }
}

class Solution {
    //改进算法：单调栈
    public int trap(int[] height) {
        int ans = 0;
        int n = height.length;
        int curr = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                curr = stack.pop();         //第一个比i底的板块决定了雨水底部的高度
                while (!stack.isEmpty() && height[curr] == height[stack.peek()]) {
                    stack.pop();            //pop出所有与curr高度相同的板块
                }
                if (stack.isEmpty()) {
                    break;                  //若栈空，则说明到达边缘
                }
                int distance = i - stack.peek() - 1;
                int hgt = Math.min(height[i], height[stack.peek()]) - height[curr];
                ans += distance * hgt;
            }
            stack.add(i);       //若仍维持单调，则直接入栈
        }
        return ans;
    }   //O(n)
}


class Solution1 {
    //暴力解：遍历数组-> 每个位置能装的水=左右侧最高高度的较低值-当前高度
    public int trap(int[] height) {
        int n = height.length;
        int ans = 0;
        int capacity = 0;
        for (int i = 1; i < n - 1; i++) {
            int left = 0;
            int right = 0;
            //分别找出左右侧的最高点
            for (int j = i - 1; j >= 0; j--) {
                left = Math.max(left, height[j]);
            }
            for (int j = i + 1; j < n; j++) {
                right = Math.max(right, height[j]);
            }
            capacity = Math.min(left, right) - height[i];
            if (capacity < 0) {
                capacity = 0;
            }
            ans += capacity;
        }
        return ans;
    }
}   //O(n^2)
