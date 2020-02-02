/* Leetcode 10 Regular Expression Matching */
/* Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
*	'.' Matches any single character.
*	'*' Matches zero or more of the preceding element.
*	The matching should cover the entire input string (not partial).
*/

#include <string>
#include <vector>

//使用回溯算法

using namespace std;

class Solution {
public:
	bool isMatch(string s, string p) {
		if (p.empty()) { return s.empty(); }
		bool firstMatch = !s.empty() && (s[0] == p[0] || p[0] == '.');
		if (p.size() >= 2) {
			if (p[1] == '*') {
				return isMatch(s, p.substr(2)) || (firstMatch&&isMatch(s.substr(1), p));
			}	//前者为*前被重复0次的情况，后者为*前被重复n(n>=1)次的情况
				//若n=1，则进入isMatch后下一层为n = 0的情况
		}
		return firstMatch && isMatch(s.substr(1), p.substr(1));
	}
};