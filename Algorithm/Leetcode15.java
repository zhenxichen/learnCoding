import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode15 {
    /* Leetcode 15 3Sum */
    /**
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     * Note:
     * The solution set must not contain duplicate triplets.*/
    //解题思路：排序+双指针
    Solution s = new Solution();
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(len < 3){ return ans; }      //返回空的List
        Arrays.sort(nums);
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){ break; }
            if(i > 0 && nums[i] == nums[i-1]){ continue; }
            int left = i + 1;
            int right = len - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left < right && nums[left] == nums[left + 1]){ left++; }
                    while(left < right&& nums[right] == nums[right-1]){ right--; }
                    left++;
                    right--;
                }
                else if(sum > 0){
                    right--;
                }
                else{      //sum < 0
                    left++;
                }
            }
        }
        return ans;
    }
}
