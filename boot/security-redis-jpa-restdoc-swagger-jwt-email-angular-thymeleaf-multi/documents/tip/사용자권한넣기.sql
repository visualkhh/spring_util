INSERT INTO T_AUTH_URL (AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT)
SELECT 'ROLE_ADMIN' AUTH_ID, URL_SEQ, CRUD_TYPE_CD, NOW() REG_DT FROM
    (SELECT URL_SEQ, CRUD_TYPE_CD FROM T_URL A JOIN
                                       (
                                           SELECT 'GET' CRUD_TYPE_CD
                                           UNION ALL
                                           SELECT 'POST' CRUD_TYPE_CD
                                           UNION ALL
                                           SELECT 'PUT' CRUD_TYPE_CD
                                           UNION ALL
                                           SELECT 'DELETE' CRUD_TYPE_CD
                                       ) B WHERE URL_SEQ > 200) FF;
