import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode887 {
    /* LeetCode 887 鸡蛋掉落 */

    /**
     * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
     * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     * 你的目标是确切地知道 F 的值是多少。
     * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     * 示例 1：
     * 输入：K = 1, N = 2
     * 输出：2
     * 解释：
     * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
     * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
     * 如果它没碎，那么我们肯定知道 F = 2 。
     * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 解法：动态规划 + 二分搜索
// 递推公式： dp(K, N) = min(max(dp(K-1,X-1), dp(K,N-X))) + 1
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i < K + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 设定边界条件
        for (int i = 0; i < N + 1; i++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }
        for (int i = 0; i < K + 1; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        // 递推
        for (int i = 2; i < K + 1; i++) {
            for (int j = 2; j < N + 1; j++) {
                int low = 1;
                int high = j;
                while (low + 1 < high) {
                    int mid = (low + high) / 2;
                    int broken = dp[i - 1][mid - 1];
                    int notBroken = dp[i][j - mid];
                    // 要使上述两者的最大值最小
                    // 由于我们知道broken的值随X(即mid)的值增大而增大
                    // notBroken的值则关于X单调递减
                    // 因此，要使两者的最大值最小，我们可以通过二分法进行查找
                    if (broken > notBroken) {
                        high = mid;
                    } else if (broken < notBroken) {
                        low = mid;
                    } else {
                        low = high = mid;
                    }
                }
                // 由于函数是离散的，所以可能并不存在交点，需要对交点两侧的low和high都进行计算，再取其中较小值
                dp[i][j] = Math.min(Math.max(dp[i - 1][low - 1], dp[i][j - low]),
                        Math.max(dp[i - 1][high - 1], dp[i][j - high])) + 1;
            }
        }
        return dp[K][N];
    }
}