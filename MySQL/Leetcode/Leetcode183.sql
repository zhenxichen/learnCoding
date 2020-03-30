# Leetcode 183 Customers Who Never Order
# 知识点：not in
# Write your MySQL query statement below
SELECT Name as Customers FROM Customers
WHERE Id not in(
    SELECT CustomerId FROM Orders
);