/* Leetcode 10 Regular Expression Matching*/
//之前参照着题解写过一个回溯算法的解，但是运行效率好像比较低
//然后就打算顺带再学习一下动态规划，写一个自底向上的动态规划解
//但之前用C++写一直会报错，所以这题的动态规划解用Java来写

public class Leetcode10 {
    public static void main(String[] args){
        Solution solution = new Solution();
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        boolean [][] dp = new boolean[s.length()+1][p.length()+1];  //用dp[i][j]表示s[i:]和p[j:]匹配
        dp[s.length()][p.length()] = true;
        for(int i=s.length();i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                boolean firstMatch = false;
                if(i<s.length()){
                    firstMatch = (s.charAt(i)==p.charAt(j)||p.charAt(j)=='.');
                }
                if(j+1<p.length() && p.charAt(j+1)=='*'){
                    dp[i][j]=(dp[i][j+2] || (firstMatch && dp[i+1][j]));
                }                   //前者表示*指代0次的情况，后者表示*指代>=1次的情况
                else{
                    dp[i][j]=firstMatch && dp[i+1][j+1];  //注意先后顺序，如果将dp[i+1][j+1]放在前面可能会越界
                }
            }
        }
        return dp[0][0];
    }
}
