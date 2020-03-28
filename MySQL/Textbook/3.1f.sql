SELECT course_id, COUNT(course_id) FROM `takes`
WHERE semester='Fall' AND `year`=2009
GROUP BY course_id
ORDER BY COUNT(course_id) DESC
LIMIT 1