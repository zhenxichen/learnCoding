# Leetcode 181 Employees Earning More Than Their Managers
# 这道题目体现的主要知识点大概就是可以将一张表From两次
# Write your MySQL query statement below
select a.Name as Employee
from Employee as a, Employee as b
where a.ManagerId=b.Id and a.Salary>b.Salary