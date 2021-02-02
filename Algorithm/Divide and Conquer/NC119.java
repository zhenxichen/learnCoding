import java.util.*;

public class NC119 {
    /* NC 119 最小的K个数（快排思路） */
    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        int n = input.length;
        ArrayList<Integer> ans = new ArrayList<>();
        if (k == 0 || n < k) {
            return ans;
        }
        quickSelect(input, 0, n - 1, k);
        for (int i = 0; i < k; i++) {
            ans.add(input[i]);
        }
        return ans;
    }

    void quickSelect(int[] arr, int left, int right, int k) {
        if (left >= right) {    // 递归基
            return;
        }
        int p = randomPartition(arr, left, right);
        int num = p - left + 1;         // 计算共划分了几个数
        if (k == num) {
            return;
        } else if (k > num) {       // 划分数量不足
            quickSelect(arr, p + 1, right, k - num);
        } else {        // k < num
            quickSelect(arr, left, p - 1, k);
        }
    }

    // 添加了随机化的划分
    int randomPartition(int[] arr, int left, int right) {
        int i = new Random().nextInt(right - left + 1) + left;
        swap(arr, i, right);
        return partition(arr, left, right);
    }

    int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i++, j);
            }
        }
        swap(arr, i, right);
        return i;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}