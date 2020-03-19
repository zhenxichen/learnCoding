/* Leetcode 101 Symmetric Tree */
// 迭代解和题干见Leetcode101.java
// 这里用C++只尝试做递归解

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

#include <queue>

using namespace std;

struct TreeNode {
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x),left(NULL),right(NULL){ }
};

class Solution {
public:
	bool isSymmetric(TreeNode* root) {
		if (!root) { return true; }
		return judgeIn(root->left, root->right);
	}

	bool judgeIn(TreeNode* t1, TreeNode* t2) {
		if (t1 == NULL && t2 == NULL) {		//均为NULL
			return true;
		}
		if (t1 == NULL || t2 == NULL) {		//仅有一个为NULL
			return false;
		}
		return (t1->val == t2->val) && judgeIn(t1->left, t2->right)
			&& judgeIn(t2->left, t1->right);
	}
};
