import java.util.*;

public class LeetCode146 {
    /* LeetCode 146 LRU 缓存机制 */
    /**
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字已经存在，则变更其数据值；
     *      如果关键字不存在，则插入该组「关键字-值」。
     *      当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
     */
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(1);
        cache.put(2,1);
        cache.get(2);
        cache.put(3,2);
        cache.get(2);
        cache.get(3);
    }
}

// 解法：哈希表 + 双向链表
class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node() { }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> hashMap;
    private Node head;  // 头结点
    private Node tail;  // 尾结点
    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        hashMap = new HashMap<>();
        size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!hashMap.containsKey(key)) {
            return -1;
        }
        Node node = hashMap.get(key);
        int ret = node.value;
        moveToHead(node);
        return ret;
    }

    public void put(int key, int value) {
        // 若表中已有该key，更新并放到表头
        if(hashMap.containsKey(key)) {
            Node node = hashMap.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }
        // 插入到链表头
        Node node = new Node(key, value);
        addToHead(node);
        // 若链表已满，删除链表尾部的结点
        if(size > capacity) {
            removeTail();
        }
    }

    private void moveToHead(Node node) {
        // 将该结点的前后相接
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // 将该节点放到链表头
        node.prev = head;
        node.next = head.next;          // 当前节点的下一个节点设为原来的第一个节点
        head.next.prev = node;     // 原来第一个节点的prev设为当前节点
        head.next = node;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;          // 当前节点的下一个节点设为原来的第一个节点
        head.next.prev = node;     // 原来第一个节点的prev设为当前节点
        head.next = node;          // 将头节点的下一个节点设为当前节点
        hashMap.put(node.key, node);
        size++;
    }

    private void removeTail() {
        Node node = this.tail.prev;
        this.tail.prev = node.prev;
        node.prev.next = this.tail;
        hashMap.remove(node.key);
        size--;
    }
}
