public class LeetCode765 {
    /* LeetCode 765 情侣牵手 */
    /**
     * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。
     * 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。一次交换可选择任意两人，让他们站起来交换座位。
     * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
     * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
     * 示例 1:
     * 输入: row = [0, 2, 1, 3]
     * 输出: 1
     * 解释: 我们只需要交换row[1]和row[2]的位置即可。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (i != parent[i]) { i = find(parent[i]); }
            return parent[i];
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
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        UnionFind uf = new UnionFind(2 * n);
        // 先将情侣之间连接起来
        for (int i = 0; i < n; i++) {
            uf.union(2 * i, 2 * i + 1);
        }
        // 再将坐错的两对连接起来
        for (int i = 0; i < n; i++) {
            if (row[2*i] / 2 != row[2*i+1] / 2) {
                uf.union(row[2*i], row[2*i+1]);
            }
        }
        return n - uf.getCount();
    }
}