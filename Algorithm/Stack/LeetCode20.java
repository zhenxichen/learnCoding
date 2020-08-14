import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LeetCode20 {
    /* LeetCode 20 有效的括号 */

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 1. 左括号必须用相同类型的右括号闭合。
     * 2. 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');
        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                stack.push(c);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char pair = stack.pop();
                if(pairs.get(pair) != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

// 额外学习： JDK的Stack类，及其父类Vector
// 用IDEA打开，Ctrl + 左键点击代码中的Stack即可访问其源码
// Vector和ArrayList的区别： Vector是线程安全的，ArrayList不是
// Vector通过一个数组保存数据，同时维护elementCount, capacityIncrement两个变量
// 前者表示vector中的元素数，后者表示增长系数（决定每次调用grow方法增长多少个变量）
// Vector是完全线程安全的吗？
// 不是，Vector实现了单个方法的同步，但是由于不同方法之间的操作存在间隙，即连续调用的多个方法的组合不具有原子性
