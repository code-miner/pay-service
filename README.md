# pay-service (지자체 협약 지원 API 개발)



### 개발 프레임워크

- Spring boot 1.5.21.RELEASE, Java 1.8
- DataBase  : H2
- Security : JWT



### 문제해결 방법

------

#### 1. 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API

multipart 로 전달받은 csv 데이터를 opencsv librarary 를 이용하여 파싱한다. 파싱한 데이터는 regionRepository 와 regionDetailRepository 를 이용하여 각각 저장한다.

> <http://localhost:8080/swagger-ui.html#!/file/uploadFileUsingPOST> 



#### 2. 지원하는 지자체 목록 검색 API

regionDetailRepository 를 이용하여 전체 List 를 가져오며, RegionDataDto 로 출력 Type 을 변환한다.

> <http://localhost:8080/swagger-ui.html#!/region/selectListUsingGET>



#### 3. 지원하는 지자체명을 입력받아 해당 지자체의 지원정보를 출력하는 API

regionRepository 를 이용하여, 입력받은 지자체명의 code 를 조회하고, 조회된 code 로 지자체 정보를 조회한다.

> <http://localhost:8080/swagger-ui.html#!/region/selectOneUsingGET>



#### 4. 지원하는 지자체 정보 수정 기능 API

> <http://localhost:8080/swagger-ui.html#!/region/updateReionUsingPOST>



#### 5. 지원한도 컬럼에서 지원금액으로 내림차순 정렬하여 특정 개수만 출력하는 API

> <http://localhost:8080/swagger-ui.html#!/region/selectLimitSortedRegionsUsingGET>



#### 6. 이차보전 컬럼에서 보전 비율이 가장 작은 추천 기관명을 출력하는 API

> <http://localhost:8080/swagger-ui.html#!/region/selectMinRateUsingGET>



### < 추가 제약사항 >

**signup과 login을 제외한 모든 API는 Header에 Token 값으로 유효성 검사 실시**

*JWT* 이용 - Access token, Refresh token 생성



#### 7-1. signup 계정 생성 API

> <http://localhost:8080/swagger-ui.html#!/users/signupUsingPOST>



#### 7-2. signin 로그인 API

초기 계정 : admin / admin, client / client

> <http://localhost:8080/swagger-ui.html#!/users/loginUsingPOST>



#### 7-3. refresh 토큰 재발급 API

> <http://localhost:8080/swagger-ui.html#!/users/refreshUsingGET>



## 빌드 및 실행 방법



##### 1. jar 파일이 있는 폴더로 이동

[spring-service](https://github.com/lee-sunmin/spring-service)/**target**/

##### 2. 서비스 시작

``` java -jar spring-service-0.0.1-SNAPSHOT.jar ```
