getPageList
===
select id,
	page_name,
	match_url_suffix,
	if(status = 1,'有效','无效') statusName
from `sys_pages`
where 1=1
@ if($$:pageName){
	and page_name = #{pageName}
@}


getPageAdminList
===
SELECT ru.id id,
	s.name name,
    s.account as account,
	r.service_right_code as serviceRight,
	r.service_right_name as serviceRightName,
	p.page_name pageName,
	ru.updatime updateTime
FROM `sys_pages_service_right_user` ru
INNER JOIN `sys_user` s ON ru.sys_user_id = s.sys_user_sno
INNER JOIN `sys_pages_service_right` r ON r.id = ru.page_service_right_id
INNER JOIN `sys_pages` p ON p.id = r.page_id
where 1=1
@ if($$:name){
	and (s.name = #{name} or s.account = #{name})
@}
order by ru.id asc


getSysload
===
select sys_user_sno ,name,account
from sys_user
where enable = 1


getloadPageName
===
select id,page_name
from `sys_pages`
where status = 1

getloadRightName
===
select right_code,right_name from `sys_service_right`
where status =1



countUserById
===
SELECT COUNT(1) `value`
FROM `sys_pages_service_right_user` ru
INNER JOIN `sys_user` s ON ru.sys_user_id = s.sys_user_sno
INNER JOIN `sys_pages_service_right` r ON r.id = ru.page_service_right_id
INNER JOIN `sys_pages` p ON p.id = r.page_id
WHERE ru.sys_user_id=#{userId}
and p.id = #{pageId}
and r.service_right_code = #{rightCode}



getRight
===
SELECT id `value` FROM `sys_pages_service_right` 
WHERE page_id = #{pageId}
AND service_right_code = #{rightCode}