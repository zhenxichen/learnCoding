# Leetcode 182 Duplicate Emails
# Write your MySQL query statement below
SELECT Email FROM(
    SELECT Email, COUNT(Email) as num
    FROM Person
    GROUP BY Email
) as temp
WHERE 1<num