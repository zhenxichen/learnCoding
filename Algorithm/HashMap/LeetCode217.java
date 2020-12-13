import java.util.HashSet;
import java.util.Set;

public class LeetCode217 {
    /* LeetCode 217 存在重复元素 */
    /**
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution solution = new Solution();
    }
}

// 优化：set.add()方法会返回一个boolean
// 如果Set集合中不包含要添加的对象，则添加对象并返回true；否则返回false。
// 因此，只需要判断add()方法的结果，而不需要额外判断一次contains()
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}

// 解法：HashSet
class Solution1 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }
}
