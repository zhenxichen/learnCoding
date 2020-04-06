public class Leetcode292 {
    /* Leetcode 292 Nim Game */

    /**
     * You are playing the following Nim Game with your friend: There is a heap of stones on the table,
     * each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner.
     * You will take the first turn to remove the stones.
     * Both of you are very clever and have optimal strategies for the game.
     * Write a function to determine whether you can win the game given the number of stones in the heap.
     */
    //数学解
    //这题大概是我做过题解最短的leetcode题目了吧- -
    //若n的数量是4的整数倍，则先手的人必输
    //反之，则先手的人必赢
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
}
