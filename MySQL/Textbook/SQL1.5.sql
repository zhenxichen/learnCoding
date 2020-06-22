# Find the enrollment of each section what was offerd in Fall 2009.
select course_id, sec_id, count(*)
from takes
where semester = 'Fall' and `year` = 2009
group by course_id, sec_id;