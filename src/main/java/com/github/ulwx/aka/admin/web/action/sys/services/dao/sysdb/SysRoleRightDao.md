getRightByRoles
===
select c.* from sys_role a,sys_role_right b, sys_right c
				where a.sys_role_sno=b.sys_role_Id
				and b.sys_right_code=c.sys_right_code  and c.enable=1
@if($$:roles){				
				AND sys_role_id in (#{[Array]roles})
@}else{
				AND 1!=1 
@}
				group by c.sys_right_code
				order by LEFT(c.sys_right_code, 2) asc, c.order_code asc, c.update_time
