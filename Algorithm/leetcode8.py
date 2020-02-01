#Leetcode 8 String to Integer (atoi) 
#看到题解中正则表达式一行解的思路，照着写了一个

import re

class Solution:
    def myAtoi(self, str: str) -> int:
        return max(min(int(*re.findall(r'^[+-]?\d+',str.lstrip())),2**31-1),-2**31)
