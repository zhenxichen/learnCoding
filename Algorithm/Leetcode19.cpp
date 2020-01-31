/* Leetcode 19 Remove Nth Node From End of List */
/*给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。*/

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
	ListNode(int x):
		val(x),next(NULL){ }
};

class Solution {
public:
	ListNode* removeNthFromEnd(ListNode* head, int n) {
		ListNode* dummy = new ListNode(0);		//引入一个哑结点作为链表头
		dummy->next = head;						//用以简化部分特殊情况
		ListNode *p1 = dummy, *p2 = dummy;
		for (int i = 0; i <= n; i++) {
			p1 = p1->next;
		}
		while (p1) {							//最终状态：p1指向空结点，p2指向要删除结点的前驱
			p1 = p1->next;
			p2 = p2->next;
		}
		ListNode* temp = p2->next->next;
		p2->next = temp;
		return dummy->next;
	}
};