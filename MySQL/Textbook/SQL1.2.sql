# Find the IDs of all students who were taught by an instructor whose name
# is Einstein; make sure there are no duplicates in the result.
select distinct ID
from takes
where (course_id, sec_id, semester, `year`) in (
	select course_id, sec_id, semester, `year`
	from instructor natural join teaches
	where `name` = 'Einstein'
);