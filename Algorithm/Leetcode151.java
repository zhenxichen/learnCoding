import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Leetcode151 {
    /* Leetcode 151 Reverse Words in a String */

    /**
     * Given an input string, reverse the string word by word.
     * Example 1:
     * Input: "the sky is blue"
     * Output: "blue is sky the"
     */
    //这题我用了两种做法：一种是用JAVA自带的split等方法来实现，另一种是用栈来实现
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution s = new Solution();
        s.reverseWords("the sky is blue");
    }
}

class Solution {
    //运用栈实现
    //属于数据结构题型
    //似乎可以用双端队列来改进，从而进一步提高运行效率，但是这里就没继续写了
    public String reverseWords(String s) {
        int left = 0;
        int right = s.length() - 1;
        StringBuilder ans = new StringBuilder();
        StringBuilder word = new StringBuilder();
        Stack<String> stack = new Stack<>();
        boolean isEmpty = false;
        while (left <= right && s.charAt(left) == ' ') {
            left++;     //去除左侧空格
        }
        while (left <= right && s.charAt(right) == ' ') {
            right--;    //去除右侧空格
        }
        for (int i = left; i <= right; i++) {
            char c = s.charAt(i);
            if (word.length() != 0 && c == ' ') {
                stack.push(word.toString());    //将word压栈
                word.delete(0, word.length());   //清空StringBuilder
                //word.setLength(0);            //也可以用setLength(0)方法清空
            } else if (c != ' ') {
                word.append(c);
            }
        }
        stack.push(word.toString());            //补上最后一个word
        while (!stack.isEmpty()) {
            ans.append(stack.pop());
            if(!stack.isEmpty()){
                ans.append(' ');
            }
        }
        return ans.toString();
    }
    //执行用时: 7ms, 内存占用: 40.1 MB
    //时间复杂度: O(N), 空间复杂度: O(N)
}

class Solution1 extends Solution {
    //利用JAVA自带的split等函数实现
    //属于语言运用类题型
    public String reverseWords(String s) {
        s = s.trim();   //去掉头尾的空格
        List<String> wordList = Arrays.asList(s.split("\\s+")); //以空格为分隔符
        Collections.reverse(wordList);  //将List旋转
        return String.join(" ", wordList);   //将旋转后的List再拼成字符串
    }
    //执行用时: 7ms, 内存占用: 39.9 MB
    //时间复杂度: O(N), 空间复杂度: O(N)
}