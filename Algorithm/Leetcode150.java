/* Leetcode 150 Evaluate Reverse Polish Notation */

import java.util.Stack;
import java.lang.Integer;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 **/
//这题关键是C++字符串转整数有点麻烦，于是就用JAVA写了

public class Leetcode150 {
    public static void main(String[] args){
        Solution solution = new Solution();
    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<Integer>();
        for(String string:tokens){
            switch(string){
                case "+":{
                    int op2 = nums.pop();
                    int op1 = nums.pop();
                    nums.push(op1+op2);
                    break;
                }
                case "-":{
                    int op2 = nums.pop();
                    int op1 = nums.pop();
                    nums.push(op1-op2);
                    break;
                }
                case "*":{
                    int op2 = nums.pop();
                    int op1 = nums.pop();
                    nums.push(op1*op2);
                    break;
                }
                case "/":{
                    int op2 = nums.pop();
                    int op1 = nums.pop();
                    nums.push(op1/op2);
                    break;
                }
                default:{
                    nums.push(Integer.parseInt(string));
                    break;
                }
            }
        }      //string-loop
        return nums.pop();
    }
}
