/* Leetcode 20 Valid Parentheses */
/*Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
* An input string is valid if:
*	1. Open brackets must be closed by the same type of brackets.
*	2. Open brackets must be closed in the correct order.
* Note that an empty string is also considered valid.
*/

#include <stack>
#include <string>

using namespace std;

class Solution {
public:
	bool isValid(string s) {
		if (s.empty()) { return true; }
		stack<char> S;
		for (int i = 0; i < s.size(); i++) {
			if (s[i] == '(' || s[i] == '[' || s[i] == '{') {
				S.push(s[i]);
			}
			else if (s[i] == ')') {
				if (S.empty()) { return false; }
				if (S.top() == '(') { S.pop(); }
				else { return false; }
			}
			else if (s[i] == ']') {
				if (S.empty()) { return false; }
				if (S.top() == '[') { S.pop(); }
				else { return false; }
			}
			else if (s[i] == '}') {
				if (S.empty()) { return false; }
				if (S.top() == '{') { S.pop(); }
				else { return false; }
			}
		}
		if (!S.empty()) { return false; }
		return true;
	}
};