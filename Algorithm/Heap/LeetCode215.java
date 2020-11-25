public class LeetCode215 {
    /* LeetCode 215 数组中的第K个最大元素 */
    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 经典的TopK问题，常用的解法有堆和快速选择
// 此处使用建堆的方法
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        // 建堆
        buildMaxHeap(nums);
        // 删除前k - 1个最大的元素(此处选择移动到数组末尾)
        int heapSize = n;
        for (int i = n - 1; i >= n - k + 1; i--) {
            swap(nums, 0, i);
            sink(nums, 0, --heapSize);
        }
        return nums[0];
    }

    // 建立大顶堆
    void buildMaxHeap(int[] arr) {
        int n = arr.length;
        // 自右向左利用sink建堆
        for (int i = n / 2; i >= 0; i--) {
            sink(arr, i, n);
        }
    }

    // 将小于子节点的点下沉
    void sink(int[] arr, int i, int heapSize) {
        int left = i * 2;
        int right = i * 2 + 1;
        int largest = i;        // 存储最大的点
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            sink(arr, largest, heapSize);
        }
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
