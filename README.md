# 2024-springboot
Java 빅데이터 개발자과정 Spring Boot 학습 리포지토리

## 1일차
- Spring Boot 개요
    - 개발환경, 개발 난이도를 낮추는 작업
    - Servlet > EJB > JSP > Spring > Spring Boot
    - 장점
        - Spring의 기술을 그대로 사용가능(마이그레이션 간단)
        - JPA를 사용하면 ERD나 DB설계를 하지 않고도 손쉽게 DB생성
        - Tomcat Webserver가 내장(따로 설치할 필요가 없음)
        - 서포트 기능 다수 존재(개발을 쉽게 도와줌)
        - JUnit 테스트, Log4J2 로그도 모두 포함
        - JSP, Thymeleaf, Mustache 등... 편하게 사용가능
        - DB 연동이 무지 쉽다
    - MVC
    <img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp002.png" width="730">

- Spring Boot 개발환경 설정
    - Java JDK 확인 > 17버전 이상
    - Visual Studio Code
        - VsCodeUserSetUp-x64-1.90.0.exe가 아님
        - VsCodeSetUp으로 설치
        - Extensions > 한국어 검색해서 설치
        - Extensions > Java 검색해서 Extention Pack for Java 설치
        - Extensions > Spiring 검색해서 Spring Extention Pack 설치
        - Extensions > Gradle for Java 검색해서 설치
    - Gradle build tool 설치고려(필요 시)
        - https://gradle.org/releases/
    - Oracle latest version Docker
    - Node.js
    - React setting

- Spring Boot 프로젝트 생성
    - 보기 > 명령 팔레트(ctrl + shift + p)
        - Spring Initializr : Create a Gradle Project
        - Specify Spring Boot version : 3.2.6
        - Specify project language : Java
        - Input Group Id : com.(임의로 변경)
        - Input Artifact Id (대문자 불가능)
        - Specify package type : Jar
        - Specify Java version : 17
        - Choose dependencies : Selected 0 dependencies
        - 폴더 선택 Diaglog 팝업 : 원하는 폴더 선택 후 Generate
        - 오른쪽 하단 팝업에서 Open 
        - Git 설정 옵션, Language Support for Java by Red Hat 설정 '항상'버튼 클릭
    
    - TroubleShooting
        1. 프로젝트 생성이 진행되다가 Gradle Connection 에러가 뜨면
            - Extentions > Gradle for Java를 제거 후
            - VsCode를 재시작한 뒤 프로젝트를 재 생성
        2. Gradle 빌드 시 버전 에러로 빌드가 실패하면
            - Gradle build tool 사이트에서 에러에 표시도니 버전의 Gradle bt 다운
            - 개발 컴퓨터에 설치
        3. ':compileJava' execution failed ...
            - Java JDK 설치 중 x86(32bit), x64 혼용설치
            - JDK 17 ... error 메세지
            - eclipse adoptium jdk17 새로 설치, 시스템 환경설정
            - build.gradle
    
    - 프로젝트 생성 후 
        - build.gradle 확인
        - src/main/resources/application.properties(또는 .yml) 확인
        - src/Java/GroupID/ArtifactID/ Java 소스파일 위치, 작업
        - src/main/resources/ 프로젝트 설정파일, 웹 리소스 파일(CSS, HTML, JS, JSP ... )
        - Spring01Application.java Run|Debug 메뉴
        - Gradle 빌드
            - 터미널에서 .\gradlew.bat 실행
            - Gradle for java(코끼리 아이콘) > tasks > Build > Build play icon(run task)실행
        - Spring Boot Dashboard
            - Apps > Spring01 Run|Debug 중에서 하나 아이콘 클릭해서 서버실행
            - 디버그로 실행해야 Hot code replace가 동작

            <img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp001.png" width="350">
        - 브라우저 변경설정
            - 설정 (Ctrl + ',') > browser > SpringDashboard Open With 'Internal' > 'External'로 변경
            - Chrome을 기본브라워저로 사용추천

## 2일차
- Oracle 도커로 설치
    - 설치되어 있는 Oracle 삭제
    -  

- Database 설정
    - H2 DB : Spring Boot에서 쉽게 사용가능한 Inmemory DB, Oracle, MySql, SQLServer와 쉽게 호환
    - Oracle : 운영시 사용할 DB
    - MySql : Optional 설명 DB
    - Oracle PKNUSB / pknu_p@ss 로 생성
        - 콘솔
        ```shell
        > sqlplus system/password
        SQL>
        ```