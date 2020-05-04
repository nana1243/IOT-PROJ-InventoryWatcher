# InventoryWatcher Proj(2020.03.27-04.30)

> 목차
>
> 1. 프로젝트에 설명 및 배경
> 2. 시스템 구성
> 3. 화면구성
>    - Web 구성
>    - Android 구성
>    - IOT구성
> 4.  DB-ERD
> 5. 프로젝트 결과
>
>    



### 1. 프로젝트 설명 및 배경

> 1-1 프로젝트 배경
>
> 팀원들의 다양한 아르바이트 경험 특히,  **매장에서의 재고관리**   그리고  **AWS 원버튼** 의 아이디어를 얻어 로드셀을 활용한 IOT가 결합된 재고관리 시스템을 구축을 목표로 하게 되었습니다.
>
> 1-2 프로젝트가 목표한 서비스에 관한 기능 
>
> - 프랜차이즈 소유주의 가맹점 매출정보 및 재고현황 파악 (웹 서비스)
> - 가맹점주의 매장 매출정보, 재고정보 파악 (안드로이드 앱 , 웹 서비스 제공)
> - 자동 발주 주문서 생성 , 배송상태 확인 가능
> - 매출 정보 및 발주 정보 데이터 분석을 통한 최적의 재고관리 솔루션 제공 
>
> 

### 2. 시스템 구성

![system](https://user-images.githubusercontent.com/52269210/80945520-f182bd00-8e26-11ea-9d7b-a23ef6e50568.JPG)

#### 부가 설명

>1. 안드로이드 및 아두이노
>    1. POS : Android, Java<br>
>    2. Local Pad : Android, Java<br>
>    3. Logistics : JavaFx, ECU(Lattepanda), CAN 통신<br>
>
>2. 웹서버
>    1. Back-End : Spring & Mybatis , AWS EC2 , Apache Tomcat 
>    2. Front-End: Bootstrap, HTML5, AJAX & jQuery<br>
>
>3. 데이터베이스
>    1. DB : Oracle Database , Hadoop HDF
>    2. 분석도구 :Hadoop hive, R
>    3. 시각화: Highcharts ( Java )  
>
>4. API
>    1. FCM
>    2. AWS
> ![PROJ](https://user-images.githubusercontent.com/52269210/80945612-21ca5b80-8e27-11ea-9ca2-f0bbee831b60.JPG)


### 3.  화면구성

#### 3-1 웹 구성(MVC pattern)

-  주체를 기준으로 크게 **2가지**로 나누어 설계하였습니다 . 부가적인 설명은 아래와 같습니다.
> 1. 서비스를 제공 받는 사람 : headquarter(매장을 총 관리하는 본사 직원), user(가맹주)
>
>    1-1 본사[Headquarter]를 위한 페이지   
>      1. CRUD 페이지
>      2. 매장POS로(android) 부터 받은 데이터 모니터링 페이지
>      3. 분석 페이지
>      4. 본사와 가맹주간의 메세지 페이지
>      5. IOT 기계들을 (container) 등록 및 관리 페이지
>      6. 재료 관리 페이지
>   
>    1-2 가맹주[User]를 위한 페이지 
>      1. CRUD 페이지
>      2. 계정 신청 페이지
>      3. 분석 페이지
>      4. 재고관리 및 발주 페이지
>    
>
> | Controller                         | Model                                                   | View                                                         |
> | ---------------------------------- | ------------------------------------------------------- | ------------------------------------------------------------ |
> | MainController StatisticController | salesVO                                                 | main.jsp statMain.jsp                                        |
> | HeadquarterController              | HeadquarterVO,NotiVO UserVO,ChainVO                     | sign.jsp login.jsp addAddr.jsp admin.jsp                     |
> | UserController                     | UserVO,ChainVO,HeadquarterVO NotiVO                     | userapply.jsp                                                |
> | OrderController                    | OrderVO, OrderDetailVO                                  | orderStatus.jsp popup.jsp                                    |
> | IngredientControoler               | ingredientVO                                            | viewFolder : inventory && ingredient                         |
> | PosController                      | MenuVO SalesVO SalesDetailVO                            |                                                              |
> | ContainerController                | ContainerVO IngredientVO OrderVO, OrderDetailVO ChainVO | containerProgress.jsp containerRegisterWizard.jsp showContainerUpdateList.jsp |
> | AdminController                    | ChainVO NotiVO                                          | admin.jsp                                                    |
> | ChainController                    | ContainerVO NotiVO                                      | statMain.jsp                                                 |
>



### 3-2 안드로이드 구성

- 기능에 따라 크게 **3가지로** 나누었습니다. 부가적인 설명은 아래와 같습니다

> ####  3-2-1.POS : 매장의 주문 및 매출에 관련된 정보를 담는 시스템
>
>   1. 컴포넌트( 中 액티비티) -1개의 MainActivity에 8개의 Fragment로 구성
>
>      | MainActivity           | Explain                                         |
>      | ---------------------- | ----------------------------------------------- |
>      | CaculateDialogFragment | 정산확인(영업마감) 메시지를 띄우는 기능         |
>      | CaculateLayoutFragment | 정산기능과 일일 주문건의 정보를 담음            |
>      | CustomDialogFragment   | 메뉴를 담지 않고 주문할 경우 띄우는 메세지 기능 |
>      | DaySellLayoutFragment  | 영업기간과 일일판매량을 표시                    |
>      | LoginDialogFragment    | 로그인 기능                                     |
>      | MenuLayOutFragment     | 메뉴 선택 및 결제 기능                          |
>      | PlaceHolderFragment    | ViewPager 를 사용하기위해 필요한 Default model  |
>      | OrderLayoutFragmnet    | 주문내역,  DB                                   |
>   
>   2. 서버 통신
>    - Android - web 간의 HttpConnection
>     
>      2-1 HttpConnection 클래스 모음
>     
>      | Handler                  | Explain                                                      |
>      | ------------------------ | ------------------------------------------------------------ |
>      | getDailySalesHttpHandler | SpringServer  - DB로 부터 일일 판매량 수신                   |
>      | getSalesHttpHandler      | SpringServer 로 기존 판매량 정보를 송신                      |
>      | LoginHttpHandler         | 안드로이드 앱 ( POS 기) 를 사용하는 관리자의 아이디와 비밀번호를 DB와 비교하여 앱 사용 가능여부를 판단 |
>      | OrderDetailHttpHandler   | 주문 1건의 상세 정보를 전송                                  |
>      | OrderHttpHandler         | 주문 1건의 정보를 전송                                       |
>     
>      
>
> #### 3-2-2.LocalPad : 재고관리를 실시간 모니터링 할 수 있는 테블릿(iot장비와 연동)
>
>   1. 액티비티
>
>      | Activity         | Explain                                           |
>      | ---------------- | ------------------------------------------------- |
>      | MainActivity     | 현재시간과, 배송정보, 평균 재고량을 나타내는 기능 |
>      | ProgressActivity | IOT기계로 부터 받은 데이터 표시                   |
>      
>   2. 서비스
>   
>      -  FCM(Firebase Cloud Messaging)
>   
>   3. 사용된 통신
>   
>    | 통신종류          | Explain                                                  |
>    | ----------------- | -------------------------------------------------------- |
>    | httpUrlConnection | IOT로 부터 받은 데이터를 Android-> WEB 로 데이터 송/수신 |
>    | TCP/IP            | IOT기계로 부터 받은 데이터 송/수신                       |
>
>      
>
> #### 3-2-3  Logistics



### 3-3 Aduino 및 로드셀 구성



> - 기능에 따른 Aduino  구성
>
>   | 기능                                | src folder |
>   | ----------------------------------- | ---------- |
>   | 로드셀로 부터 측정한 데이터 송/수신 | Serial     |
>   | Android와 tcp/ip통신                | tcp/ip2    |
>
> - 로드셀 회로설계
>
>   ![로드셀회로](https://user-images.githubusercontent.com/52269210/80945694-54745400-8e27-11ea-92b8-417cec94a9e4.png)
>
> 

### 3-4 Hadoop 구성

> 1. 연결 흐름도
>
>    - java eclipse -> HDFS->HIVE ->R
>
> 2.  Data FILE 구성
>
>    ![logdata](https://user-images.githubusercontent.com/52269210/80945790-93a2a500-8e27-11ea-8588-7e6b3baa8148.JPG)  
>
> 3. hive
>
>    ![Hadoop](https://user-images.githubusercontent.com/52269210/80945872-cba9e800-8e27-11ea-8d83-07ac08954088.jpg) ![hadoop2](https://user-images.githubusercontent.com/52269210/80945940-e7ad8980-8e27-11ea-8fa4-e07b0579c082.jpg)


### 4. DB - ERD

![ERD설계](https://user-images.githubusercontent.com/52269210/80946110-483cc680-8e28-11ea-8d84-aa5c375c7824.png)

### 5. 프로젝트  결과

5-1 동영상 시연 

[top-youtube]: https://youtu.be/6x_5fEjYh4E	"youtube-click "


5-2 프로젝트 사진(대표 사진 2개 이하로 첨부하였습니다)

> 1.로드셀 장비
>
> <img src="https://user-images.githubusercontent.com/52269210/80946182-6d313980-8e28-11ea-9e08-b6627b0e8b70.png" alt="로드셀" style="zoom:33%;" />   
>
> <img src="https://user-images.githubusercontent.com/52269210/80946201-79b59200-8e28-11ea-9454-10219aded0d0.png" alt="로드셀2" style="zoom:33%;" />       
>
> 2. web
>
>    ![web3](https://user-images.githubusercontent.com/52269210/80946412-f9436100-8e28-11ea-9646-da3c6094c66d.png)
>
>    <img src="https://user-images.githubusercontent.com/52269210/80946342-ca2cef80-8e28-11ea-8146-a512bb50ed2c.png" alt="web1" style="zoom:50%;" /> 
>
>
>    3.android_localpad
>
>    ![android_localpad2](https://user-images.githubusercontent.com/52269210/80946506-27c13c00-8e29-11ea-83cc-a7850e600b4c.png)
>
>    ![android_localpad](https://user-images.githubusercontent.com/52269210/80946550-3c053900-8e29-11ea-80cc-cf381fb76d74.png)
>
>    
>
>    
>
>    4. pos
>
>       ![pos](https://user-images.githubusercontent.com/52269210/80946627-6f47c800-8e29-11ea-8ee8-656c6baf0605.png)
>
>       ![pos2](https://user-images.githubusercontent.com/52269210/80946695-8e465a00-8e29-11ea-829a-3d0bc9f37f84.png)
>
>    5.Logistic
>
>       ![semi_plusalpha](https://user-images.githubusercontent.com/52269210/80946799-cb125100-8e29-11ea-985e-8b83c8c62b72.png)   
>
>       
>
>    6. webView in Android
>
>          <img src="https://user-images.githubusercontent.com/52269210/80946838-e2513e80-8e29-11ea-979c-512cb72b93ef.png" alt="android_webview" style="zoom: 33%;" /> 
>
>          
>
>    7. teamWork 관련 사진
>
>          <img src="https://user-images.githubusercontent.com/52269210/80947094-63103a80-8e2a-11ea-8fd7-fbbdb74c8e0b.jpg" alt="top2" style="zoom:35%;" />     <img src="https://user-images.githubusercontent.com/52269210/80947165-8aff9e00-8e2a-11ea-9d37-ad5e20352957.jpg" alt="top4" style="zoom:35%;" />
>
>        

