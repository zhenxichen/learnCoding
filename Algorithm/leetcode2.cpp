/*Leetcode 2 Add Two Numbers*/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

#include <iostream>
#include <stack>

using namespace std;

struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x):
		val(x),next(NULL){ }
};

class Solution {
public:
	ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
		ListNode* ret = NULL;
		ListNode *p = l1, *q = l2;
		ListNode* l;
		int carry = 0;							//carry
		int x, y;								//To save the two numbers
		int sum;								//To save the sum
		while (p != NULL || q != NULL) {
			x = (p != NULL ? p->val : 0);
			y = (q != NULL ? q->val : 0);
			sum = x + y + carry;
			carry = sum / 10;
			if (!ret) {
				ret = new ListNode(sum % 10);
				l = ret;
			}
			else {
				l->next = new ListNode(sum % 10);
				l = l->next;
			}
			if (p) { p = p->next; }
			if (q) { q = q->next; }
		}
		if (carry == 1) {
			l->next = new ListNode(1);
		}
		return ret;
	}
};


//第一次做的代码如下方备注所示
//错误在于试图将两数转为具体的数，从而超出了范围
/*
class Solution {
public:
	ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
		unsigned long long num1 = 0, num2 = 0;							//To save the two numbers
		unsigned long long num = 0;									//To save the result
		ListNode* ret;									//To save the Linked list's head
		unsigned long long i = 1;
		for (ListNode* l = l1; l; l = l->next) {
			num1 += l->val*i;
			i *= 10;
		}
		i = 1;
		for (ListNode* l = l2; l; l = l->next) {
			num2 += l->val*i;
			i *= 10;
		}
		num = num1 + num2;
		ret = new ListNode(num % 10);
		ListNode* l3 = ret;
		ListNode* l4;
		while (num > 0) {
			num /= 10;
			if (num > 0) {
				l4 = l3;
				l3->next = new ListNode(num % 10);
				l3 = l3->next;
			}
		}
		return ret;
	}
};

int main() {				//To test
	Solution s;
	ListNode* l1,*l2;
	
	l1 = new ListNode(1);
	l1->next = new ListNode(8);
	l2 = new ListNode(0);
	s.addTwoNumbers(l1, l2);
	
	return 0;
}

*/