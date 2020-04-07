public class Leetcode48 {
    /* Leetcode 48 Rotate Image */

    /**
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * Note:
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.
     */
    //转换公式为m[i][j] = m[j][n-1-i]
    // => 可分为两步：转置m[i][j] => m[j][i], 翻转m[j][i] => m[j][n-i-1]
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int temp;
        //先转置
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //再按行翻转
        for(int i=0;i<n;i++){
            for(int j=0;j<n/2;j++){
                int loc = n-j-1;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][loc];
                matrix[i][loc] = temp;
            }
        }
    }
}
