project
===
jar 실행
 - 추가할때: java -Dspring.profiles.active=h2 -jar cli.jar --spring.config.additional-location=./config/cli.yaml 
 - /Library/Java/JavaVirtualMachines/jdk-12.0.1.jdk/Contents/Home/bin/java -Dspring.profiles.active=dev1 -jar external-web.jar --spring.config.additional-location=application-dev1.yaml
 - 위치바꿀때: java -Dspring.profiles.active=h2 -jar cli.jar --spring.config.location=classpath:/,./application-h2.yaml
 ~~java -jar ./cbpp.jar --spring.config.name=dev --spring.config.location=./application.yaml~~
 - active option: -Dspring.profiles.active=h2-local 

gradle
===
 - https://docs.gradle.org/current/userguide/multi_project_builds.html
 - https://kwonnam.pe.kr/wiki/gradle/multiproject
 - https://docs.gradle.org/current/userguide/tutorial_using_tasks.html
 - https://shnoble.tistory.com/91
 - https://www.it-swarm-ko.tech/ko/gradle/%ec%97%ac%eb%9f%ac-%ec%9e%91%ec%97%85%ec%9d%84-%ec%8b%a4%ed%96%89%ed%95%98%eb%8a%94-gradle-%ec%82%ac%ec%9a%a9%ec%9e%90-%ec%a7%80%ec%a0%95-%ec%9e%91%ec%97%85/1055056419/
 - Project Properties: https://docs.gradle.org/current/userguide/writing_build_scripts.html
 - Archives: https://docs.gradle.org/current/userguide/working_with_files.html#sec:creating_archives_example

node
===
 - 메모리부족:  https://ajh322.tistory.com/242@q 
 - node_modules: https://vomvoru.github.io/blog/typescript-module-resolution/
 
nginx (http://nginx.org/en/docs/http)
===
 - grpc config: http://nginx.org/en/docs/http/ngx_http_grpc_module.html 

linux
=== 
pid & kill
 - https://java.ihoney.pe.kr/435
 - https://knight76.tistory.com/entry/spring-boot-pidport-%EB%B2%88%ED%98%B8-%EC%83%9D%EC%84%B1
 
  
 
Spring
===
 - 어디에서나 Spring 컨터이너 Bean 객체 얻어오기: https://bluexmas.tistory.com/497
 - Async 사용: https://jeong-pro.tistory.com/187
 - FailureAnalyzer
   - https://www.baeldung.com/spring-boot-failure-analyzer
   - https://www.javadevjournal.com/spring-boot/spring-boot-failure-analyzer/

 - test
   - https://goddaehee.tistory.com/211
 - jpa
   - https://howtodoinjava.com/jpa/jpa-native-update-sql-query-example/
   - https://www.baeldung.com/spring-data-jpa-modifying-annotation
   - batch - https://www.baeldung.com/spring-data-jpa-batch-inserts
   - jpql: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
 
 - batch: https://frightanic.com/software-development/jpa-batch-inserts/
 
 - Spring bean scope 빈 스코프(scope)  
   - 별도의 scope를 지정하지 않으면 스프링에서 default는 singleton 이다.
   - singleton : 기본 싱글톤 스코프
   - prototype : 어플리케이션에서 요청시 (getBean()) 마다 스프링이 새 인스턴스를 생성
   - request : HTTP 요청별로 인스턴스화 되며 요청이 끝나면 소멸 (spring mvc webapplication 용도)
   - session : HTTP 세션별로 인스턴스화되며 세션이 끝나며 소멸 (spring mvc webapplication 용도)
   - global session : 포틀릿 기반의 웹 어플리케이션 용도. 전역 세션 스코프의 빈은 같은 스프링 MVC를 사용한 포탈 어플리케이션 내의 모든 포틀릿 사이에서 공유할 수 있다
   - thred : 새 스레드에서 요청하면 새로운 bean 인스턴스를 생성, 같은 스레드에 대해서는 항상 같은 bean 인스턴스가 반환
   - custom : org.pringframework.beans.factory.config.Scope를 구현하고 커스텀 스코프를 스프링의 설정에 등록하여 사용
   - 출처: https://javaslave.tistory.com/45 [전산쟁이 블로그]
   - Listener
     - https://engkimbs.tistory.com/755
   
 - ThreadPollExecutor
   - https://github.com/HomoEfficio/dev-tips/blob/master/Java-Spring%20Thread%20Programming%20간단%20정리.md
   
 - config
   - https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html  
   - https://www.baeldung.com/spring-boot-context-path  
   - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html  
   - https://jistol.github.io/java/2017/04/26/extract-springboot-config/  
   - hikari
     - https://www.javadevjournal.com/spring-boot/spring-boot-hikari/
     - https://javacan.tistory.com/entry/spring-boot-2-hikaricp-property
     - https://goddaehee.tistory.com/205
   - config outerfiler 
    - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html  
    - java -jar myproject.jar --spring.config.name=myproject      
    - java -jar myproject.jar --spring.config.location=classpath:/default.properties,classpath:/override.properties
      
 - property 
   - default: https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html
   - yaml: https://mkyong.com/spring-boot/spring-boot-configurationproperties-example/
   - http://kwonnam.pe.kr/wiki/springframework/springboot/properties  
   - https://araikuma.tistory.com/42
   - https://ict-nroo.tistory.com/94
   - https://charlie-choi.tistory.com/210
   - https://junjangsee.github.io/2019/05/02/spring/spring-07/
   - https://engkimbs.tistory.com/765
   - 프로퍼티 암호화: https://warpgate3.tistory.com/entry/%ED%94%84%EB%A1%9C%ED%8D%BC%ED%8B%B0-%ED%8C%8C%EC%9D%BC-%EC%95%94%ED%98%B8%ED%99%94
   - application.properties (https://supawer0728.github.io/2018/03/11/Spring-Property/)
     - Srping boot enviroment abstraction은 다음 순서로 application.properties를 검색한다
     - 현재 위치에서 /config 디렉토리
     - 현재 위치
     - classpath 내의 /config 패키지
     - classpath 내의 루트
     - properties 파일을 대체하여 YAML file(.yml)을 사용할 수 있다.
     
 - console application  
   - https://www.baeldung.com/spring-boot-console-app 
   
 - shell application 
   - https://javaworld.co.kr/67
   - https://docs.spring.io/spring-shell/docs/current-SNAPSHOT/reference/htmlsingle/
   - https://medium.com/agency04/developing-cli-application-with-spring-shell-part-1-807cd3a32461
   
 - remote shell application 
   - https://java.ihoney.pe.kr/379
   
 - actuator 
   - https://dhsim86.github.io/web/2017/03/24/spring_boot_features_10-post.html

 - multi-project: https://github.com/ihoneymon/multi-module
 
Spring webflux
===
 - https://bclozel.github.io/webflux-workshop/
 - https://github.com/hantsy/spring-reactive-sample
 - https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-client
 - exception: https://luvstudy.tistory.com/74
 
 - webFlux WebClient
   - 예제: https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/  
   - https://www.logicbig.com/tutorials/spring-framework/spring-webflux/web-client.html  
   - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-webclient.html  
   - https://juejin.im/post/5a62f17cf265da3e51333205  
   - 토비의봄: https://www.youtube.com/watch?v=ScH7NZU_zvk
   - SSL: https://cnpnote.tistory.com/entry/SPRING-ssl%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-Spring-5-WebClient
 
JWT
===
 - https://alwayspr.tistory.com/8
 
Spring boot HTTP2
===
 - https://digitalbourgeois.tistory.com/67
 - https://iwantadmin.tistory.com/284
 - https://www.baeldung.com/spring-5-webclient
 
netty
===
 - https://github.com/netty/netty/tree/4.0/example/src/main/java/io/netty/example
 - HTTP/2 in Netty: https://www.baeldung.com/netty-http2
  
hsql
===
 - https://docs.jboss.org/hibernate/core/3.3/reference/en/html/queryhql.html

CURL
===
 - https://www.lesstif.com/pages/viewpage.action?pageId=14745703#curl%EC%84%A4%EC%B9%98%EB%B0%8F%EC%82%AC%EC%9A%A9%EB%B2%95-HTTPGET/POST,RESTAPI%EC%97%B0%EA%B3%84%EB%93%B1-Content-TypeHeader%EC%84%A4%EC%A0%95

ORACLE OJDBC
===
 - https://www.oracle.com/database/technologies/appdev/jdbc-ucp-183-downloads.html
 - https://www.oracle.com/database/technologies/develop-java-apps-using-jdbc.html
 
jackson
===
 - https://mkyong.com/java/jackson-2-convert-java-object-to-from-json/
 - lazy object: https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
 - plugin reference guide: https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/

http2
===
 - https://digitalbourgeois.tistory.com/67  
 - https://engkimbs.tistory.com/756  
 - https://programmer.help/blogs/java-9-spring-boot-2-undertow2-enables-http2-and-server-push.html  
 - https://www.novatec-gmbh.de/en/blog/spring-boot-applications-tls-http2/  
 - https://github.com/andifalk/ssl-demo  
 - https://docs.spring.io/spring-boot/docs/2.0.0.M6/reference/html/howto-embedded-web-servers.html  
 - https://github.com/otrosien/demo-http2.git 
 - https://craftingjava.com/blog/http2-java-9/
 - java11: https://openjdk.java.net/groups/net/httpclient/intro.html
 - http2 에서 http도 활성화: https://engkimbs.tistory.com/756
 - 인증서: https://engkimbs.tistory.com/756

jetty
===
 - http2: https://github.com/janweinschenker/jetty-http2-cleartext-demo

ORACLE
===
 - 사용쿼리 확인 및 바인드변수: https://idlecomputer.tistory.com/175

java
===
 - memory
   - https://blog.voidmainvoid.net/184
   - https://javaslave.tistory.com/23

angular
===
 - guard: https://www.a-mean-blog.com/ko/blog/Angular-2/%EA%B8%B0%EB%B3%B8%EC%82%AC%EC%9D%B4%ED%8A%B8-%EB%A7%8C%EB%93%A4%EA%B8%B0/Guard%EB%A1%9C-%EB%B9%84%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%A0%91%EA%B7%BC%EC%A0%9C%ED%95%9C-%EB%A7%8C%EB%93%A4%EA%B8%B0
 - assets outside path: https://github.com/angular/angular-cli/wiki/stories-asset-configuration
 - tsconfig -> paths 외부경로 처리할때 오류나는거: https://gitmemory.com/issue/angular/angular/35586/630774572
   - angularCompilerOptions > enableIvy: false
 - 외부 bootstrap npm으로 설치
   - https://medium.com/sjk5766/angular-6에서-bootstrap-4-설치하기-d0327776ed28
   - https://www.itsolutionstuff.com/post/install-bootstrap-4-in-angular-9-how-to-add-bootstrap-in-angular-9example.html
 - script load
   - Load External JavaScript File Dynamically: https://medium.com/better-programming/angular-load-external-javascript-file-dynamically-3d14dde815cb
 - directive
   - https://medium.com/@gusdk7656812/angular2-directive-d5119e33cc29
   
mysql
===
 dump and improt: https://bluebreeze.co.kr/462
  - mysqldump -u root -p -h 123.123.123 database_nm table > db.sql
  - mysql -u genome -p -h 123.123.123 genome < genome_dev_ignore_table_20200921.sql 
  - charset change in dumpfile: https://github.com/drud/ddev/issues/1902
 charset
    ```
        show tables;
        SELECT default_character_set_name, DEFAULT_COLLATION_NAME FROM information_schema.SCHEMATA
        WHERE schema_name = "genome";
        show variables like 'char%';
    ```   
 all table row and infommation
    ```
    SELECT SUM(TABLE_ROWS) 
         FROM INFORMATION_SCHEMA.TABLES 
         WHERE TABLE_SCHEMA = '{your_db}';
         show table status;
    ```
  definer remove
    ```
    Linux 에서
    sed -i 's/DEFINER=[^*]*\*/\*/g' dump.sql
    MacOS 에서
    sed -i '' 's/DEFINER=[^*]*\*/\*/g' dump.sql
    ```
  fk drop, insert
    ```
    alter table T_ADM drop foreign key FK_T_CORP_GRP_TO_T_ADM;
    drop index FK_T_CORP_GRP_TO_T_ADM on T_ADM;
    alter table T_ADM add FOREIGN KEY(CORP_GRP_SEQ) REFERENCES T_CORP_GRP(CORP_GRP_SEQ);
    ```
tip
=====
/Library/Java/JavaVirtualMachines/jdk-12.0.1.jdk/Contents/Home/bin/java -Dspring.profiles.active=prod -jar gate-web.jar --spring.config.additional-location=gate-web.yaml
