# 选出所有选了‘00128’学生所选的所有课程的学生
# 实际上是用两个not exists 来实现关系代数除法
select distinct ID, name
from (
	select ID, name, course_id
	from student natural join takes
) as R1
where not exists (
	select course_id
	from (
		select course_id
		from takes
		where ID = '00128'
	) as S
	where not exists (
		select * from (
			select ID, name, course_id
			from student natural join takes
		)as R2
		where R1.ID = R2.ID and R2.course_id = S.course_id
	)
)