/* Leetcode 17 Letter Combinations of a Phone Number */
/*	Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
*	A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.*/

#include <vector>
#include <string>

using namespace std;

class Solution {
public:
	vector<string> letterCombinations(string digits) {
		vector<string> ret;
		if (digits == "") { return ret; }			//有点难搞，直接加个检测处理这种特殊情况吧
		traceback(ret, "",digits, 0);
		return ret;
	}

	void traceback(vector<string> &v,string curr,string digits, int depth) {
		if (curr.size() == digits.size()) {
			v.push_back(curr);
			return;
		}
		string match[10] = {
			"","","abc","def","ghi","jkl","mno",
			"pqrs","tuv","wxyz"
		};
		int num = transNum(digits[depth]);
		for (int i = 0; i < match[num].size(); i++) {
			traceback(v, curr + match[num][i], digits, depth + 1);
		}
	}

	int transNum(char c) {
		switch (c) {
			case '2':return 2;
			case '3':return 3;
			case '4':return 4;
			case '5':return 5;
			case '6':return 6;
			case '7':return 7;
			case '8':return 8;
			case '9':return 9;
			default: return 0;
		}
	}
};

int main() {							//Test
	Solution s;
//	string digits = "23";
	string digits = "";					//special condition
	s.letterCombinations(digits);		//break point here
	return 0;
}