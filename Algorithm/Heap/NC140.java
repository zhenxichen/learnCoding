public class NC140 {
    /* NC 140 排序（堆排序） */
    /**
     * 给定一个数组，请你编写一个函数，返回该数组排序后的形式。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 堆排序
class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] MySort (int[] arr) {
        // write code here
        int n = arr.length;
        int heapSize = n;
        buildMaxHeap(arr);
        while (heapSize > 0) {
            swap(arr, 0, heapSize - 1);     // 将堆顶与最后一个元素交换，堆顶必定为最大元素
            sink(arr, 0, --heapSize);
        }
        return arr;
    }

    void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            sink(arr, i, n);
        }
    }

    void sink(int[] arr, int i, int heapSize) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = i;
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