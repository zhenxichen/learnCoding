public class LeetCode684 {
    /* LeetCode 684 冗余连接 */

    /**
     * 在本问题中, 树指的是一个连通且无环的无向图。
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。
     * 如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
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
            while (i != parent[i]) {
                i = parent[i];
            }
            return i;
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot != jRoot) {
                if(rank[iRoot] > rank[jRoot]) {
                    parent[jRoot] = iRoot;
                } else if(rank[iRoot] < rank[jRoot]) {
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

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] ans = new int[2];
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            if(uf.find(edges[i][0] - 1) == uf.find(edges[i][1] - 1)) {
                ans = edges[i];
            }
            uf.union(edges[i][0] - 1, edges[i][1] - 1);
        }
        return ans;
    }
}