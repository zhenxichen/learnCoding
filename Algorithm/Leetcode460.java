import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Leetcode460 {
    /* Leetcode 460 LFU Cache */

    /**
     * Design and implement a data structure for Least Frequently Used (LFU) cache.
     * It should support the following operations: get and put.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
     * it should invalidate the least frequently used item before inserting a new item.
     * For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
     * the least recently used key would be evicted.
     * Note that the number of times an item is used is the number of calls to the get and put functions for that
     * item since it was inserted. This number is set to zero when the item is removed.
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     */
    //解法： 哈希表+双向链表
    //通过LinkedHashSet维护先进先出的特性来实现多个最小频率时的LRU
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2 /* capacity (缓存容量) */);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        cache.get(2);       // 返回 -1 (未找到key 2)
        cache.get(3);       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        cache.get(1);       // 返回 -1 (未找到 key 1)
        cache.get(3);       // 返回 3
        cache.get(4);       // 返回 4
    }
}

class Node {
    int key;
    int value;
    int freq = 1;

    public Node() {
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LFUCache {
    Map<Integer, Node> cache;   //缓存内容
    Map<Integer, LinkedHashSet<Node>> freqMap;      //各频次对应的双向链表
    int size;
    int capacity;
    int min;                //当前最小频次

    public LFUCache(int capacity) {
        cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
        size = 0;
        min = 0;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        addFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {   //已经存在 -> 增加频率即可
            addFreq(node);
            node.value = value;     //虽然key相同，value仍有可能不同
        } else {              //尚不存在
            if (size == capacity) {   //已满 -> 删除LFU结点
                removeNode();
            }
            node = new Node(key, value);
            addNode(node);
        }
    }

    void removeNode() {
        LinkedHashSet<Node> set = freqMap.get(min);
        Node minNode = set.iterator().next();       //LinkedHashSet遵从先入先出的原则
        set.remove(minNode);                        //因此，在拥有多个最小freq时，通过LinkedHashSet可以删除最早的结点
        cache.remove(minNode.key);
        size--;
    }

    void addNode(Node node) {
        cache.put(node.key, node);
        size++;
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(freq, set);
        }
        set.add(node);
        min = freq;
    }

    void addFreq(Node node) {
        //从原链表中删除node
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        set.remove(node);
        if (freq == min && set.size() == 0) {
            min = freq + 1;     //维护min变量
        }
        node.freq++;
        freq++;
        //向新链表中加入node
        set = freqMap.get(freq);
        if (set == null) {    //不存在则新建
            set = new LinkedHashSet<>();
            freqMap.put(freq, set);
        }
        set.add(node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
