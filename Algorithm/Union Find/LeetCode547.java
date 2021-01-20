public class LeetCode547 {
    /* LeetCode 547 省份数量 */
    /**
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。
     * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，
     * 其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     * 示例 1：
     * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
     * 输出：2
     * 提示：
     * 1 <= n <= 200
     * n == isConnected.length
     * n == isConnected[i].length
     * isConnected[i][j] 为 1 或 0
     * isConnected[i][i] == 1
     * isConnected[i][j] == isConnected[j][i]
     */
    public static void main(String[] args) {
        Solution_DFS s1 = new Solution_DFS();
        Solution_UnionFind s2 = new Solution_UnionFind();
    }
}

// 解法一：DFS
class Solution_DFS {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;     // 1 <= n <= 200
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += dfs(isConnected, i, visited);
        }
        return count;
    }

    int dfs(int[][] isConnected, int i, boolean[] visited) {
        if (visited[i]) {
            return 0;
        }
        visited[i] = true;
        for (int j = 0; j < isConnected[i].length; j++) {
            if (isConnected[i][j] == 1) {
                dfs(isConnected, j, visited);
            }
        }
        return 1;
    }
}

// 解法二：并查集
class Solution_UnionFind {

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            while(parent[i] != i) {
                i = parent[i];
            }
            return parent[i];
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if(rootI != rootJ) {
                if(rank[rootI] > rank[rootJ]) {
                    parent[rootJ] = rootI;
                } else if (rank[rootI] < rank[rootJ]) {
                    parent[rootI] = rootJ;
                } else {
                    parent[rootJ] = rootI;
                    rank[rootI]++;
                }
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if(isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
}