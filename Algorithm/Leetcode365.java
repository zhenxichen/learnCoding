public class Leetcode365 {
    /* Leetcode 365 Water and Jug Problem */

    /**
     * You are given two jugs with capacities x and y litres.
     * There is an infinite amount of water supply available.
     * You need to determine whether it is possible to measure exactly z litres using these two jugs.
     * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
     * Operations allowed:
     * Fill any of the jugs completely with water.
     * Empty any of the jugs.
     * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
     */
    //这里采用的是数学解：
    //实际上就是每次只能加上或减去x, y, 即将问题转换为 ax+by = z是否有解
    //由Bézout's identity, 若a,b是整数,且gcd(a,b)=d，那么对于任意的整数x, y, 都有ax+by是d的倍数
    //因此，问题可以再转化为，z是否为x,y的最大公约数的倍数
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        //先处理特殊情况
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return (z == 0 || x + y == z);
        }
        //正常情况
        int d = gcd(x, y);
        return z % d == 0;
    }

    int gcd(int a, int b) {
        //貌似没找到求最大公约数的函数- -自己现场造轮子叭
        //顺带复习一下数学基础了
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
    //执行用时 : 0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗 :36.4 MB, 在所有 Java 提交中击败了13.93%的用户
    //可能是因为gcd方法运用了递归，导致内存消耗较大？
}
