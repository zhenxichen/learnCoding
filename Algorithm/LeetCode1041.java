public class LeetCode1041 {
    /* LeetCode 1041 困于环中的机器人 */
    /**
     * 在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。机器人可以接受下列三条指令之一：
     * · "G"：直走 1 个单位
     * · "L"：左转 90 度
     * · "R"：右转 90 度
     * 机器人按顺序执行指令 instructions，并一直重复它们。
     * 只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        boolean ans = s.isRobotBounded("RGL");
        System.out.println(ans);
    }
}

class Solution {
    public boolean isRobotBounded(String instructions) {
        int dir = 0;            // 记录方向
        int x = 0, y = 0;       // 记录坐标
        for (char c: instructions.toCharArray()) {
            if (c == 'G') {
                switch (dir) {
                    case 3: {
                        x++;
                        break;
                    }
                    case 2: {
                        y--;
                        break;
                    }
                    case 1: {
                        x--;
                        break;
                    }
                    case 0: {
                        y++;
                        break;
                    }
                }
            } else if (c == 'L') {
                dir = ((dir + 1) % 4 + 4) % 4;      // 因为Java对负数取模的结果也为负数，所以需要将结果加4后再取模一次
            } else if (c == 'R') {
                dir = ((dir - 1) % 4 + 4) % 4;
            } else {
                return false;
            }
        }
        if ((x == 0 && y == 0) || dir != 0) {
            return true;
        }
        return false;
    }
}