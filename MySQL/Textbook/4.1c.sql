SELECT T.course_id, T.sec_id, IFNULL(instructor.`name`, '-') as ID
FROM(
	SELECT S.course_id as course_id, S.sec_id as sec_id, teaches.ID as ID
	FROM (
		SELECT course_id, sec_id
		FROM section
		WHERE semester = 'Spring' and `year` = '2010'
	)as S
	LEFT JOIN teaches
	ON S.course_id = teaches.course_id
)as T
LEFT JOIN instructor
ON T.ID = instructor.ID