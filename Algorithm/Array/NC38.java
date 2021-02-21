import java.util.ArrayList;

public class NC38 {
    /* NC 38 螺旋矩阵 */
    /**
     * 给定一个m x n大小的矩阵（m行，n列），按螺旋的顺序返回矩阵中的所有元素。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        s.spiralOrder(new int[][]{{6, 9, 7}});
        System.out.println(s.spiralOrder(new int[][]{{3}, {2}}));
    }
}

class Solution {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) { return ans; }
        int n = matrix[0].length;
        int left = 0, right = n - 1;
        int top = 0, bottom = m - 1;
        while (top < (m + 1) / 2 && left < (n + 1) / 2) {
            // left -> right
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            // top -> bottom
            for (int i = top + 1; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            // right -> left （注意如果top=bottom，则不需要往左走）
            for (int i = right - 1; top != bottom && i >= left; i--) {
                ans.add(matrix[bottom][i]);
            }
            // bottom -> top （同理，这里如果left=right则不需要往上走）
            for (int i = bottom - 1; left != right && i > top; i--) {
                ans.add(matrix[i][left]);
            }
            top++; bottom--; left++; right--;
        }
        return ans;
    }
}