import java.util.*;

public class NC93 {
    /* NC 93 设计LRU缓存结构 */
    /**
     * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
     * · set(key, value)：将记录(key, value)插入该结构
     * · get(key)：返回key对应的value值
     * [要求]
     *  1. set和get方法的时间复杂度为O(1)
     *  2. 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
     *  3. 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
     * 若opt=1，接下来两个整数x, y，表示set(x, y)
     * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
     * 对于每个操作2，输出一个答案
     * 示例1：
     * 输入：[[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
     * 输出：[1,-1]
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        s.LRU(new int[][]{
                {1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}
        }, 3);
    }
}

// 解法：双向链表+哈希表
class Solution {
    class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode prev;
        public ListNode() { }
        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, ListNode> map;
    private ListNode head;
    private ListNode tail;
    private int size;
    private int capacity;

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        // 初始化数据结构
        capacity = k;
        size = 0;
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        // 调用方法
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                set(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2) {
                ans.add(get(operators[i][1]));
            }
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void set(int key, int value) {
        ListNode node = map.getOrDefault(key, null);
        if (node == null) {
            addToHead(key, value);
            size++;
        } else {
            node.value = value;
            moveToHead(node);
        }
        if (size > capacity) {
            removeTail();
        }
    }

    private int get(int key) {
        ListNode node = map.getOrDefault(key, null);
        if (node == null) { return -1; }
        moveToHead(node);
        return node.value;
    }

    private void addToHead(int key, int value) {
        ListNode node = new ListNode(key, value);
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
        map.put(key, node);
    }

    private void moveToHead(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    private void removeTail() {
        ListNode lastNode = tail.prev;
        lastNode.prev.next = tail;
        tail.prev = lastNode.prev;
        map.remove(lastNode.key);
    }
}