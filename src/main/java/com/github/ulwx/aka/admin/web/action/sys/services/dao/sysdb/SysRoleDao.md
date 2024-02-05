getData
===
SELECT t1.* FROM sys_role t1 WHERE 1=1
@ if($$:roleName){
  	and t1.role_name LIKE #{%roleName%} 
@ }

ORDER BY  t1.sys_role_sno asc
    

    
getOneData 
===
SELECT sys_role_sno, role_name, description FROM sys_role WHERE sys_role_sno = #{sysRoleSno}



getSysRightByRole
===
SELECT b.sys_right_code AS sysRightCode FROM sys_role a 
				LEFT JOIN sys_role_right b ON  a.sys_role_sno=b.sys_role_Id 
				WHERE a.sys_role_sno = #{sysRoleSno}
				and RIGHT(sys_right_code, 3)!='000'

	
				
getSysRight
===
SELECT sys_right_code, sys_right_name FROM sys_right WHERE (1=1)
@ if($$:mainRightID){
	AND sys_right_code <> #{sysRightCode} AND sys_right_code LIKE #{%sysRightCode2%} 
@ }
@ else{
	AND RIGHT(sys_right_code, 3) = #{sysRightCode} AND enable = 1
@ }
	ORDER BY LEFT(sys_right_code, 2) ASC, order_code ASC, update_time



getDataByName
===
SELECT * FROM sys_role WHERE (1=1)
@ if($$:newRoleName){
	AND role_name = #{newRoleName}
@ }


getYdyRoleCount
===
SELECT
  count(1) as `value`
FROM
  `sys_role`
WHERE sys_role_sno IN (#{ids}) 



