countUsersSession
===
@ //用户session汇总信息
select count(*) as `value` 
from sys_users_session  
where session_id=#{sessionId}  and status=1


getUsersSession
===
@ //通过userId获取session信息
SELECT
  `sys_user_id`,
  `session_id`,
  `status`,
  `login_ip`,
  `login_time`
FROM
  `sys_users_session`
  where sys_user_id=#{userId}
  
  
countCurrentOneMiniteSession
===
@ // 获取5分钟内的session数
SELECT COUNT(1) AS `value` 
FROM sys_users_session 
WHERE login_time >= DATE_ADD(NOW(),INTERVAL -5 MINUTE)