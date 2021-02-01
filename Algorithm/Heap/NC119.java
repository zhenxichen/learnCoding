import java.util.*;

public class NC119 {
    /* NC 119 最小的K个数（堆） */
    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
     */
    // 这里通过两种方法来实现堆
    // 分治算法（快排思路）的解法放到分治目录下
    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        s1.GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 0);
        Solution2 s2 = new Solution2();
        s2.GetLeastNumbers_Solution(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 4);
    }
}

class Solution2 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        int n = input.length;
        int heapSize = n;
        ArrayList<Integer> ans = new ArrayList<>();
        if (k == 0 || n < k) {
            return ans;
        }
        buildMinHeap(input);        // 构建小顶堆
        for (int i = 0; i < k; i++) {
            ans.add(poll(input, --heapSize));
        }
        return ans;
    }

    void buildMinHeap(int[] arr) {
        int n = arr.length;
        // 从右向左进行建堆
        for (int i = n / 2; i >= 0; i--) {
            sink(arr, i, n);
        }
    }

    // 将大于子节点的点下沉
    void sink(int[] arr, int i, int heapSize) {
        int left = 2 * i;       // 左侧子节点对应的下标
        int right = 2 * i + 1;      // 右侧子节点对应的下标
        int smallest = i;            // 存储最小节点的下标
        if (left < heapSize && arr[smallest] > arr[left]) {
            smallest = left;
        }
        if (right < heapSize && arr[smallest] > arr[right]) {
            smallest = right;
        }
        if (smallest != i) {     // 若当前节点大于某一子节点
            swap(arr, i, smallest);      // 将其与最小的子节点进行交换
            sink(arr, smallest, heapSize);       // 并且进一步向下检查是否需要下沉
        }
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    int poll(int[] arr, int heapSize) {
        int ret = arr[0];
        swap(arr, 0, heapSize);
        sink(arr, 0, heapSize);
        return ret;
    }
}

// 解法一：堆排序（运用Java自带的数据结构PriorityQueue）
class Solution1 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = input.length;
        if (n < k || k == 0) { return ans; }
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });     // 通过重写Comparator将PriorityQueue改为大顶堆
        for (int i = 0; i < n; i++) {
            if (heap.size() < k) {    // 堆未满
                heap.offer(input[i]);
            } else {    // 堆已满
                if (input[i] < heap.peek()) {   // 若当前数小于堆顶，则弹出堆顶的数，再将当前数压入堆中
                    heap.poll();
                    heap.offer(input[i]);
                }
            }
        }
        while(!heap.isEmpty()) {
            ans.add(heap.poll());
        }
        Collections.reverse(ans);
        return ans;
    }
}