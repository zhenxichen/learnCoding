public class Leetcode1323 {
    /* Leetcode 1323 Maximum 69 Number */
    /**Given a positive integer num consisting only of digits 6 and 9.
     * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).*/
    //思考：一开始的想法就是直接将最高位的6改成9
    //这题实际上就用来熟悉一下JAVA的一些操作吧
    //感觉基本算不上算法题
    public static void main(String[] args){
        Solution s = new Solution();
        //执行用时 : 1 ms, 在所有 Java 提交中击败了93.08%的用户
        //内存消耗 : 36 MB, 在所有 Java 提交中击败了100.00%的用户
        //感觉JAVA想要修改字符串中的某一个字符的话有点麻烦- -
        //本来以为效率会比较低，结果最后提交完看结果还不错
    }
}

class Solution {
    public int maximum69Number (int num) {
        String num_string = String.valueOf(num);    //会比直接使用num + ""效率更高
        char[] c = num_string.toCharArray();
        int n = c.length;
        for(int i=0;i<n;i++){
            if(c[i] == '6'){
                c[i] = '9';
                break;
            }
        }
        num_string = String.valueOf(c);
        return Integer.parseInt(num_string);
    }
}
