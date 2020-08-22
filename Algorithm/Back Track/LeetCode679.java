import java.util.ArrayList;
import java.util.List;

public class LeetCode679 {
    /* LeetCode 679 24点游戏 */
    // 难度： hard
    /**
     * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
     * 示例 1:
     * 输入: [4, 1, 8, 7]
     * 输出: True
     * 解释: (8-4) * (7-1) = 24
     * 示例 2:
     * 输入: [1, 2, 1, 2]
     * 输出: False
     * 注意:
     * 1. 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
     * 2. 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。
     *    例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
     * 3. 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.judgePoint24(new int[]{4,1,8,7});
    }
}

class Solution {
    static final int TARGET = 24;
    static final double EPSILON = 1e-6;     // 浮点数结果相差10^-6以内近似认为相等
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return backtrack(list);
    }

    boolean backtrack(List<Double> list) {
        if (list.size() == 0) {
            return false;
        }
        if (list.size() == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == j) {
                    continue;
                }
                List<Double> list2 = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    if(k != i && k != j) {
                        list2.add(list.get(k));     // 将除了i,j以外的其他数放入list中
                    }
                }
                double num1 = list.get(i);
                double num2 = list.get(j);
                // 对i, j两个数做四种可能操作，然后将结果加入list
                for (int k = 0; k < 4; k++) {
                    if (k < 2 && i > j) {   // 加法和乘法满足交换律，因此只需要计算一次
                        continue;
                    }
                    if (k == ADD) {
                        list2.add(num1 + num2);
                    } else if (k == MULTIPLY) {
                        list2.add(num1 * num2);
                    } else if (k == SUBTRACT) {
                        list2.add(num1 - num2);
                    } else if (k == DIVIDE) {
                        if(num2 < EPSILON) {
                            continue;
                        }
                        list2.add(num1 / num2);
                    }
                    if(backtrack(list2)) {
                        return true;
                    }
                    list2.remove(list2.size() - 1);     // 若不符合，则将其从List中删除，尝试其他情况
               }
            }
        }
        return false;
    }
}
