package DFS;

public class LeetCode130 {
    /* LeetCode 130 被围绕的区域 */
    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * 示例:
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
     * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     */
    public static void main(String[] args) {
        Solution130_DFS solution = new Solution130_DFS();
    }
}

// 解法一：DFS
class Solution130_DFS {
    // 以边界上的O为起点，进行DFS，将搜索到的O进行标记
    // 被标记到的O说明与边界的O相邻，则不会被围绕
    // 反之，未被标记到的O则说明被X围绕，而不与边界上的O相邻

    int m;
    int n;

    public void solve(char[][] board) {
        m = board.length;
        if (m == 0) {
            return;
        }
        n = board[0].length;
        // 对边界点进行遍历
        for (int i = 0; i < n; i++) {
            if(board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            if(board[m - 1][i] == 'O') {
                dfs(board, m - 1, i);
            }
        }
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if(board[i][n - 1] == 'O') {
                dfs(board, i, n-1);
            }
        }
        // 对整个矩阵进行遍历
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    void dfs(char[][] board, int i, int j) {
        // 递归基
        if(i >= m || i < 0 || j >= n || j < 0) {
            return;
        }
        if(board[i][j] != 'O') {
            return;
        }
        // 操作
        board[i][j] = 'A';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}
