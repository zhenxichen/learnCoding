import java.util.Random;

public class LeetCode470 {
    /* LeetCode 470 用 Rand7() 实现 Rand10() */
    /**
     * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
     * 不要使用系统的 Math.random() 方法。
     */
}

class Solution extends SolBase {
    public int rand10() {
        int a = 0, b = 0;
        int index = 0;
        while (true) {
            a = rand7();
            b = rand7();
            index = b + (a - 1) * 7;    // 不直接相乘是因为直接相乘不同数出现的概率不等
            if (index <= 40) {      // 若得到的数小于40，就直接返回
                return 1 + (index - 1) % 10;    // 这里用1 + (index - 1) % 10是为了防止出现0
            }
            a = index - 40;     // 得到一个1-9的随机数
            b = rand7();
            index = b + (a - 1) * 7;        // 得到一个1-63的随机数
            if (index <= 60) {
                return 1 + (index - 1) % 10;
            }
            a = index - 60;
            b = rand7();
            index = b + (a - 1) * 7;        // 得到一个1-21的随机数
            if (index <= 20) {
                return 1 + (index - 1) % 10;
            }
        }
    }
}

class SolBase {
    Random random = new Random();
    public int rand7() {
        return random.nextInt(7) + 1;
    }
}