SELECT instructor.ID, instructor.`name`, IFNULL((
	SELECT COUNT(*) FROM teaches
	GROUP BY teaches.ID
	HAVING teaches.ID = instructor.ID
),0) as sections
FROM instructor