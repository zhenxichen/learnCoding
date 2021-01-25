import java.util.Random;

public class NC88 {
    /* NC 88 寻找第K大 */

    /**
     * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
     * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.findKth(new int[]{1, 3, 5, 2, 2}, 5, 1);
        System.out.println(ans);
    }
}

class Solution {
    Random random = new Random();

    public int findKth(int[] a, int n, int K) {
        // write code here
        return quickSelect(a, 0, n - 1, n - K);
    }

    int quickSelect(int[] arr, int left, int right, int index) {
        int q = randomPartition(arr, left, right);
        if (q == index) {   // 划分的q为我们所要找的位置
            return arr[q];  // 则q位置的数即为要找的数
        }
        if (q < index) {    // 若q在左侧，则对右侧子数组进行划分
            return quickSelect(arr, q + 1, right, index);
        } else {
            return quickSelect(arr, left, q - 1, index);
        }
    }

    int randomPartition(int[] arr, int left, int right) {
        int i = random.nextInt(right - left + 1) + left;
        swap(arr, i, right);        //  将pivot移动到最右侧
        return partition(arr, left, right);
    }

    int partition(int[] arr, int left, int right) {
        int x = arr[right];     // 以最右侧的数为pivot
        int index = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= x) {
                swap(arr, ++index, j);
            }
        }
        swap(arr, index + 1, right);
        return index + 1;
    }

    void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}