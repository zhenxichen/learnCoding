import java.util.ArrayList;
import java.util.List;

public class LeetCode207 {
    /* LeetCode 207 课程表 */

    /**
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * 示例 1:
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     * 提示：
     * 1. 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
     * 2. 你可以假定输入的先决条件中没有重复的边。
     * 3. 1 <= numCourses <= 10^5
     */
    // 本质上是图论问题：如果在图中存在环，则不能完成，如不存在环，则可以完成
    // 解法： DFS
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    int[] visited;
    List<List<Integer>> edges;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if(visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    void dfs(int u) {
        visited[u] = 1;
        for(int vertex: edges.get(u)) {
            if(visited[vertex] == 0) {
                dfs(vertex);
                if(!valid) {
                    return;
                }
            } else if (visited[vertex] == 1){
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }
}
