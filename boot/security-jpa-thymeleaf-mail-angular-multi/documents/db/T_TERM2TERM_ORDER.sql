-- 장동환: name순 정렬을 방법을 강구하다가.. 너무 어려워서 테이블을 따로 생성하였습니다. 2018.08.21

drop table T_TERM2TERM_ORDER;

CREATE TABLE `T_TERM2TERM_ORDER` (
	`term1_id` INT(11) NOT NULL,
	`term2_id` INT(11) NOT NULL,
	`lvl` INT(2) NOT NULL,
    `term2_nm` VARCHAR(1000) NOT NULL,
    `ordkey` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`ordkey`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


insert into T_TERM2TERM_ORDER

select  null as parent_termid
      , id as termid
      , 1 lvl
      , name
      , RPAD(concat('01', ifnull(substr(name, 1, 2), '00'), ifnull(LPAD(id, 7, '0'), '0000000')), 165, '0') ORDNO2
from    term
where   id  IN (118, 12823, 40006, 40279)
;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 2
        and     Y1.parent_termid   =   Y2.1_termid
        and     Y1.termid          =   Y2.2_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 3
        and     Y1.parent_termid   =   Y2.2_termid
        and     Y1.termid          =   Y2.3_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 4
        and     Y1.parent_termid   =   Y2.3_termid
        and     Y1.termid          =   Y2.4_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 5
        and     Y1.parent_termid   =   Y2.4_termid
        and     Y1.termid          =   Y2.5_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 6
        and     Y1.parent_termid   =   Y2.5_termid
        and     Y1.termid          =   Y2.6_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 7
        and     Y1.parent_termid   =   Y2.6_termid
        and     Y1.termid          =   Y2.7_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 8
        and     Y1.parent_termid   =   Y2.7_termid
        and     Y1.termid          =   Y2.8_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 9
        and     Y1.parent_termid   =   Y2.8_termid
        and     Y1.termid          =   Y2.9_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 10
        and     Y1.parent_termid   =   Y2.9_termid
        and     Y1.termid          =   Y2.10_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 11
        and     Y1.parent_termid   =   Y2.10_termid
        and     Y1.termid          =   Y2.11_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 12
        and     Y1.parent_termid   =   Y2.11_termid
        and     Y1.termid          =   Y2.12_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 13
        and     Y1.parent_termid   =   Y2.12_termid
        and     Y1.termid          =   Y2.13_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 14
        and     Y1.parent_termid   =   Y2.13_termid
        and     Y1.termid          =   Y2.14_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;

insert into T_TERM2TERM_ORDER

select Y3.parent_termid, Y3.termid, Y3.lvl, Y4.name, Y3.ordno2
from  (select   Y1.*, rpad(case when lvl = 1 then substr(Y2.ORDNO, 1, 11)
                                when lvl = 2 then substr(Y2.ORDNO, 1, 22)
                                when lvl = 3 then substr(Y2.ORDNO, 1, 33)
                                when lvl = 4 then substr(Y2.ORDNO, 1, 44)
                                when lvl = 5 then substr(Y2.ORDNO, 1, 55)
                                when lvl = 6 then substr(Y2.ORDNO, 1, 66)
                                when lvl = 7 then substr(Y2.ORDNO, 1, 77)
                                when lvl = 8 then substr(Y2.ORDNO, 1, 88)
                                when lvl = 9 then substr(Y2.ORDNO, 1, 99)
                                when lvl = 10 then substr(Y2.ORDNO, 1, 110)
                                when lvl = 11 then substr(Y2.ORDNO, 1, 121)
                                when lvl = 12 then substr(Y2.ORDNO, 1, 132)
                                when lvl = 13 then substr(Y2.ORDNO, 1, 143)
                                when lvl = 14 then substr(Y2.ORDNO, 1, 154)
                                when lvl = 15 then substr(Y2.ORDNO, 1, 165) end,165, '0') ordno2
        from   (select  '' as parent_termid
                      , term2_id as termid
                      , 1 as lvl
                from    term2term
                where   term2_id  IN (118, 12823, 40006, 40279)
                union all
                select  z.parent_termid, z.termid, z.lvl
                from   (select  CASE WHEN B.distance + 1 = 1 THEN '' ELSE B.term1_id END as root_termid
                              , CASE WHEN B.distance + 1 = 1 THEN '' ELSE A.term1_id END as parent_termid
                              , B.term2_id      as termid
                              , B.distance + 1  as lvl
                              , B.distance
                        from    term2term   A
                              , graph_path  B
                        where   1 = 1
                        and     B.term1_id  IN (118, 12823, 40006, 40279)
                        and     A.term2_id  =   B.term2_id) z
                     , (select  *
                        from    graph_path
                        where   term1_id  IN (118, 12823, 40006, 40279) ) p1
                where   1 = 1
                and     p1.term2_id = z.parent_termid
                and     p1.distance = z.distance - 1) Y1
              ,(select  *
                from   (SELECT  t1.term2_id  1_termid
                              , t2.term2_id  2_termid
                              , t3.term2_id  3_termid
                              , t4.term2_id  4_termid
                              , t5.term2_id  5_termid
                              , t6.term2_id  6_termid
                              , t7.term2_id  7_termid
                              , t8.term2_id  8_termid
                              , t9.term2_id  9_termid
                              , t10.term2_id 10_termid
                              , t11.term2_id 11_termid
                              , t12.term2_id 12_termid
                              , t13.term2_id 13_termid
                              , t14.term2_id 14_termid
                              , t15.term2_id 15_termid
                              , concat('01', ifnull(substr(t21.name, 1, 2), '00'), ifnull(LPAD(t1.term2_id, 7, '0'), '0000000')
                                     , '02', ifnull(substr(t22.name, 1, 2), '00'), ifnull(LPAD(t2.term2_id, 7, '0'), '0000000')
                                     , '03', ifnull(substr(t23.name, 1, 2), '00'), ifnull(LPAD(t3.term2_id, 7, '0'), '0000000')
                                     , '04', ifnull(substr(t24.name, 1, 2), '00'), ifnull(LPAD(t4.term2_id, 7, '0'), '0000000')
                                     , '05', ifnull(substr(t25.name, 1, 2), '00'), ifnull(LPAD(t5.term2_id, 7, '0'), '0000000')
                                     , '06', ifnull(substr(t26.name, 1, 2), '00'), ifnull(LPAD(t6.term2_id, 7, '0'), '0000000')
                                     , '07', ifnull(substr(t27.name, 1, 2), '00'), ifnull(LPAD(t7.term2_id, 7, '0'), '0000000')
                                     , '08', ifnull(substr(t28.name, 1, 2), '00'), ifnull(LPAD(t8.term2_id, 7, '0'), '0000000')
                                     , '09', ifnull(substr(t29.name, 1, 2), '00'), ifnull(LPAD(t9.term2_id, 7, '0'), '0000000')
                                     , '10', ifnull(substr(t210.name, 1, 2), '00'), ifnull(LPAD(t10.term2_id, 7, '0'), '0000000')
                                     , '11', ifnull(substr(t211.name, 1, 2), '00'), ifnull(LPAD(t11.term2_id, 7, '0'), '0000000')
                                     , '12', ifnull(substr(t212.name, 1, 2), '00'), ifnull(LPAD(t12.term2_id, 7, '0'), '0000000')
                                     , '13', ifnull(substr(t213.name, 1, 2), '00'), ifnull(LPAD(t13.term2_id, 7, '0'), '0000000')
                                     , '14', ifnull(substr(t214.name, 1, 2), '00'), ifnull(LPAD(t14.term2_id, 7, '0'), '0000000')
                                     , '15', ifnull(substr(t215.name, 1, 2), '00'), ifnull(LPAD(t15.term2_id, 7, '0'), '0000000')
                                     ) ORDNO
                        FROM   (select  term1_id, term2_id
                                from    term2term
                                where   1 = 1
                                AND     term2_id IN (118, 12823, 40006, 40279)
                                ) t1
                                LEFT JOIN term2term AS t2 ON t2.term1_id    =   t1.term2_id
                                LEFT JOIN term2term AS t3 ON t3.term1_id    =   t2.term2_id
                                LEFT JOIN term2term AS t4 ON t4.term1_id    =   t3.term2_id
                                LEFT JOIN term2term AS t5 ON t5.term1_id    =   t4.term2_id
                                LEFT JOIN term2term AS t6 ON t6.term1_id    =   t5.term2_id
                                LEFT JOIN term2term AS t7 ON t7.term1_id    =   t6.term2_id
                                LEFT JOIN term2term AS t8 ON t8.term1_id    =   t7.term2_id
                                LEFT JOIN term2term AS t9 ON t9.term1_id    =   t8.term2_id
                                LEFT JOIN term2term AS t10 ON t10.term1_id    =   t9.term2_id
                                LEFT JOIN term2term AS t11 ON t11.term1_id    =   t10.term2_id
                                LEFT JOIN term2term AS t12 ON t12.term1_id    =   t11.term2_id
                                LEFT JOIN term2term AS t13 ON t13.term1_id    =   t12.term2_id
                                LEFT JOIN term2term AS t14 ON t14.term1_id    =   t13.term2_id
                                LEFT JOIN term2term AS t15 ON t15.term1_id    =   t14.term2_id
                                LEFT JOIN term AS t21 ON t21.id = t1.term1_id
                                LEFT JOIN term AS t22 ON t22.id = t2.term1_id
                                LEFT JOIN term AS t23 ON t23.id = t3.term1_id
                                LEFT JOIN term AS t24 ON t24.id = t4.term1_id
                                LEFT JOIN term AS t25 ON t25.id = t5.term1_id
                                LEFT JOIN term AS t26 ON t26.id = t6.term1_id
                                LEFT JOIN term AS t27 ON t27.id = t7.term1_id
                                LEFT JOIN term AS t28 ON t28.id = t8.term1_id
                                LEFT JOIN term AS t29 ON t29.id = t9.term1_id
                                LEFT JOIN term AS t210 ON t210.id = t10.term1_id
                                LEFT JOIN term AS t211 ON t211.id = t11.term1_id
                                LEFT JOIN term AS t212 ON t212.id = t12.term1_id
                                LEFT JOIN term AS t213 ON t213.id = t13.term1_id
                                LEFT JOIN term AS t214 ON t214.id = t14.term1_id
                                LEFT JOIN term AS t215 ON t215.id = t15.term1_id
                        ) Z
                ) Y2
        where   1 = 1
        and     Y1.lvl = 15
        and     Y1.parent_termid   =   Y2.14_termid
        and     Y1.termid          =   Y2.15_termid
        group by ordno2
        ) Y3
      , term Y4
where 1 = 1
and   Y3.termid = Y4.id
;

commit;
