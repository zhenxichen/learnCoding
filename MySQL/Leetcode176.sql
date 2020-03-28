#Leetcode 176 Second Highest Salary
#Write a SQL query to get the second highest salary from the Employee table.
#If there is no second highest salary, then the query should return null.

# Write your MySQL query statement below
SELECT(
    SELECT DISTINCT Salary FROM Employee
    ORDER BY Salary DESC
    LIMIT 1 OFFSET 1
) AS SecondHighestSalary;