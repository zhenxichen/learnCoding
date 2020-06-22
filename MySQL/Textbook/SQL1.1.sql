# Find the titles of courses in the Comp. Sci. department that have 3 credits.
select title from course
where dept_name = 'Comp. Sci.' and credits = 3;