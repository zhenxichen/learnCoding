SELECT * FROM `instructor`
WHERE salary = (SELECT MAX(salary) FROM instructor)