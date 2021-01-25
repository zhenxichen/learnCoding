public class LeetCode990 {
    /* LeetCode 990 等式方程的可满足性 */
    /**
     * 给定一个由表示变量之间关系的字符串方程组成的数组，
     * 每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
     * 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
     * 示例 1：
     * 输入：["a==b","b!=a"]
     * 输出：false
     * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
     * 提示：
     * 1. 1 <= equations.length <= 500
     * 2. equations[i].length == 4
     * 3. equations[i][0] 和 equations[i][3] 是小写字母
     * 4. equations[i][1] 要么是 '='，要么是 '!'
     * 5. equations[i][2] 是 '='
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            while(i != parent[i]) {
                i = parent[i];
            }
            return i;
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot != jRoot) {
                if (rank[iRoot] > rank[jRoot]) {
                    parent[jRoot] = iRoot;
                } else if (rank[iRoot] < rank[jRoot]) {
                    parent[iRoot] = jRoot;
                } else {
                    parent[jRoot] = iRoot;
                    rank[iRoot]++;
                }
            }
        }

        public boolean isUnited(int i, int j) {
            return find(i) == find(j);
        }
    }
    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        UnionFind uf = new UnionFind(26);   // 因为变量一定是小写字母
        // 先遍历等式，建立结点的连接
        for (int i = 0; i < n; i++) {
            // 等式格式是固定的
            if (equations[i].charAt(1) == '=') {
                uf.union(equations[i].charAt(0) - 'a', equations[i].charAt(3) - 'a');
            }
        }
        // 再遍历不等式，检验是否存在矛盾
        for (int i = 0; i < n; i++) {
            if (equations[i].charAt(1) == '!') {
                if (uf.isUnited(equations[i].charAt(0) - 'a', equations[i].charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}
