public class NC140 {
    /* NC 140 排序 */
    /**
     * 给定一个数组，请你编写一个函数，返回该数组排序后的形式。
     */
    public static void main(String[] args) {
        Solution_QuickSort sq = new Solution_QuickSort();
        Solution_MergeSort sm = new Solution_MergeSort();
    }
}

// 快速排序
class Solution_QuickSort {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] MySort (int[] arr) {
        // write code here
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
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
        swap(arr, left, i);                 // 需要将pivot移动到正确的位置上
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

// 归并排序
class Solution_MergeSort {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public int[] MySort (int[] arr) {
        // write code here
        mergeSort(arr, new int[arr.length],0, arr.length - 1);
        return arr;
    }

    void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    void merge(int[] arr, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];       // 将原数组copy到临时数组上
        }
        int i1 = left, i2 = mid + 1;
        for (int i = left; i <= right; i++) {
            if (i1 == mid + 1) {       // i1超界
                arr[i] = temp[i2++];
            } else if (i2 > right) {    // i2超界
                arr[i] = temp[i1++];
            } else if (temp[i1] > temp[i2]) {
                arr[i] = temp[i2++];
            } else {
                arr[i] = temp[i1++];
            }
        }
    }
}