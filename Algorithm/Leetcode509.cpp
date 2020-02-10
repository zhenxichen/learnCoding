/* Leetcode 509 Fibonacci Number */
/**
* The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
* such that each number is the sum of the two preceding ones, starting from 0 and 1.
* Given N, calculate F(N).
*/
//Note: 0 ≤ N ≤ 30.

#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
	int fib(int N) {
		int f[31];
		f[0] = 0;
		f[1] = 1;
		for (int i = 2; i <= N; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[N];
	}
};