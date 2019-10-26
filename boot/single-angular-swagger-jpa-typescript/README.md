
swagger
----------
https://springfox.github.io/springfox/docs/current/
https://github.com/springfox/springfox/issues/1443


Spring data jpa JPQL
----
SpEL Expressions : https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions
https://www.baeldung.com/spring-expression-language
ifnull COALESCE -> https://stackoverflow.com/questions/1215495/select-nvlmaxc-employeeid-0-in-jpql

lt, gt, gte, lte
https://arahansa.github.io/docs_spring/jpa.html



Documents
-------------
#### maria
function: https://mariadb.com/kb/en/library/week/

##### spring boot
> - vmOption
> - java mainclass -Dspring.profiles.active=local
##### GIT submodule 초기 셋팅 방법

> - git clone http://adonis84@125.141.209.173:7990/scm/om/api.git
> - cd commo
> - git submodule init
> - git submodule update
> - git checkout master


##### GIT submodule 수정 후 push 방법

> - cd common
> - git commit -am "커밋 내용"
> - git push
> - cd ..
> - git commit -am "common소스 현행화"
> - git push



##### 실행 VM options 지정
> -Dspring.profiles.active=local





#### 배치가 문제가 되는 경우 날려야 할 배치 테이블 공유 드립니다.
```
delete from BATCH_STEP_EXECUTION_CONTEXT;
delete from BATCH_STEP_EXECUTION_SEQ;
delete from BATCH_STEP_EXECUTION;
delete from BATCH_JOB_EXECUTION_CONTEXT;
delete from BATCH_JOB_EXECUTION_PARAMS;
delete from BATCH_JOB_EXECUTION_SEQ;
delete from BATCH_JOB_SEQ;
delete from BATCH_JOB_EXECUTION;
delete from BATCH_JOB_INSTANCE;
```
