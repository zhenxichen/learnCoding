public class LeetCode208 {
    /* LeetCode 208 实现 Trie (前缀树) */
    /**
     * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
     * 示例:
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 true
     * trie.search("app");     // 返回 false
     * trie.startsWith("app"); // 返回 true
     * trie.insert("app");
     * trie.search("app");     // 返回 true
     * 说明:
     * 你可以假设所有的输入都是由小写字母 a-z 构成的。
     * 保证所有输入均为非空字符串。
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
    }
}

class Trie {
    class TrieNode {
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd = false;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char key) {
            return links[key - 'a'] != null;
        }

        public TrieNode get(char c) {
            return links[c - 'a'];
        }

        public void put(char key, TrieNode node) {
            links[key - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!node.containsKey(c)) {
                return false;
            }
            node = node.get(c);
        }
        return node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!node.containsKey(c)) {
                return false;
            }
            node = node.get(c);
        }
        return true;
    }
}