import java.util.HashSet;
import java.util.Set;

public class LeetcodeJ03 {
    /* Leetcode 剑指Offer 03 数组中重复的数字 */
    //有一段时间没写JAVA了（其实C++也好多天没写了）
    //所以打算用JAVA来写一两道Leetcode的题
    Solution s = new Solution();
}

class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int n:nums){
            if(!set.add(n)){
                return n;
            }
        }
        return -1;
    }
}
