
getSmsInfo
===
SELECT
  js.`id`,
  js.`sms_type` ,
  js.`mobile`,
  js.`content`,
  js.`sms_code`,
  js.`response`,
  js.`response_id`,
  js.`create_time`
FROM
  `sys_sms` js
WHERE js.mobile=#{mobile}  AND DATE_ADD(js.create_time,INTERVAL 5 MINUTE)>NOW()
ORDER BY  js.create_time DESC
LIMIT 1;


getSmsRecent
===
SELECT
  js.`id`,
  js.`sms_type`,
  js.`mobile`,
  js.`content`,
  js.`sms_code`,
  js.`response`,
  js.`response_id`,
  js.`create_time`
FROM
  `sys_sms` js
WHERE js.mobile=#{mobile} 
ORDER BY  js.create_time DESC
LIMIT 1;
