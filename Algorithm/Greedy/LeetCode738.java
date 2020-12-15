public class LeetCode738 {
    /* LeetCode 738 单调递增的数字 */
    /**
     * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     * 示例 1:
     * 输入: N = 10
     * 输出: 9
     * 说明: N 是在 [0, 10^9] 范围内的一个整数。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

// 解法：贪心
class Solution {
    public int monotoneIncreasingDigits(int N) {
        String str = Integer.toString(N);
        char[] arr = str.toCharArray();
        int i = 1;
        for (; i < arr.length && arr[i - 1] <= arr[i] ; i++) { }   // 找到开始不符合递增规则的位置
        if(i < arr.length) {
            // 将不符合规则的位置大小-1
            // 使用while是为了防止修改了arr[i]后导致arr[i] < arr[i - 1]
            while (i >= 0 && arr[i - 1] > arr[i]) {
                arr[i]--;
                i--;
            }
        }
        // 之后，将之后的数全改为9
        for (i = i + 1; i < arr.length; i++) {
            arr[i] = '9';
        }
        return Integer.valueOf(String.valueOf(arr));
    }
}