import java.util.ArrayDeque;
import java.util.Queue;

public class Leetcode1162 {
    /* Leetcode 1162 As Far from Land as Possible */

    /**
     * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land,
     * find a water cell such that its distance to the nearest land cell is maximized and return the distance.
     * The distance used in this problem is the Manhattan distance:
     * the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
     * If no land or water exists in the grid, return -1.
     * Note:
     * 1. 1 <= grid.length == grid[0].length <= 100
     * 2. grid[i][j] is 0 or 1
     */
    //直接解法：BFS
    // => 多源BFS
    //对每个陆地区域进行BFS，最后访问到的海域即为最远海域
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        boolean haveWater = false;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});     //将所有陆地入队
                }
            }
        }
        int[] point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] > 0) {
                    continue;
                }
                grid[nextX][nextY] = grid[x][y] + 1;
                queue.add(new int[]{nextX, nextY});
                haveWater = true;
            }
        }
        if (point == null || !haveWater) {
            return -1;
        }
        return grid[point[0]][point[1]] - 1;    //最后访问的海域的距离
    }
}

