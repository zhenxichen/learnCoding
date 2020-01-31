/*Leetcode 108 Convert Sorted Array to Binary Search Tree */

/* Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*  For this problem, a height-balanced binary tree is defined as a binary tree 
*  in which the depth of the two subtrees of every node never differ by more than 1.
*/

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

#include <vector>

using namespace std;

struct TreeNode {
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x):
		val(x),left(NULL),right(NULL) { }
};
class Solution {
public:
	TreeNode* sortedArrayToBST(vector<int>& nums) {
		if (nums.size() == 1) { return new TreeNode(nums[0]); }
		if (nums.empty()) { return NULL; }
		int mid = nums.size() / 2;
		TreeNode* root = new TreeNode(nums[mid]);
		vector<int> left;
		vector<int> right;
		for (int i = 0; i < mid; i++) {
			left.push_back(nums[i]);
		}
		for (int i = mid + 1; i < nums.size(); i++) {
			right.push_back(nums[i]);
		}
		root->left = sortedArrayToBST(left);
		root->right = sortedArrayToBST(right);
		return root;
	}
};					//recursion