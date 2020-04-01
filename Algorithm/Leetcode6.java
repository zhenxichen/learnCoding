import java.util.ArrayList;
import java.util.List;

public class Leetcode6 {
    /* Leetcode 6 ZigZag Conversion */

    /**
     * The string "PAYPALISHIRING" is
     * written in a zigzag pattern on a given number of rows like this:
     * (you may want to display this pattern in a fixed font for better legibility)
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * string convert(string s, int numRows);
     */
    //本来以为这题会有什么“高端”的做法，没想到他确实是这么做的- -
    //于是就当作拿来熟悉一下StringBuilder类的操作吧
    //JAVA中的String并不像C++的std::string那样容易操作，很多时候操作需要通过StringBuilder来实现
    public static void main(String[] args) {
        Solution s = new Solution();
    }
}

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean direction = false;      //false代表向下，true代表向上
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (!direction) { //向下
                if (curRow == numRows - 1) {
                    direction = true;
                    curRow--;
                } else {
                    curRow++;
                }
            } else {
                if (curRow == 0) {
                    direction = false;
                    curRow++;
                } else {
                    curRow--;
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }
}