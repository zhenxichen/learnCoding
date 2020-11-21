package Greedy;

public class LeetCode121 {
    /* LeetCode 121 买卖股票的最佳时机 */
    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * 注意：你不能在买入股票前卖出股票。
     */
    public static void main(String[] args) {
        Solution121 s = new Solution121();
        s.maxProfit(new int[]{7, 1, 5, 3, 6, 4});   // 5
    }
}

class Solution121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0) { return 0; }
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }
}
