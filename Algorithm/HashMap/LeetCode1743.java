import java.util.*;

public class LeetCode1743 {
    /* LeetCode 1743 从相邻元素对还原数组 */
    /**
     * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
     * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，
     * 其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
     * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，
     * 存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
     * 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
     */

    // 解法：哈希表
    public static class Solution {
        public int[] restoreArray(int[][] adjacentPairs) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            // 将左右连接关系存入map当中
            for (int[] adjacentPair: adjacentPairs) {
                map.putIfAbsent(adjacentPair[0], new ArrayList<>());    // 若不存在则插入
                map.putIfAbsent(adjacentPair[1], new ArrayList<>());
                map.get(adjacentPair[0]).add(adjacentPair[1]);
                map.get(adjacentPair[1]).add(adjacentPair[0]);
            }
            // 遍历EntrySet，找到数组的开头
            int begin = -1;
            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                // 如果List中只有一个对象，说明是数组的开头或结尾
                if (entry.getValue().size() == 1) {
                    begin = entry.getKey();
                    break;
                }
            }
            // 从起点开始，遍历下一个点
            int n = adjacentPairs.length + 1;
            int[] ans = new int[n];
            ans[0] = begin;
            ans[1] = map.get(begin).get(0);
            for (int i = 2; i < n; i++) {
                int nex = 0;
                for (Integer point : map.get(ans[i - 1])) {
                    if (point == ans[i - 2]) {
                        continue;
                    }
                    nex = point;
                }
                ans[i] = nex;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] ans = s.restoreArray(new int[][]{{2,1},{3,4},{3,2}});
        System.out.println(Arrays.toString(ans));
    }
}
