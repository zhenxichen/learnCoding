SELECT * FROM student NATURAL JOIN takes
UNION
SELECT ID, `name`, dept_name, tot_cred, NULL, NULL, NULL, NULL, NULL
FROM student
WHERE NOT EXISTS (
	SELECT ID FROM takes
	WHERE student.ID = takes.ID
)