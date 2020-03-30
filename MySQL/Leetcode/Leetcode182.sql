# Leetcode 182 Duplicate Emails
# 解法一：GROUP BY + 临时表
# Write your MySQL query statement below
#SELECT Email FROM(
#    SELECT Email, COUNT(Email) as num
#    FROM Person
#    GROUP BY Email
#) as temp
#WHERE 1<num
# 解法二：GROUP BY + HAVING(速度更快)
# Write your MySQL query statement below
SELECT Email FROM Person
GROUP BY Email
HAVING 1 < COUNT(Email)