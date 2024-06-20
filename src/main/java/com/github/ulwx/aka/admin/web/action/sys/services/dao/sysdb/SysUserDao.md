getUserList
===
SELECT 
  a.`sys_user_sno`,
  a.`account`,
  a.`name`,
  a.`tel`,
  a.`sex`,
  a.`phone`,
  a.`birth_day`,
  a.`nike_name`,
  a.`email`,
  a.`nation`,
  a.`sign`,
  a.`pic_url`,
  a.`enable`,
  a.`update_time` ,
  GROUP_CONCAT(DISTINCT b.`sys_role_id`) AS sysRoleIds,
  GROUP_CONCAT(DISTINCT role.`role_name`) AS sysRoleNames,
  GROUP_CONCAT(DISTINCT c.`sys_roletype_code`) AS sysRoleTypeCodes,
  GROUP_CONCAT(DISTINCT sr.`sys_roletype_name`) AS sysRoleTypeNames
FROM
  `sys_user` a 
  LEFT JOIN (`sys_user_role` b INNER JOIN `sys_role` role ON b.`sys_role_id`=role.`sys_role_sno`)
    ON a.`sys_user_sno` = b.`sys_user_id` 
  LEFT JOIN (`sys_user_roletype` c INNER JOIN `sys_roletype` sr ON c.`sys_roletype_code`=sr.sys_roletype_code)
    ON a.`sys_user_sno` = c.`sys_user_id` 
WHERE 1=1 

@if($$:userName){
AND (a.`name` like #{%userName%} or a.account like #{%userName%} )

@}
@if($$:enable){
AND a.`enable` =#{enable}
@}
@if($$:userPhone){
AND a.`phone` like #{userPhone%}
@}
@if($$:roles){
AND c.sys_roletype_code in(#{roles})
@}

GROUP BY a.sys_user_sno 
ORDER BY a.sys_user_sno DESC 


changeAllPassword
===
UPDATE
   `sys_user`
SET
  `password` = #{password}
WHERE `phone`= #{userPhone} AND `enable`=1


getUserInfo
===
select count(1) `value` from `sys_user_oper_log` 
where user_name = #{account}
and user_id = #{userId}
and oper_type=1 
limit 1

updatePassword
===
UPDATE
  `sys_user`
SET
  `password` = #{password}
WHERE `sys_user_sno` = #{sysUserId}

updateUserNameMobile
===
UPDATE
  `sys_user`
SET
  `name` = #{realname},
  `phone` = #{mobile}
WHERE `sys_user_sno` = #{sysUserId}


getUserById
===
SELECT
  `sys_user_sno`,
  `account`,
  `password`,
  `name`,
  `tel`,
  `sex`,
  `phone`,
  `birth_day`,
  `nike_name`,
  `email`,
  `nation`,
  `add_time`,
  `pic_url`,
  `sign`,
  `update_time`,
  `updator`,
  `enable`
FROM
  `sys_user`
WHERE sys_user_sno=#{sysUserId}
