public class Leetcode289 {
    /* Leetcode 289 Game of Life */

    /**
     * According to the Wikipedia's article: "The Game of Life, also known simply as Life,
     * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
     * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
     * using the following four rules (taken from the above Wikipedia article):
     * 1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * 2. Any live cell with two or three live neighbors lives on to the next generation.
     * 3. Any live cell with more than three live neighbors dies, as if by over-population.
     * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * Write a function to compute the next state (after one update) of the board given its current state.
     * The next state is created by applying the above rules simultaneously to every cell in the current state,
     * where births and deaths occur simultaneously.
     */
    //直观解法：另外定义一个二维数组用来保存初始状态
    //用新数组来保存初始状态而不是用来进行变化的好处在于可以减少将新数组赋值回旧数组的步骤
    //节省空间的解法：可以引入额外的状态（如-1等），这样可以直接在原数组上进行修改
    //从而将空间复杂度降至O(1)
    //这种方法以后有机会再做吧，这次就先写的一开始的想法，也就是时间复杂度和空间复杂度均为O(mn)的解
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] grid = new int[n][m];   //首先，我们需要另外建一个二维数组来保存初始状态，因为每个细胞的生死是同时发生的
        int live = 0;                   //用于记录周围活细胞数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                live = 0;
                for (int k = -1; k < 2; k++) {
                    if (i + k < 0 || i + k >= n) {
                        continue;
                    }
                    for (int l = -1; l < 2; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        if (j + l < 0 || j + l >= m) {
                            continue;
                        }
                        if (grid[i + k][j + l] == 1) {
                            live++;
                        }
                    }
                }
                if (grid[i][j] == 1) {
                    if (live < 2) {
                        board[i][j] = 0;       //Condition 1
                    } else if (live > 3) {
                        board[i][j] = 0;       //Condition 3
                    }
                }else{                         //grid[i][j]==0
                    if(live==3){
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
}
