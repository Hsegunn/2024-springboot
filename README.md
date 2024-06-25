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

## 2, 3일차
- Oracle 도커로 설치
	- Docker는 Virtual Machine을 업그레이드한 시스템
	- 윈도우 서비스 내(services.msc) Oracle 관련 서비스 종료
	- Docker에서 Oracle 이미지 컨테이너를 다운로드 후 실행
	- Docker 설치시 오류 Docker Desktop - WSL Update failed
		- Docker Desktop 실행종료 후
		- Windows 업데이트 실행 최신판 재부팅
		- https://github.com/microsoft/WSL/releases, wsl.2.x.x.x64.msi 다운로드 설치 한 뒤
		- Docker Desktop 재실행
	- Oracle 최신판 설치
	```shell
	> docker --version
	Docker version 26.1.1, build 4cf5afa
	> docker pull container-registry.oracle.com/database/free:latest
	latest: ....
	... : Download complete
	> docker images
	REPOSITORY                                    TAG       IMAGE ID       CREATED       SIZE
	container-registry.oracle.com/database/free   latest    7510f8869b04   7 weeks ago   8.7GB
	> docker run -d -p 1521:1521 --name oracle container-registry.oracle.com/database/free
	....
	> docker logs oracle
	...
	#########################
	DATABASE IS READY TO USE!
	#########################
	...	
	> docker exec -it oracle bash
	bash-4.4$ 
	```

	- Oracle system 사용자 비번 oracle로 설정
	```shell
	bash-4.4$ ./setPassword.sh oracle
	```

	- Oracle 접속확인
		- DBeaver 탐색기 > Create > Connection

- Database 설정
	- Oracle - 운영시 사용할 DB
	- Oracle PKNUSB / pknu_p@ss 로 생성
	- 콘솔(도커 / 일반 Oracle)
	```shell
	> sqlplus system/password
	SQL> select name from v$database;
	// 서비스명 확인
	// 최신버전에서 사용자 생성시 C## prefix 방지 쿼리
	SQL> ALTER SESSION SET "_ORACLE_SCRIPT"=true;
	// 사용자 생성
	SQL> create user pknusb identified by "pknu_p@ss";
	// 사용자 권한
	SQL> grant CONNECT, RESOURCE, CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW to pknusb;
	// 사용자 계정 테이블 공간설정, 공간쿼터
	SQL> alter user pknusb default tablespace users;
	SQL> alter user pknusb quota unlimited on users;
	```

	- H2 DB - Spring Boot에서 손쉽게 사용한 Inmemory DB, Oracle, MySql, SQLServer과 쉽게 호환
	- MySQL - Optional 설명할 DB	
	
- Spring Boot + MyBatis 프로젝트
	- application name : spring02
	- Spring Boot 3.3.x 에는 MyBatis 없음
	- Dependency
        - Spring Boot DevTools
        - Lombok
        - SPring Web
        - Thymeleaf
        - Oracle Driver
        - Mybatis starter

	- build.gradle 확인
	- application.properties 추가작성
    - Dependency 중 DB(H2, Oracle, MySQL)가 선택시 application.properties에 DB설정이 안되면 서버 실행이 안됨

	```properties
	spring.application.name=spring02

	## 포트변경
	server.port=8091

	## 로그색상
	spring.output.ansi.enabled=always

	## 수정사항이 있으면 서버 자동 재빌드 설정
	spring.devtools.livereload.enabled=true
	spring.devtools.restart.enabled=true

	## 로그레벨 설정
	logging.level.org.springframework=info
	logging.level.org.zerock=debug

	## Oracle 설정
	spring.datasource.username=pknusb
	spring.datasource.password=pknu_p@ss
	#spring.datasource.url=jdbc:oracle:thin@localhost:1521:XE 
	spring.datasource.url=jdbc:oracle:thin@localhost:11521:FREE
	spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

	## MyBatis 설정
	## mapper 폴더 및에 여러가지 폴더가 내재, 확장자는 .xml이지만 파일명은 뭐든지 
	mybatis.mapper-locations=classpath:mapper/**/*.xml
	mybatis.type-aliases-package=com.jaemin.spring02.domain
	```

	- MyBatis 적용 
		- Spring, resource/WEB-INF 위치에 root-context.xml에 DB, Mybatis 설정
		- SPringBoot, application.properties + Config.java 로 변경
    
    - MyBatis 개발 시 순서
        1. Database 테이블 생성
        2. MyBatis 설정 -> /config/MyBatisConfig.java
        3. 테이블과 일치하는 클래스 (domain, entity, dto, vo, etc ...)
			- 테이블 컬럼 "_"는 Java클래스는 사용안함
        4. DB에 데이터를 주고받을 수 있는 클래스(dao, **mapper**, repository ...) 생성
            - 쿼리를 클래스내 작성가능, xml로 분리가능
        5. (Model) 분리했을 경우 /resources/mapper/클래스.xml 생성, 쿼리입력
        6. 서비스 인터페이스 /service/*Service.java, 서비스 구현 클래스 /service/*ServiceImpl.java 생성 작성
        7. 사용자 접근하는 컨트롤러 클래스 생성 -> @Controller 변경가능
        8. (Controller) ~~경우에 따라 @SpringBootApplication 클래스에 sqlSessionFactory 빈을 생성 메서드 작성~~
        9. (View) /resource/templates/Thymeleaf html 생성, 작성
	- (오류났던 사항)
		- application.properties jdbc:oracle:thin:@localgost:1521:FREE , thin뒤에 :이 없었음 붙여야 함
		- TodoMapper.xml에서 Select문에 세미클론이 찍혀있는 경우 오류가 남(FROM TODOS;) xml파일은 세미클론이 들어가면 안됨

## 4일차
- Sprig Boot JPA + Oracle + Thymeleaf + React
	- JPA -> DB설계를 하지 않고 엔티티 클래스를 DB로 자동생성 해주는 기술, Query로 만들 필요없음
	- H2 -> Oracle, MySQL, SQLServer등과 달리 Inmemory DB, 스프링부트 실행되면 같이 실행되는 DB
		개발편의성, 다른 DB로 전환시 아주 편리, 개발하는 동안 사용을 추천
	- Thymeleaf -> JSP의 단점 복잡한 템플릿형태 + 스파게티코드를 해소해주는 템플릿
	- Bootstrap -> 웹디자인 및 CSS의 혁신, 커스터마이징도 가능
	- React -> 프론트엔드를 분리, 백엔드 서버와 프론트엔드 서버를 따로 관리(통합도 가능)

- Spring Boot 프로젝트 생성
	- 명령 팔레트로 시작
	- Spring Boot version : 3.2.6
	- project language : java
	- Group ID : com.jaemin
	- Artifact ID : backboard
	- package type : jar
	- Java version : 17
	- Dependency
		1. Spring Boot DevTools
		2. Lombok
		3. Spring Web
		4. Thymeleaf
		5. Oracle Driver(나중에)
		6. H2 Database(나중에)
		7. Data JPA(나중에)

- Spring Boot JPA 프로젝트 개발
	1. (설정) build.gradle 디펜던시 확인
	2. (설정) application.properties 기본설정 입력(포트번호, 로그색상, 자동재빌드, 로그레벨)
	3. MVC패턴에 맞춰서 각 기능별로 폴더를 생성(controller, service, entity ... )
	4. /controller/MainController.java 생성, 기본 메서드 구현
	5. (설정) application.properties H2, JPA 설정추가
	6. (설정) 웹 서버 실행 http://localhost:8080/h2-console DB 연결확인

	7. /entity/Board.java 생성
		- GenerationType 타입
			- AUTO : SpringBoot에서 자동으로 선택(X)
			- IDENTITY : MySQL, SQLServer
			- SEQUENCE : Oracle(!)
		- column이름을 createDate로 만들면 DB에 컬럼명이 create_date로 생성됨
		- 컬럼명에 언더바를 안넣으려면 @column(name = "createDate") 사용
	8. /entity/Reply.java 생성
	9. 두 엔티티간 @OneToMany, @ManyToOne을 설정
	10. 웹 서버 재시작 후 h2-console에서 테이블 생성확인
	11. /repository/BoardRepository.java 빈 인터페이스(JpaRepository 상속) 생성
	12. /repository/BoardRepository.java 빈 인터페이스(JpaRepository 상속) 생성
	13. /test/ ... /repository/BoardRepositoryTests.java 생성, 테스트 메서드 작성
	14. 테스트 시작 > 웹 서버 실행 > h2-console 확인

## 5일차
- 오류
	- Test중에 OpenJDK 64-Bit Server VM warning 경고가 뜨면
	- 설정 > Java test config > settings.json 편집 
	```json
	    "java.test.config": {
        "vmArgs": [
            "-Xshare:off"
        ]
    }
	```
	- 저장 후 실행

- Spring Boot 프로젝트 오류처리
	- 빌드를 해도 제대로 결과가 반영되지 않으면 
	- Github Remote Repository 커밋, 푸시 후
	- Local Repository 모두 삭제 후 새로 커밋
	- 프로젝트 새롭게 로드해서 초기화

- Spring Boot JPA 프로젝트 개발 계속
	1. jUnit 테스트로 CRUD 확인
	2. /service/BoardService.java 생성 후 getList() 메세드 작성
	3. /controller/BoardController.java 생성 후 /board/list 실행할 수 있는 메서드 작성
	4. /templates/board/list.html 생성
		- thymeleaf 속성
			- th:if="${board != null}"
			- th:each="${boardList}"
			- th:text="${board.title}"
	5. /service/BoardService.java에 getBoard() 메서드 추가
	6. /controller/BoardController.java에 /borad/detail/{bno} 실행 메서드 작성
	7. /templates/board/detail.html 생성
	
		<img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp003.png" width="730">

	8. /templates/board/detail.html에 댓글영역 추가
	9. /service/ReplyService.java 생성, 댓글저장 메서드 작성
	10. /controller/ReplyController.java 생성, /reply/create/{bno} 포스트매핑 메서드 작성

	11. Bootstrap 적용방법
		- 다운로드 후 프로젝트에 위치
		- CDN 링크를 추가
		- https://getbootstrap.com/ 다운로드 후 압축풀기
		- bootstrap.min.css, bootstrap.min.js를 templates/static에 위치
	12. /templates/board/detail.html, list.html 부트스트랩 적용

		<img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp004.png" width="730">
		

## 6일차
- Spring Boot JPA 프로젝트 개발계속
	1. (설정) build.gradle Thymeleaf 레이아웃 사용을 위한 디펜던시 추가
	2. /templates/layout.html Tymeleaf로 레이아웃 템플릿 생성
	3. list.html, detail.html 레이아웃 템플릿 적용
	4. /templates/layout.html에 Bootstrap CDN 적용
	5. /templates/board/list.html에 게시글 등록버튼 추가
	6. /templates/board/create.html 게시글 작성 페이지 생성
	7. /controller/BoardController.java에 create()라는 GetMapping 메서드 추가
	8. /service/BoardService.java setBoard() 작성
	9. /controller/BoardController.java에 create()라는 PostMapping 메서드 추가
	10. (문제) 내용을 적지 않아도 저장됨
	11. (설정) build.gradle 입력값에 입력값 검증 디펜던시 추가
	12. /validation/BoardForm.java 클래스 생성
	13. /controller/BoardController.java에 BoardForm을 전달 (Get, PostMapping 둘 다)
	14. create.html 입력항목 name, id를 th:field로 변경(ex. th:field='*{title}')
	15. 댓글등록에도 반영, ReplyForm, ReplyController, detail.html 작업 (12~14번 내용들과 유사)
	16. detail.html 경고영역 div는 create.html에서 복사해서 사용(오타발생 예방)
	17. (문제) 각 입력창에 공백을 넣었을 때 입력되는 문제
		- @NotEmpty를 @NotBlank로 변경하면 공백이 들어가지 않음

		<img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp005.png" width="730">

	18. /templates/layout.html에네비게이션바(navbar) 추가
	19. 테스트로 데이터 추가

## 7일차
- Spring Boot JPA 프로젝트 개발계속
	0. 개념
	```sql
	-- 오라클 전용(11g 이하는 해당 쿼리가 작동안함)
	select b1_0.bno,b1_0.content,b1_0.create_date,b1_0.title 
	from board b1_0 offset 0 		-- 0부터 페이지 사이즈만큼 증가
	rows fetch first 10 rows only	-- 페이지사이즈
	```
	1. 페이징
		- /repository/BoardRepository.java findAll(pageable) 인터페이스 메서드 작성
		- /service/BoardService.java getList(page) 메서드 작성
		- /controller/BoardController.java list() 메서드 수정
		- /templates/board/list.html boardList -> paging 변경
		- /templates/board/list.html 하단 페이징 버튼추가, thymeleaf 기능추가
		- /service/BoardService.java getList() 내용을 최신순으로 역정렬
		- /templates/board/list.html에 게시글 번호 수정

		<img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp006.png" width="730">


	2. /templates/board/list.html td 뱃지추가

	3. H2 -> Oracle로 변경
		- build.gradle에 Oracle 디펜던시 추가
		- application.properties에 Oracle 관련 설정 추가, H2 설정 주석처리
		- 재시작

	4. 스프링 시큐리티
		- (설정) build.gradle 스프링 시큐리티 관련 디펜던시 추가
		- (설정) gradle 재빌드 , 서버 실행
		- user / 로그상의 UUID(서버실행 시 마다 변경됨)입력
		- /security/SecurityConfig.java 보안설정 파일생성, 작성 -> 시큐리티를 다시 풀어주는 작업

		- entity에 Member.java 생성
		- /repository/MemberRepository.java 인터페이스 생성
		- /service/MemberService.java 생성, setMember() 메서드 작성


## 8일차
- Spring Boot JPA 프로젝트 개발계속
	1. 스프링 시큐리티 계속
		- /security/SecurityConfig.java에 BCryptPasswordEncoder를 빈으로 작업
		- /validation/MemberForm.java 생성
		- /controller/MemberController.java 생성
		- /entity/Member.java에 regDate 추가
		- /service/MemberService.java에 regDate() 부분 추가작성
		- /templates/member/register.html 작성
		- (설정) Member 테이블에 저장된 회원정보 확인
		- /templates/layout.html에 회원가입 링크추가
		- /controller/MemberController.java Postmapping register에 중복회원가입 방지 설정
		- /entity/Member.java role 변수 추가

	2. 로그인 기능
		- /security/SecurityConfig.java에 login url 설정
		- /templates/layout.html 로그인 링크 수정
		- /templates/member/login.html 생성
		- /repository/MemberRepository.java find* 메서드추가
		- /controller/MemberController.java에 login Get/Post 메서드 작성
		- /service/MemberSecurityService.java - 로그인은 post를 사용하지 않고, Spring Security가 지원하는 UserDetailService 
		인터페이스 활용
		- /security/SecurityConfig.java 계정관리자 빈 추가
		- /templates/layout.html 로그인/로그아웃 토글 메뉴 추가

	3. 게시글 작성자 추가
		- /entity/Board.java , /entity/Reply.java에 작성자 변수(속성) 추가
		- /service/MemberService.java getMember() 메서드
		- (TIP) default Exception으로 예외를 처리하면 메서드뒤에 항상 throws Exception을 적어야함
		- /common/NotFoundException.java 생성 -> throws Exception 쓰는데 반영
		- /service/ReplyService.java setReply() 사용자 추가
		- /controller/ReplyController.java 오류나는 setReply() 파라미터 수정
		- /service/BoardService.java ...
		- /controller/BoardController.java setBoard() 사용자 추가
		- /controller/ 작성하는 get/Post 메서드에 @PreAuthorize 어노테이션 추가
		- /config/SecurityConfig.java @PreAuthorize 동작하도록 설정추가
		- /templates/board/detail.html 답변 textarea 로그인전, 후로 구분
		
		- /templates/board/list.html table태그에 작성자 컬럼 추가
		- /templates/board/list.html 게시글 작성자, 댓글 작성자표시 추가

		<img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp007.png" width="730">


## 9일차
- Spring Boot JPA 프로젝트 개발계속
	1. 수정, 삭제 기능
		- /entity/Board, Reply.java 수정일자 필드 추가
		- /tempaltes/board.html 수정, 삭제버튼 추가
		- /controller/BoardController.java, modify() Get 메서드
		- /templates/board/create.html form th:action을 삭제
			- create.html 생성, 수정할 때 모두 사용
			- get이 /board/create로 들어가면 post도 같은 URL로 실행되고, /board/modify/{bno}로 페이지를 들어가면 post도 같은 url로 실행
		- /service/BoardService.java 수정관련 메서드 추가작성
		- /controller/BoardController.java, modify() POST 메서드 작성
			- html에는 BoardForm 객체 값이 들어있음. 컨트롤러에 받아서 Board객체 다시 만들어 서비스로 전달
		
		- /service/BoardService.java 삭제관련 메서드 추가
		- /controller/BoardController.java delete() Get 메서드 작성

		- /tempaltes/board/detail.html 댓글 수정 , 삭제버튼 추가
		- /service/ReplyService.java 수정, 삭제관련 메서드 추가
		- /controller/ReplyController.java modify Get, Post 메서드, 삭제 Get메서드 작성
		- /templates/reply/modify.html 생성, 작성

		- /templates/board/detail.html에 게시글, 댓글에 수정날짜 표시

	2. 앵커기능
		- 추가, 수정, 삭제 시 이전 자신의 위치로 되돌아가는 기능
		- /templates/board/detail.html 댓글마다 앵커링 추가
		- /controller/ReplyController.java modify() Post매핑, return에 앵커링 추가
		- /service/ReplyService.java 생성 메서드 void -> Reply 변경
		- /controller/ReplyController.java create Post 메서드를 변경

		- /controller/Board/Controller.java detail() 메서드 수정
	
	3. 검색 기능
		- /service/BoardService.java search() 메서드 추가
		- /repository/BoardRepository.java findAll() 메서드 추가
		- /service/BoardService.java getList() 메서드 추가생성
		- /controller/BoardController.java list() 메서드 추가
		- /templates/board/list.html 검색창 추가, searchForm 폼영역 추가, 페이징영역 수정, javascript 추가
	
## 10일차
- Spring Boot JPA 프로젝트 개발계속
	1. 검색 기능 -> JPA Query
		- @Query 어노테이션으로 직접 쿼리를 작성
		- 단순 쿼리가 아니라서 JpaRepository가 자동으로 만들어 줄 수 없을 때 사용
		- DB의 표준쿼리와 차이가 있음(Java Entity와 일치)
		- /repository/Baordrepository.java , findAllByKeyword() 메서드 추가
		- JPA Query @Query("")에 작성
		- /service/BoardService.java getList() 수정

	2. 마크다운 적용
		- Wysiwyg 에디터 - [CKEditor](https://ckeditor.com/), [TinyMCE](https://www.tiny.cloud/)
		- [simpleMDE](https://simplemde.com/) 갯헙에 CDN 링크복사 layout.html 링크추가
		- create.html textarea id content를 simplemde로 변환하는 js 추가
		- detail.html textarea content simplemde js추가
		
		- (설정) build.gradle 마크다운 디펜던시 추가
		- /common/CommonUtil.java 생성
		- /templates/board/detail.html 마크다운 뷰어 적용

		<img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp009.png" width="730">


		<img src="https://raw.githubusercontent.com/Hsegunn/2024-springboot/main/images/sp010.png" width="730">



	3. 카테고리 추가(게시판, QnA, 공지사항)
		- /entity/Categoty.java 클래스 추가
		- /repository/CategoryRepository.java 인터페이스
		- /serivce/CategoryService.java 추가
		- /service/BoardService.java 조회조건에 카테고리 추가 수정
		- 카테고리를 자유게시판, 질문응답게시판 분리
		- /templates/layout.html navbar.html 추가
		- /controller/BoardController.java GetMapping 메서드에 카테고리를 추가

	4. 조회수 표시
		- /entity/Board.java 조회수 필드 추가
		- /service/BoardService.java 메서드 추가
		- /controller/BoardController.java detail() 메서드 수정
		- /templates/board/list.html 조회수 컬럼 추가


	- 비밀번호 찾기, 변경

	- 리액트 적용
	- 리액트로 프론트엔드 설정
	- Thymeleaf - 리액트로 변경
	- Spring Boot RestAPI 작업