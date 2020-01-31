/* Leetcode 21 Merge Two Sorted Lists */
/*	Merge two sorted linked lists and return it as a new list. 
	The new list should be made by splicing together the nodes of the first two lists.
*/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

#include <iostream>

struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) :
		val(x), next(NULL) { }
};

class Solution {
public:
	ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
		ListNode* head = new ListNode(0);
		ListNode* l = head;
		ListNode *p1 = l1, *p2 = l2;
		while (p1 != NULL && p2 != NULL) {
			if (p1->val > p2->val) {
				l->next = new ListNode(p2->val);
				p2 = p2->next;
			}
			else {
				l->next = new ListNode(p1->val);
				p1 = p1->next;
			}
			l = l->next;
		}
		if (p1) { l->next = p1; }
		else if (p2) {
			l->next = p2;
		}
		return head->next;
	}
};

int main() {			//Test
	Solution s;
	ListNode* l1 = new ListNode(1);
	l1->next = new ListNode(2);
	l1->next->next = new ListNode(4);
	ListNode* l2 = new ListNode(1);
	l2->next = new ListNode(3);
	l2->next->next = new ListNode(4);
	s.mergeTwoLists(l1, l2);
	return 0;
}