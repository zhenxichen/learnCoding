SELECT instructor.ID, instructor.`name`, COUNT(teaches.course_id) as sections
FROM instructor
LEFT JOIN teaches
ON instructor.ID = teaches.ID
GROUP BY instructor.ID