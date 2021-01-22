import java.util.*;


public class NC510 {
    /* 牛客 NC510 牛妹的礼物*/
    /**
     * 众所周知，牛妹有很多很多粉丝，粉丝送了很多很多礼物给牛妹，牛妹的礼物摆满了地板。
     * 地板是N\times MN×M的格子，每个格子有且只有一个礼物，牛妹已知每个礼物的体积。
     * 地板的坐标是左上角(1,1)  右下角（N, M）。
     * 牛妹只想要从屋子左上角走到右下角，每次走一步，每步只能向下走一步或者向右走一步或者向右下走一步
     * 每次走过一个格子，拿起（并且必须拿上）这个格子上的礼物。
     * 牛妹想知道，她能走到最后拿起的所有礼物体积最小和是多少？
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 解法：动态规划
class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param presentVolumn int整型二维数组 N*M的矩阵，每个元素是这个地板砖上的礼物体积
     * @return int整型
     */
    public int selectPresent (int[][] presentVolumn) {
        // write code here
        if (presentVolumn == null) { return 0; }
        int m = presentVolumn.length;
        if (m == 0) { return 0; }
        int n = presentVolumn[0].length;
        if (n == 0) { return 0; }
        int[][] dp = new int[m][n];
        dp[0][0] = presentVolumn[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + presentVolumn[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + presentVolumn[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] =
                        Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + presentVolumn[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}