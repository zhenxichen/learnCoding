/* Leetcode 3 Longest Substring Without Repeating Characters */

//Algorithm: Sliding Window Algorithm

#include <iostream>
#include <string>
#include <list>
using namespace std;

class Solution {
public:
	int lengthOfLongestSubstring(string s) {
		int n = s.length();
		int max = 0;					//存储最大长度
		list<char> window;				//窗口
		for (int i = 0; i < n; i++) {
			char c = s[i];
			if (noRepeat(window, c)) {
				window.push_back(c);
				if (window.size() > max) {
					max = window.size();
				}
			}
			else {
				while (!noRepeat(window, c)) {		//只要仍有重复，就删去第一个
					window.erase(window.begin());
				}
				window.push_back(c);
			}
		}
		return max;
	}

	int noRepeat(list<char> window, char c) {
		if (window.empty()) { return 1; }
		for (list<char>::iterator it = window.begin(); it != window.end();it++) {
			if (*it == c) { return 0; }
		}
		return 1;
	}
};

int main() {			//To test
	Solution s;
	string str = "aab";
//	string str = "pwwkew";
	cout << s.lengthOfLongestSubstring(str);
	return 0;
}