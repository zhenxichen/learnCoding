/* Leetcode 22 Generate Parentheses */
/*给出n代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。*/

//本题使用回溯算法

#include <vector>
#include <string>

using namespace std;

class Solution {
public:
	vector<string> generateParenthesis(int n) {
		vector<string> v;
		backtrack(v, "", 0, 0, n);
		return v;
	}

	void backtrack(vector<string>& v, string curr, int open, int close, int max) {
		if (curr.size() == max * 2) {
			v.push_back(curr);
			return;
		}
		if (open < max) {
			backtrack(v, curr + "(", open + 1, close, max);
		}
		if (close < open) {
			backtrack(v, curr + ")", open, close + 1, max);
		}
	}
};