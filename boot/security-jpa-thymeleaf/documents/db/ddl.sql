
-- auto-generated definition
create table T_CORP_GRP
(
    CORP_GRP_SEQ int auto_increment comment '업체 그룹 시퀀스'
        primary key,
    CORP_GRP_NM  varchar(50)                         null comment '업체 그룹 이름',
    REG_DT       timestamp default CURRENT_TIMESTAMP null comment '등록 날짜'
)
    comment '업체 그룹' charset = utf8;


-- auto-generated definition
create table T_AUTH
(
    AUTH_ID varchar(20)   not null comment '권한 아이디'
        primary key,
    AUTH_NM varchar(50)   null comment '권한 이름',
    XPLN    varchar(4000) null comment '설명'
)
    comment '권한' charset = utf8;


-- auto-generated definition
create table T_CORP_GRP_AUTH
(
    CORP_GRP_AUTH_SEQ int auto_increment comment '업체 그룹 권한 시퀀스'
        primary key,
    CORP_GRP_SEQ      int         null comment '업체 그룹 시퀀스',
    AUTH_ID           varchar(20) null comment '권한 아이디',
    constraint FK_T_AUTH_TO_T_CORP_GRP_AUTH
        foreign key (AUTH_ID) references T_AUTH (AUTH_ID),
    constraint FK_T_CORP_GRP_TO_T_CORP_GRP_AUTH
        foreign key (CORP_GRP_SEQ) references T_CORP_GRP (CORP_GRP_SEQ)
)
    comment '그룹 권한 관계' charset = utf8;


-- auto-generated definition
create table T_URL
(
    URL_SEQ      int auto_increment comment 'URL 주소 시퀀스'
        primary key,
    MENU_NM      varchar(50)                         null comment '메뉴 이름',
    MENU_NM_EN   varchar(50)                         null comment '메뉴 이름 영문',
    I18N_CD      varchar(100)                        null,
    MENU_LVL     int                                 null comment '메뉴레벨',
    MENU_ICON    varchar(1000)                       null comment '메뉴 아이콘',
    MENU_ORD     int                                 null comment '메뉴 순서',
    URL          varchar(1000)                       null comment 'URL 주소',
    URL_XPLN     varchar(1000)                       null comment 'URL 주소 설명',
    URL_XPLN_EN  varchar(1000)                       null comment 'URL 주소 설명 영문',
    PRNT_URL_SEQ int                                 null comment '부모 URL 주소 시퀀스',
    USE_CD       varchar(6)                          null comment '사용유무',
    HDDN_CD      varchar(6)                          null comment '숨김 여부',
    REGEXP_CD    varchar(6)                          null,
    REG_DT       timestamp default CURRENT_TIMESTAMP null comment '등록 날짜'
)
    comment ' URL 주소' charset = utf8;

-- auto-generated definition
create table T_ADM_AUTH
(
    ADM_AUTH_SEQ int auto_increment comment '관리자 권한 시퀀스'
        primary key,
    ADM_SEQ      int         null comment '관리자 시퀀스',
    AUTH_ID      varchar(20) null comment '권한 아이디',
    constraint FK_T_ADM_TO_T_ADM_AUTH
        foreign key (ADM_SEQ) references T_ADM (ADM_SEQ),
    constraint FK_T_AUTH_TO_T_ADM_AUTH
        foreign key (AUTH_ID) references T_AUTH (AUTH_ID)
)
    comment '관리자 권한 관계' charset = utf8;



-- auto-generated definition
create table T_AUTH_URL
(
    AUTH_URL_SEQ int auto_increment comment '권한 URL주소 시퀀스'
        primary key,
    AUTH_ID      varchar(20)                         null comment '권한 아이디',
    URL_SEQ      int                                 null comment 'URL 주소 시퀀스',
    CRUD_TYPE_CD varchar(6)                          null comment 'CURD 유형 코드',
    REG_DT       timestamp default CURRENT_TIMESTAMP null comment '등록 날짜',
    constraint FK_T_AUTH_TO_T_AUTH_URL
        foreign key (AUTH_ID) references T_AUTH (AUTH_ID),
    constraint FK_T_URL_TO_T_AUTH_URL
        foreign key (URL_SEQ) references T_URL (URL_SEQ)
)
    comment '권한 URL 관계' charset = utf8;



create table T_ADM
(
    ADM_SEQ       int auto_increment comment '관리자 시퀀스'
        primary key,
    ADM_LGIN_ID   varchar(15)                         null comment '관리자 로그인 아이디',
    ADM_LGIN_PW   varchar(300)                        null comment '관리자 로그인 패스워드',
    ADM_NM        varchar(50)                         null comment '관리자 이름',
    USE_CD        varchar(6)                          null comment '사용 여부',
    HOME_URL      varchar(2000)                       null,
    EMAIL         varchar(300)                        null,
    PHONE         varchar(100)                        null,
    COMPANY_NM    varchar(100)                        null,
    JOB_CD        varchar(6)                          null,
    LGIN_FAIL_CNT int                                 null comment '로그인 실패 수',
    LGIN_WAIT_DT  timestamp                           null comment '로그인 대기시간',
    CORP_GRP_SEQ  int                                 null comment '업체 그룹 시퀀스',
    UPD_DT        timestamp                           null,
    START_DT      timestamp                           null,
    END_DT        timestamp                           null,
    REG_DT        timestamp default CURRENT_TIMESTAMP null comment '등록 날짜',
    constraint T_ADM_ibfk_1
        foreign key (CORP_GRP_SEQ) references T_CORP_GRP (CORP_GRP_SEQ)
)
    comment '관리자 정보' charset = utf8;

create index CORP_GRP_SEQ
    on T_ADM (CORP_GRP_SEQ);



-- auto-generated definition
create table T_CODE
(
    CD         varchar(6)                          not null comment '코드'
        primary key,
    CD_NM      varchar(100)                        null comment '코드 이름',
    CD_NM_EN   varchar(100)                        null comment '코드 이름 영문',
    CD_XPLN    varchar(4000)                       null comment '코드 설명',
    CD_ORD     int                                 null comment '코드 순서',
    MAPPING_CD varchar(100)                        null comment '맵핑 코드',
    PRNT_CD    varchar(6)                          null comment '부모 코드',
    REG_DT     timestamp default CURRENT_TIMESTAMP null comment '등록 날짜'
)
    comment '공통 코드' charset = utf8;




# admin // 1234
INSERT INTO test.T_ADM (ADM_SEQ, ADM_LGIN_ID, ADM_LGIN_PW, ADM_NM, USE_CD, HOME_URL, EMAIL, PHONE, COMPANY_NM, JOB_CD, LGIN_FAIL_CNT, LGIN_WAIT_DT, CORP_GRP_SEQ, UPD_DT, START_DT, END_DT, REG_DT) VALUES (1, 'admin', '$2a$10$Ntcpo2Lagef4uael9zrXQ.6H0LQG692wX0rg.sI8vGUFoYH4eZq8S', 'adminNm', 'USE001', '/home', null, null, null, null, null, null, 1, null, null, null, '2020-10-18 15:35:38');
INSERT INTO test.T_AUTH (AUTH_ID, AUTH_NM, XPLN) VALUES ('ROLE_ADMIN', '최고', '최고관리자');
INSERT INTO test.T_AUTH (AUTH_ID, AUTH_NM, XPLN) VALUES ('ROLE_CORP', '업체', '업체관리자');
INSERT INTO test.T_AUTH (AUTH_ID, AUTH_NM, XPLN) VALUES ('ROLE_CROP_ADMIN', '업체 관리자', '업체 관리자');
INSERT INTO test.T_AUTH (AUTH_ID, AUTH_NM, XPLN) VALUES ('ROLE_NORMAL', '일반', '일반사용자');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (1, 'ROLE_ADMIN', 1, 'GET', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (2, 'ROLE_ADMIN', 1, 'POST', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (3, 'ROLE_ADMIN', 1, 'PUT', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (4, 'ROLE_ADMIN', 1, 'DELETE', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (5, 'ROLE_ADMIN', 2, 'GET', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (6, 'ROLE_ADMIN', 2, 'POST', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (7, 'ROLE_ADMIN', 2, 'PUT', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (8, 'ROLE_ADMIN', 2, 'DELETE', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (9, 'ROLE_ADMIN', 3, 'GET', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (10, 'ROLE_ADMIN', 3, 'POST', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (11, 'ROLE_ADMIN', 3, 'PUT', '2020-10-18 15:44:07');
INSERT INTO test.T_AUTH_URL (AUTH_URL_SEQ, AUTH_ID, URL_SEQ, CRUD_TYPE_CD, REG_DT) VALUES (12, 'ROLE_ADMIN', 3, 'DELETE', '2020-10-18 15:44:07');
INSERT INTO test.T_CORP_GRP (CORP_GRP_SEQ, CORP_GRP_NM, REG_DT) VALUES (1, 'COMPANY', '2020-10-18 15:35:00');
INSERT INTO test.T_CORP_GRP_AUTH (CORP_GRP_AUTH_SEQ, CORP_GRP_SEQ, AUTH_ID) VALUES (1, 1, 'ROLE_ADMIN');
INSERT INTO test.T_URL (URL_SEQ, MENU_NM, MENU_NM_EN, I18N_CD, MENU_LVL, MENU_ICON, MENU_ORD, URL, URL_XPLN, URL_XPLN_EN, PRNT_URL_SEQ, USE_CD, HDDN_CD, REGEXP_CD, REG_DT) VALUES (1, '홈', 'Home', 'url.home', 1, 'grid', 0, '/home', '홈', 'home', null, 'USE001', 'USE002', null, '2020-10-18 15:37:51');
INSERT INTO test.T_URL (URL_SEQ, MENU_NM, MENU_NM_EN, I18N_CD, MENU_LVL, MENU_ICON, MENU_ORD, URL, URL_XPLN, URL_XPLN_EN, PRNT_URL_SEQ, USE_CD, HDDN_CD, REGEXP_CD, REG_DT) VALUES (2, '설정관리', 'Setup', 'url.setups', 1, 'plus', 3, '/setups', '계정관리한다.', 'Manage account', null, 'USE001', 'USE002', null, '2020-10-18 15:38:00');
INSERT INTO test.T_URL (URL_SEQ, MENU_NM, MENU_NM_EN, I18N_CD, MENU_LVL, MENU_ICON, MENU_ORD, URL, URL_XPLN, URL_XPLN_EN, PRNT_URL_SEQ, USE_CD, HDDN_CD, REGEXP_CD, REG_DT) VALUES (3, '게시판관리', 'Board', 'url.brds', 1, 'plus', 1, '/brds', '게시판 관리한다', 'Manage board', null, 'USE001', 'USE002', null, '2020-10-18 15:38:09');

INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('ACT000', '액션코드', 'Action', '액션코드', 0, null, null, '2020-09-14 10:13:04');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('ACT001', 'anon사용자 URL접근', 'anon user url', 'anon사용자 URL접근', 1, null, 'ACT000', '2020-09-14 10:13:04');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('ACT002', '로그인', 'login', 'login', 2, null, 'ACT000', '2020-09-14 10:13:04');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('ACT003', '로그아웃', 'logout', 'logout', 3, null, 'ACT000', '2020-09-14 10:13:04');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('BCC000', '게시판 카테고리 코드', 'Bulletin board category code
', '게시판 카테고리 코드', 0, null, null, '2020-08-13 15:25:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('BCC001', '공지사항', 'Notice
', '공지사항', 1, null, 'BCC000', '2020-08-13 15:25:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('BCC002', '도움말', 'Help
', '도움말', 2, null, 'BCC000', '2020-08-13 15:25:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('BCC003', '가이드', 'Guide
', '가이드', 3, null, 'BCC000', '2020-08-13 15:25:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('BCC004', '문의사항', 'Q&A', '문의사항', 4, null, 'BCC000', '2020-08-13 15:25:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('FST000', 'VCF,MRI 처리 상태 코드', 'Status of VCF, MRI', 'VCF,MRI 처리 상태에 대한 코드', 0, null, null, '2018-08-13 08:44:57');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('FST001', '미등록', 'Unregistered', '미등록', 1, null, 'FST000', '2018-08-13 08:44:57');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('FST002', '등록', 'Registration', '최초 등록', 2, null, 'FST000', '2018-08-13 08:44:57');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('FST003', '처리 중', 'Processing', '업로드 된 파일을 처리 하는 중', 3, null, 'FST000', '2018-08-13 08:44:57');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('FST004', '완료', 'Complete', '완료', 4, null, 'FST000', '2018-08-13 08:44:57');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('FST005', '실패', 'Fail', '실패', 5, null, 'FST000', '2018-08-13 08:44:57');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('GEC000', '성별 코드', 'Gender', '성별 코드', 0, null, null, '2018-01-09 20:16:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('GEC001', '남자', 'Male', '남자', 1, null, 'GEC000', '2018-01-09 20:16:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('GEC002', '여자', 'Female', '여자', 2, null, 'GEC000', '2018-01-09 20:16:44');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('JOB000', '직업', 'Job', '직업 카테고리', 0, null, null, '2020-09-11 14:12:38');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('JOB001', 'Clinician
', 'Clinician
', null, 1, null, 'JOB000', '2020-09-11 14:12:38');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('JOB002', 'Counselor
', 'Counselor
', null, 2, null, 'JOB000', '2020-09-11 14:12:38');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('JOB003', 'Clinical Laboratorian
', 'Clinical Laboratorian
', null, 3, null, 'JOB000', '2020-09-11 14:12:38');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('JOB004', 'Researcher
', 'Researcher
', null, 4, null, 'JOB000', '2020-09-11 14:12:38');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('JOB005', 'Educator
', 'Educator
', null, 5, null, 'JOB000', '2020-09-11 14:12:38');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('JOB006', '기타
', '기타
', null, 5, null, 'JOB000', '2020-09-11 14:12:38');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('POL000', '정책', 'policy', '정책', 0, null, null, '2020-09-14 01:41:30');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('POL001', '개인정보취급방침', 'Policy', '개인정보취급방침', 1, null, 'POL001', '2020-09-14 01:41:30');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('POL002', '이용약관', 'Terms', '이용약관', 2, null, 'POL001', '2020-09-14 01:41:30');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('USE000', '사용여부', 'use?', '사용여부', 0, null, null, '2020-08-13 15:02:30');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('USE001', '사용', 'Use
', '사용', 1, null, 'USE000', '2020-08-13 15:02:30');
INSERT INTO T_CODE (CD, CD_NM, CD_NM_EN, CD_XPLN, CD_ORD, MAPPING_CD, PRNT_CD, REG_DT) VALUES ('USE002', '미사용', 'Unused
', '미사용', 2, null, 'USE000', '2020-08-13 15:02:30');
