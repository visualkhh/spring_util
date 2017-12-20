Welcome
===================


spring boot 1.5 (mvc), redis, jooq, jpa, hibernate, thymeleaf
기본 템플릿 소스 입니다.

----------


Documents
-------------




##### JOOK Generate Gradle 방법

> - 리얼 : gradle generateProdJooqSchemaSource
> - 개발 : gradle generateDevJooqSchemaSource

##### Spring Boot Executable jar make

> - 리얼 : gradle build -x test -x generateProdJooqSchemaSource
> - 개발 : gradle build -x test -x generateDevJooqSchemaSource


##### 실행 VM options 지정
> -Dspring.profiles.active=local


##### 실행 angular
> ${CMS_SOURCE}\src\main\angular 폴더 이동후   npm i 하여 현재폴더의 package.json으로 module 가져온다.
> Node Parameters : ./node_modules/@angular/cli/bin/ng build -w -op ../webapp --dev
> Working directory: ${CMS_SOURCE}\src\main\angular