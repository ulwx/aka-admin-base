getSysRoleUrlsList
===
SELECT r.`role_name`,u.`id`,u.`role_id`,u.`url_match`,u.`updatime` 
FROM `sys_role_urls` u 
LEFT JOIN `sys_role` r ON u.`role_id` = r.`sys_role_sno` 
WHERE 1=1
@ if($$:roleId){
AND r.`sys_role_sno` = #{roleId}
@ }



getRoleList
===
SELECT r.`sys_role_sno`,r.`role_name` FROM `sys_role` r 
where 1=1
@ if($$:roleId){
AND r.`sys_role_sno` = #{roleId}
@ }


