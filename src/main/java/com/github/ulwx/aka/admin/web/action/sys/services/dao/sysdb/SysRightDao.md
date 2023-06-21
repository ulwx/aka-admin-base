getAllRight
===
select * from sys_right where enable=1 order by LEFT(sys_right_code, 2) asc, order_code asc, update_time 


getData 
===
select * from sys_right where (1=1)
@ if($$:sysRightCode){
    and sys_right_code like #{%sysRightCode%}
@ }
@ if($$:sysRightName){
	and sys_right_name like #{%sysRightName%}
@}
    ORDER BY LEFT(sys_right_code, 2) ASC, order_code asc, update_time

    
getDataCount
===
SELECT COUNT(sys_right_code) AS dataCount FROM sys_right WHERE (1=1)
@ if($$:sysRightCode){
	and sys_right_code = #{sysRightCode}
@ }
@ if($$:sysRightName){
	AND sys_right_name = #{sysRightName}
@ }




getDataCountByUrl
===
SELECT COUNT(sys_right_code) AS dataCount FROM sys_right WHERE (1=1) and enable=1
@ if($$:sysRightUrl){
	and sys_right_url = #{sysRightUrl}
@ }


getOneData
===
SELECT *  FROM sys_right WHERE sys_right_code = #{sysRightCode}


updateData
===
UPDATE sys_right SET sys_right_code = #{sysRightCode} , sys_right_name = #{sysRightName}, sys_right_url = #{sysRightUrl}, 
					enable = #{enable},update_time = #{updateTime}, updator = #{updator}, order_code = #{orderCode},
					icon=#{icon}
					WHERE sys_right_code = #{sysRightCode2}


getUrlByRightCode
===
SELECT sys_right_url FROM  sys_right WHERE sys_right_code = #{sysRightCode}


getRightByCode
===
SELECT * FROM  sys_right WHERE sys_right_code LIKE #{%sysRightCode%}



















