import java.util.*;

public class LeetCode974 {
    /* LeetCode 974 和可被 K 整除的子数组 */
    /**
     * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
     */
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

// 解法：前缀和 + 哈希表
// 涉及子数组之和的题目，可以考虑使用前序和
// 判断子数组的和能否被 K 整除就等价于判断 (P[j] - P[i-1]) mod K == 0
// 根据同余定理，只要P[j] mod K == P[i−1] mod K，就可以保证上面的等式成立。
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int pre = 0;
        int n = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            pre += A[i];
            int mod = (pre % K + K) % K;
            int count = map.getOrDefault(mod, 0);
            ans += count;
            map.put(mod, count + 1);
        }
        return ans;
    }
}