# Find the maximum enrollment, across all sections, in Fall 2009.
select course_id, sec_id, count(*) as enrollment
from takes
where semester = 'Fall' and year = 2009
group by course_id, sec_id
having count(*) >= all(
	select count(*)
	from takes
	where semester = 'Fall' and year = 2009
	group by course_id, sec_id
)