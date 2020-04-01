public class Leetcode28 {
    /* Leetcode 28 Implement strStr() */

    /**
     * Implement strStr().
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     */
    //直观想法：滑动窗口
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        boolean match = true;
        if (m == 0) {
            return 0;
        }
        if (n < m) {
            return -1;
        }
        for (int i = 0; i < n - m + 1; i++) {
            for(int j=0;j<m;j++){
                if(haystack.charAt(i+j)!=needle.charAt(j)){
                    match = false;
                    break;
                }
            }
            if(!match){
                match = true;
            }else{  //match
                return i;
            }
        }
        return -1;
    }
}