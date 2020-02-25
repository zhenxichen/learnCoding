import java.util.Arrays;

public class Leetcode16 {
    /* Leetcode 16 3Sum Closest */
    /**
     * Given an array nums of n integers and an integer target,
     * find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.*/
    //解题思路：类似Leetcode 15，采用排序+双指针
    //与上题的区别在于不去重也可以AC
    //添加去重可以降低用时（无去重：9ms -> 去重：6ms）
    public static void main(String [] args){
        Solution s = new Solution();
    }
}

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < len; i++){
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(sum-target) < Math.abs(closest-target)){
                    closest = sum;
                }
                if(sum == target){ return sum; }
                else if(sum > target){
                    while(left < right && nums[right] == nums[right-1]){
                        right--;            //去重以降低耗时（不能降低时间复杂度）
                    }
                    right--;
                }
                else{       //sum < target
                    while(left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    left++;
                }
            }
        }
        return closest;
    }
}
