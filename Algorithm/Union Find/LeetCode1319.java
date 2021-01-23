public class LeetCode1319 {
    /* LeetCode 1319 连通网络的操作次数 */
    /**
     * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。
     * 线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
     * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
     * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
     * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 解法：并查集
class Solution {
    class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
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
                if (rank[iRoot] > rank[jRoot]) {
                    parent[jRoot] = iRoot;
                } else if (rank[jRoot] > rank[iRoot]) {
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

    public int makeConnected(int n, int[][] connections) {
        if (n - 1 > connections.length) { return -1; }      // 若线数量不够，则不可能全连接, 否则就一定可以全连接
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < connections.length; i++) {
            uf.union(connections[i][0], connections[i][1]);
        }
        return uf.getCount() - 1;   // 每次操作将两个分区相连
    }
}