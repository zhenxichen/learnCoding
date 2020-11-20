import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode210 {
    /* LeetCode 210 课程表 II */
    /**
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
     * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
     * 示例 1:
     * 输入: 2, [[1,0]]
     * 输出: [0,1]
     * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findOrder(2, new int[][] {{1, 0}});
    }
}

class Solution {
    List<List<Integer>> edges;  // 存储边
    int[] visited;      // 标记搜索状态 0: 未搜索 1: 搜索中 2: 已搜索
    Stack<Integer> stack;
    boolean valid = true;   // 标记是否存在环

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化变量
        visited = new int[numCourses];
        edges = new ArrayList<>();
        stack = new Stack<>();
        for(int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for(int i = 0; i < prerequisites.length; i++) {
            int start = prerequisites[i][1];
            int end = prerequisites[i][0];
            edges.get(start).add(end);
        }
        // DFS
        for(int i = 0; i < numCourses; i++) {
            dfs(i);
            if(!valid) {
                return new int[0];
            }
        }
        int[] res = new int[numCourses];
        for(int i = 0; i <numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    void dfs(int i) {
        List<Integer> edge = edges.get(i);
        if(edge.size() == 0 && visited[i] == 0) {  // 若出度为0
            visited[i] = 2;     // 标记为已遍历
            stack.push(i);      // 压栈
            return;
        }
        if(visited[i] == 1) {   // 已处于遍历中，说明存在环
            valid = false;
            return;
        }
        if(visited[i] == 2) {
            return;
        }
        visited[i] = 1;     // 标记为搜索中
        for(int v: edge) {  // 对相邻点进行DFS
            dfs(v);
        }                   // 遍历完成后
        visited[i] = 2;     // 标记为已遍历
        stack.push(i);      // 压栈
    }
}
