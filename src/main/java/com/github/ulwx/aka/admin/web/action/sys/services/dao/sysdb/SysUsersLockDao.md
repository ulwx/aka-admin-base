getUsersLock
===
@ //获取用户锁定信息
SELECT t.sys_user_id,t.pass_cnt,t.last_time,t.first_time FROM sys_users_lock t
WHERE t.sys_user_id=#{userId}