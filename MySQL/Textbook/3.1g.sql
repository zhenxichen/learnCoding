SELECT course_id FROM takes
GROUP BY course_id
HAVING COUNT(course_id)=(
	SELECT MAX(count) FROM(
		SELECT course_id, COUNT(course_id) AS count
		FROM takes
		GROUP BY course_id
	) As stat
)