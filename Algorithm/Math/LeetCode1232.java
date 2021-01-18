public class LeetCode1232 {
    /* LeetCode 1232 缀点成线 */
    /**
     * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
     * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
     * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
     * 示例 1：
     * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
     * 输出：true
     * 提示：
     *  2 <= coordinates.length <= 1000
     *  coordinates[i].length == 2
     *  -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
     *  coordinates 中不含重复的点
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 不能用斜率表示，因为斜率无法表示竖直的直线
// 因此用一般式表示直线
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX = -coordinates[0][0];
        int deltaY = -coordinates[0][1];
        int n = coordinates.length;
        // 将第一个点移动到原点，其他点对应平移
        for (int i = 0; i < n; i++) {
            coordinates[i][0] += deltaX;
            coordinates[i][1] += deltaY;
        }
        // 用原点和第一个点计算直线 Ax+By=0（A, B不全为0）
        // 将第一个点代入
        // Ax1 + By1 = 0
        // 可得A = y1 B = -x1
        int A = coordinates[1][1];
        int B = -coordinates[1][0];
        // 将后续点代入，判断是否在该直线上
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}
