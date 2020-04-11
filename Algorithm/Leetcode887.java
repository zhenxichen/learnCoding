import java.util.HashMap;
import java.util.Map;

public class Leetcode887 {
    /* Leetcode 887 Super Egg Drop */

    /**
     * You are given K eggs, and you have access to a building with N floors from 1 to N.
     * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
     * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break,
     * and any egg dropped at or below floor F will not break.
     * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
     * Your goal is to know with certainty what the value of F is.
     * What is the minimum number of moves that you need to know with certainty what F is,
     * regardless of the initial value of F?
     * Note:
     * 1 <= K <= 100
     * 1 <= N <= 10000
     */
    //扔鸡蛋问题 -> 经典动态规划问题
    //解法：动态规划
    //可将状态表示为(K,N), 在X楼扔鸡蛋
    //若鸡蛋碎了，则状态变为(K-1,X-1)，即只可能在X楼以下的楼层找到答案
    //若鸡蛋没碎，则状态变为(K,N-X)，即只可能在X楼以上的楼层找到答案
    //由于我们并不知道真正的F值，因此我们必须保证【鸡蛋碎了之后接下来需要的步数】和【鸡蛋没碎之后接下来需要的步数】二者的最大值最小
    //由此，即可得出状态转移方程：
    //dp(K,N) = 1 + min{max{dp(K-1,X-1), dp(K,N-X)}}
    //那么剩下的就是求X的问题，在K>1且N!=0的情况下可以采用二分搜索
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int superEggDrop(int K, int N) {
        return dp(K, N);
    }

    Map<Integer, Integer> memo = new HashMap<>();    //备忘录，用于消除重复子问题

    int dp(int k, int n) {
        if (!memo.containsKey(k + n * 100)) {
            int ans = 0;
            if (n == 0) {       //第0层不需要试
                ans = 0;
            } else if (k == 1) {    //只剩一个蛋，只能一层层试
                ans = n;
            } else {              //其余情况可采用二分搜索
                int low = 1;
                int high = n;
                while (low + 1 < high) {
                    int x = (low + high) / 2;
                    //K一定的情况下，N越大，则F也越大
                    //因此, T1随X的增加而增加，T2随X增加而减小
                    //因此，要找到两者最大值的最小值，则需要找到两者的交点（或离得最近的点）
                    int t1 = dp(k - 1, x - 1);
                    int t2 = dp(k, n - x);
                    if (t1 < t2) {
                        low = x;
                    } else if (t1 > t2) {
                        high = x;
                    } else {
                        low = x;
                        high = x;
                    }
                }
                ans = 1 + Math.min(Math.max(dp(k - 1, low - 1), dp(k, n - low)),
                        Math.max(dp(k - 1, high - 1), dp(k, n - high))); //为了考虑两函数无法取到焦点的情况添加了min
            }
            memo.put(k + n * 100, ans);
        }
        return memo.get(k + n * 100);
    }
}