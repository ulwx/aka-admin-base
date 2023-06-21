

getSysPagesList
===
SELECT
sp.`id` as pageId,
sp.`page_name`,
sp.`match_url_suffix`,
spsr.`service_right_code`,
spsr.`service_right_name`
FROM
`sys_pages` sp
JOIN `sys_pages_service_right` spsr
ON sp.id = spsr.page_id
JOIN `sys_pages_service_right_user` spsru
ON spsru.page_service_right_id = spsr.id
WHERE sp.status = 1
AND spsru.sys_user_id=#{userNo};

