package DFS;

public class LeetCode200 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void dfs(char[][] grid, int i, int j) {
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';   // 搜索到的标记为0
        int m = grid.length;
        int n = grid[0].length;
        if (i + 1 < m) {
            dfs(grid, i + 1, j);
        }
        if (i - 1 >= 0) {
            dfs(grid, i - 1, j);
        }
        if (j + 1 < n) {
            dfs(grid, i, j + 1);
        }
        if (j - 1 >= 0) {
            dfs(grid, i, j - 1);
        }
    }
}