import java.util.ArrayList;
import java.util.Arrays;

public class NC54 {
    /* NC 54 数组中相加和为0的三元组 */
    /**
     * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
     * 注意：
     * 1. 三元组（a、b、c）中的元素必须按非降序排列。（即a≤b≤c）
     * 2. 解集中不能包含重复的三元组。
     * 例如，给定的数组 S = {-10 0 10 20 -10 -40},解集为(-10, 0, 10) (-10, -10, 20)
     */
    //
    // 注：本题与LeetCode 15 3Sum 相同
    // 题目链接：https://leetcode-cn.com/problems/3sum/
    // 题解链接：https://github.com/xuanqizi/learnCoding/blob/master/Algorithm/Leetcode15.java
    //
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：排序+双指针
class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(num);
        int n = num.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            int target = -num[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                while (left > i + 1 && left < n && num[left] == num[left - 1]) {
                    left++;
                }
                while (left < right && num[left] + num[right] > target) {
                    right--;
                }
                if (left >= right) {
                    break;
                }
                if (num[left] + num[right] == target) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(num[i]); list.add(num[left]); list.add(num[right]);
                    ans.add(new ArrayList<>(list));
                }
                left++;
            }
        }
        return ans;
    }
}