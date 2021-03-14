import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode912 {
    /* LeetCode 912 排序数组（非递归快排） */
    /**
     * 给你一个整数数组 nums，请你将该数组升序排列。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        s.sortArray(new int[]{5, 2, 3, 1});
    }
}

class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums);
        return nums;
    }

    private void quickSort(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        stack.push(n - 1);
        while (!stack.isEmpty()) {
            int right = stack.pop();
            int left = stack.pop();
            int p = partition(arr, left, right);
            if (left < p - 1) {
                stack.push(left);
                stack.push(p - 1);
            }
            if (p + 1 < right) {
                stack.push(p + 1);
                stack.push(right);
            }
        }
    }

    private int partition(int[] arr, int left, int right) {
        if (left >= right) { return left; }
        int i = left, j = right;
        int pivot = arr[left];
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, i, left);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}