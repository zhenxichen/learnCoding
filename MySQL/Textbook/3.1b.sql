SELECT student.ID FROM student, instructor, advisor
WHERE student.ID=advisor.s_ID AND instructor.`name`='Einstein' AND instructor.ID = advisor.i_ID