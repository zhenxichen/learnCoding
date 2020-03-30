# Leetcode 175 Combine Two Tables
# 这题主要在于Outer Join 和 Inner Join的区别
# Inner Join必须至少有一项匹配才会返回行
# 而Outer Join则会返回左表(Left Join)或右表(Right Join)所有的行
# Write your MySQL query statement below
SELECT FirstName, LastName, City, State
FROM Person AS P LEFT JOIN Address AS A
ON P.PersonId=A.PersonId