public class LeetCode4 {
    /* LeetCode 4 寻找两个正序数组的中位数 */

    /**
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     */
    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        Solution s = new Solution();
        // s.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        s.findMedianSortedArrays(new int[]{2}, new int[]{});
    }
}

// 解法二：二分查找
// 时间复杂度：O(log(m+n))
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        double median = 0.0;
        if (len % 2 == 1) {
            int k = (len + 1) / 2;
            median = getKthElement(A, B, k);
        } else {
            int k = len / 2;
            median = (getKthElement(A, B, k) + getKthElement(A, B, k + 1)) / 2.0;
        }
        return median;
    }

    int getKthElement(int[] A, int[] B, int k) {
        int m = A.length;
        int n = B.length;
        int index1 = 0, index2 = 0;
        while (true) {
            // 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第k小的元素。
            if (index1 == m) {
                return B[index2 + k - 1];
            }
            if (index2 == n) {
                return A[index1 + k - 1];
            }
            // 如果 k=1，我们只要返回两个数组首元素的最小值即可。
            if (k == 1) {
                return Math.min(A[index1], B[index2]);
            }
            int half = k / 2;
            // 防止newIndex越界
            int newIndex1 = Math.min(index1 + half, m) - 1;
            int newIndex2 = Math.min(index2 + half, n) - 1;
            int pivot1 = A[newIndex1], pivot2 = B[newIndex2];
            if (pivot1 <= pivot2) {  // 如果 A[k/2-1] <= B[k/2-1]，则可以排除A[0] 到 A[k/2-1]。
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {    // 如果 A[k/2-1] > B[k/2-1]，则可以排除B[0] 到 B[k/2-1]。
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}

// 解法一：双指针
// 时间复杂度：O(m+n) 空间复杂度:O(1)
class Solution1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int i1 = 0, i2 = 0;
        int left = 0, right = 0;
        boolean odd = ((m + n) % 2 == 1);
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (i1 < m && (i2 >= n || nums1[i1] < nums2[i2])) {
                right = nums1[i1++];
            } else {
                right = nums2[i2++];
            }
        }
        if (odd) {
            return right;
        } else {
            return (left + right) / 2.0;
        }
    }
}
