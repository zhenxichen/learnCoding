/* Leetcode 5 Longest Palindromic Substring */
/* Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000. */

#include <string>
#include <vector>

using namespace std;

//解题思路：动态规划
//P(i,j)=(P(i+1,j-1)&(s[i]=s[j]))

class Solution {
public:
	string longestPalindrome(string s) {
		int n = s.size();								//Length of string s
		if (n == 0 || n == 1) { return s; }
		vector<vector<bool>> dp(n, vector<bool>(n));	//用dp[i][j]来表示P(i,j)
		for (int i = 0; i < n; i++) {					//Initialize the vector dp
			for (int j = 0; j < n; j++) {
				dp[i][j] = false;
			}
		}
		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
		}
		int max = 1;
		int start = 0;
		for (int j = 1; j < n; j++) {
			for (int i = 0; i < j; i++) {
				if (j - i <= 2) {						//Length of substring is 2 or 3
					if (s[i] == s[j]) {
						dp[i][j] = true;
					}
				}
				else {									//Longer than 3
					if ((dp[i + 1][j - 1]) && (s[i] == s[j])) {	//P(i,j)=(P(i+1,j-1)&(s[i]=s[j]))
						dp[i][j] = true;
					}
				}
				if (dp[i][j] && j - i + 1 > max) {		//length=j-i+1
					max = j - i + 1;					//record
					start = i;
				}
			}
		}
		return s.substr(start, max);
	}
};