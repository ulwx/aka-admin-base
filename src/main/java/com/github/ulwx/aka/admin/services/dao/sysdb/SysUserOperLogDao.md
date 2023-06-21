countUserBlack
===
@ //查询黑名单用户是否存在
select count(*) as `value` 
from user_black  t
where t.`user_id`=#{userId} and t.`status`=1;