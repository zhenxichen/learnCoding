/* Leetcode 7 Reverse Integer */
/* Given a 32-bit signed integer, reverse digits of an integer. */

//转换时需要考虑溢出的问题(-2147483648~2147483647)

#include <iostream>

class Solution {
public:
	int reverse(int x) {
		int revert = 0;
		while (x != 0) {
			int pop = x % 10;
			if (revert > INT_MAX / 10 || (revert == INT_MAX / 10 && pop > 7)) { return 0; }
			if (revert < INT_MIN / 10 || (revert == INT_MIN / 10) && pop < -8) { return 0; }
			revert *= 10;
			revert += pop;
			x /= 10;
		}
		return revert;
	}
};

int main() {		//Test
	Solution s;
	std::cout << s.reverse(-123);
	return 0;
}