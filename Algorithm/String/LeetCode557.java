public class LeetCode557 {
    /* LeetCode 557 反转字符串中的单词 III */
    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例：
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     */
    static class Solution {
        public String reverseWords(String s) {
            char[] str = s.toCharArray();
            int n = str.length;
            int left = 0, right = 0;
            while (right < n) {
                while (right != n && str[right] != ' ') {
                    right++;
                }
                int r = right - 1;
                while (left < r) {
                    swap(str, left, r);
                    left++;
                    r--;
                }
                right = right + 1;
                left = right;
            }
            return new String(str);
        }

        private void swap(char[] arr, int i, int j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String res = s.reverseWords("Let's take LeetCode contest");
        System.out.println(res);
    }
}
