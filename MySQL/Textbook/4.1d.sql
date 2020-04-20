SELECT dept_name, COUNT(ID) as num FROM(
	SELECT department.dept_name as dept_name, instructor.ID as ID
	FROM department
	LEFT JOIN instructor
	ON department.dept_name = instructor.dept_name
) as Temp
GROUP BY dept_name