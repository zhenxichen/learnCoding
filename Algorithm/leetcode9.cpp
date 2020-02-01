/* Leetcode 9 Palidrome Number */
/* Determine whether an integer is a palindrome.
*  An integer is a palindrome when it reads the same backward as forward.*/

//思路：将后一半的数字反转后与前一半进行对比

class Solution {
public:
	bool isPalindrome(int x) {
		if (x == 0) { return true; }
		if (x < 0 || x % 10 == 0) { return false; }	//先处理x<0和末位是0的特殊情况
		int revert = 0;
		while (x > revert) {
			revert *= 10;
			revert += x % 10;
			x /= 10;
		}
		//位数为偶数时，比较前一半与后一半是否一致
		//位数为奇数时，将反转后的数除最后一位以外与原数进行比较
		return (x == revert || x == revert / 10);
	}
};