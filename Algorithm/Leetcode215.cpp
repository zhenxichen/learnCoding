/* Leetcode 215 Kth Largest Element in an Array */
//Find the kth largest element in an unsorted array. 
//Note that it is the kth largest element in the sorted order, not the kth distinct element.

#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
	int findKthLargest(vector<int>& nums, int k) {
		return quickSelect(nums, 0, nums.size() - 1, k);
	}

	int partition(vector<int>& nums, int left,int right) {
		int key = nums[left];
		int i = left, j = right;
		while (i < j) {
			while (i < j && nums[j] >= key) {
				j--;
			}
			nums[i] = nums[j];
			while (i < j && nums[i] <= key) {
				i++;
			}
			nums[j] = nums[i];
		}
		nums[i] = key;
		return i;
	}

	int quickSelect(vector<int>& nums, int left, int right, int k) {
		if (left == right) { return nums[left]; }
		int pos = partition(nums, left, right);
		int n = nums.size();
		if (pos == n - k) {
			return nums[n - k];
		}
		else if (pos < n - k) {
			return quickSelect(nums, pos + 1, right, k);
		}
		else {
			return quickSelect(nums, left, pos - 1, k);
		}
	}
};

int main() {
	Solution s;
	vector<int> v = { 7,6,5,4,3,2,1 };
	cout << s.findKthLargest(v, 2) << endl;
	return 0;
}