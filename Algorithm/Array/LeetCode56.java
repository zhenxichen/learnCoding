import java.util.*;

public class LeetCode56 {
    /* LeetCode 56 合并区间 */
    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 提示：
     * · 1 <= intervals.length <= 10^4
     * · intervals[i].length == 2
     * · 0 <= start_i <= end_i <= 10^4
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 1) {
            return intervals;
        }
        // 根据左侧端点进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0] - t1[0];
            }
        });
        // 进行合并
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                // 若列表内无元素或左侧元素大于列表最右侧元素，则直接插入
                merged.add(new int[]{left, right});
            } else {    // 否则，将右侧元素与列表最大元素对比
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}