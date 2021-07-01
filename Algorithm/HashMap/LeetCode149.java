import java.util.HashMap;
import java.util.Map;

public class LeetCode149 {
    /* LeetCode 149 直线上最多的点数 */

    /**
     * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
     * 提示：
     * · 1 <= points.length <= 300
     * · points[i].length == 2
     * · -104 <= xi, yi <= 104
     * · points 中的所有点 互不相同
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        int ans2 = s.maxPoints(new int[][]{{1, 3}, {3, 3}, {-5, 3}});
        System.out.println(ans2);
    }
}

// 解法：哈希表
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        // 如果小于等于两个点，则直接返回
        if (n <= 2) {
            return n;
        }
        int ans = 0;
        // 遍历所有点
        for (int i = 0; i < n; i++) {
            // 剪枝：当我们找到一条直线经过了图中超过半数的点或是剩余数量的点时，我们即可以确定该直线即为经过最多点的直线
            if (ans >= n - i || ans > n / 2) {
                break;
            }
            // 用x和y表示的斜率作为key，存储不同斜率的直线数量
            // 注意：由于Java没有重写数组的equals方法，因此不能直接用int[]作为key
            Map<Integer, Integer> count = new HashMap<>();
            // 当我们枚举到点 i 时，我们只需要考虑编号大于 i 的点到点 i 的斜率
            for (int j = i + 1; j < n; j++) {
                int deltaX = points[i][0] - points[j][0];
                int deltaY = points[i][1] - points[j][1];
                // 将分子和分母最简化来表示斜率
                if (deltaX == 0) {
                    deltaY = 1;
                } else if (deltaY == 0) {
                    deltaX = 1;
                } else {
                    // 对于符号不同的情况，统一规定分子为正数
                    if (deltaY < 0) {
                        deltaY = -deltaY;
                        deltaX = -deltaX;
                    }
                    // 求最大公约数
                    int greatestCommonDivisor = gcd(Math.abs(deltaX), Math.abs(deltaY));
                    // 用最大公约数化简分式
                    deltaX /= greatestCommonDivisor;
                    deltaY /= greatestCommonDivisor;
                }
                // 根据斜率更新Map
                int xAndY = 20000 * deltaX + deltaY;        // 根据数据范围，可以用20000*x+y表示x和y
                count.put(xAndY, count.getOrDefault(xAndY, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                ans = Math.max(ans, entry.getValue() + 1);      // 同起点, 同斜率的直线数+1即为该直线上的点数
            }
        }
        return ans;
    }

    // 辗转相除法求最大公约数
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}