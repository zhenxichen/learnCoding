public class Shopee3 {
    /**
     * 给出一个数组，你需要找到在这个数组中的“平衡点”。
     * 一个数组的“平衡点”满足 在它左边的所有数字的和等于在它右边的所有数字的和（不包括平衡点本身）。
     * 你的代码应该返回平衡数的下标，如果平衡数存在多个，返回最小的下标。
     * 1. 保证存在一个可行解
     * 2. 3 <= length(inputArray) <= 10^5
     * 3. 1 <= inputArray[i] <= 2*10^4, where 0 <= i < length(inputArray)
     */

    class Solution3 {
        /**
         * Note: 类名、方法名、参数名已经指定，请勿修改
         *
         *
         * find balanced index for an input array
         * @param inputArray int整型一维数组 the input array
         * @return int整型
         */
        public int findBalancedIndex(int[] inputArray) {
            // write code here
            long sum = 0;
            int n = inputArray.length;
            for (int i = 0; i < n; i++) {
                sum += inputArray[i];
            }
            if (sum - inputArray[0] == 0) {
                return 0;
            }
            long leftSum = 0;
            long rightSum = sum - inputArray[0];
            for (int i = 1; i < n; i++) {
                leftSum += inputArray[i - 1];
                rightSum -= inputArray[i];
                if (leftSum == rightSum) {
                    return i;
                }
            }
            return -1;
        }
    }
}

