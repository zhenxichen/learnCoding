public class LeetCode79 {
    /* LeetCode 79 单词搜索 */
    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * 提示：
     * · board 和 word 中只包含大写和小写英文字母。
     * · 1 <= board.length <= 200
     * · 1 <= board[i].length <= 200
     * · 1 <= word.length <= 10^3
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[] visited = new boolean[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int index,
                    int i, int j, boolean[] visited) {
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        if (visited[i * n + j]) {
            return false;
        }
        char curr = word.charAt(index);
        if (board[i][j] != curr) {
            return false;
        }
        visited[i * n + j] = true;
        if (index == word.length() - 1) {
            return true;
        }
        if (dfs(board, word, index + 1, i + 1, j, visited)) {
            return true;
        }
        if (dfs(board, word, index + 1, i - 1, j, visited)) {
            return true;
        }
        if (dfs(board, word, index + 1, i, j + 1, visited)) {
            return true;
        }
        if (dfs(board, word, index + 1, i, j - 1, visited)) {
            return true;
        }
        visited[i * n + j] = false;     // 最后若未提前返回，说明未选用该点，则将visited重新设为false
        return false;
    }
}