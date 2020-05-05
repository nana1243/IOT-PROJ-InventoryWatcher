# InventoryWatcher Proj(2020.03.27-04.30) - 수정중
### 목차

> 1. 프로젝트에 설명 및 배경
> 2. 시스템 구성
> 3. 프로젝트 구성(그림다시)
> 4. 화면구성
>    - Web 구성
>    - Android 구성
>    - IOT구성
> 5.  DB-ERD
> 6. 프로젝트 결과
>    cf> 로드셀까지 수정완료
>    


### 1. 프로젝트 설명 및 배경

       1-1 프로젝트 배경

           팀원들의 다양한 아르바이트 경험 특히,매장에서의 재고관리 그리고  AWS 원버튼의 아이디어를 얻어 
           로드셀을 활용한 IOT가 결합된 재고관리 시스템을 구축을 목표로 하게 되었습니다.

       1-2 프로젝트가 목표한 서비스에 관한 기능 
            1. 프랜차이즈 소유주의 가맹점 매출정보 및 재고현황 파악 (웹 서비스)
            2. 가맹점주의 매장 매출정보, 재고정보 파악 (안드로이드 앱 , 웹 서비스 제공)
            3. 자동 발주 주문서 생성 , 배송상태 확인 가능
            4. 매출 정보 및 발주 정보 데이터 분석을 통한 최적의 재고관리 솔루션 제공 



### 2. 시스템 구성

 ![system](https://user-images.githubusercontent.com/52269210/80945520-f182bd00-8e26-11ea-9d7b-a23ef6e50568.JPG ) 
 
### 3. 프로젝트 구조
 
 ![PROJ](https://user-images.githubusercontent.com/52269210/80945612-21ca5b80-8e27-11ea-9ca2-f0bbee831b60.JPG )


####          cf>부가 설명

              1. 안드로이드 및 아두이노
                       1.POS : Android, Java
                       2. Local Pad : Android, Java
                       3. Logistics : JavaFx, ECU(Lattepanda), CAN 통신

              2.  웹서버
                      1. Back-End : Spring & Mybatis , AWS EC2 , Apache Tomcat 
                      2. Front-End: Bootstrap, HTML5, AJAX & jQuery

             3. 데이터베이스
                     1. DB : Oracle Database , Hadoop HDF
                     2.분석도구 :Hadoop hive, R
                     3.시각화: Highcharts ( Java )  





### 4.  화면구성

####   1. 웹 구성
- 주체를 기준으로 크게 **2가지**로 나누어 설계하였습니다.

####      1-1. 본사[Headquarter]를 위한 페이지   

        1. CRUD 페이지
        2. 매장POS로(android) 부터 받은 데이터 모니터링 페이지
        3. 분석 페이지
        4. 본사와 가맹주간의 메세지 페이지
        5. IOT 기계들을 (container) 등록 및 관리 페이지
        6. 재료 관리 페이지

####      1-2. 가맹주[User]를 위한 페이지   

        1. CRUD 페이지
        2. 계정 신청 페이지
        3. 분석 페이지
        4. 재고관리 및 발주 페이지

<br>
<br>

####  2. 안드로이드 구성

- 기능에 따라 크게 **3가지로** 나누었습니다.

####      2-1.POS : 매장의 주문 및 매출에 관련된 정보를 담는 시스템

        1 . Activity: -1개의 MainActivity에 8개의 Fragment로 구성
           1-1. 정산확인(영업마감) 메시지를 띄우는 기능(CaculateDialogFragment)
           1-2. 정산기능과 일일 주문건의 정보를 담음(CaculateLayoutFragment)
           1-3. 메뉴를 담지 않고 주문할 경우 띄우는 메세지 기능(CustomDialogFragment)
           1-4. 영업기간과 일일판매량을 표시 (DaySellLayoutFragment)
           1-5. 로그인 기능(LoginDialogFragment)
           1-6. 메뉴 선택 및 결제 기능(MenuLayOutFragment)
           1-7. ViewPager 를 사용하기위해 필요한 Default model(PlaceHolderFragment)
           1-8. 주문내역,  DB (OrderLayoutFragmnet)
           
        2. 서버 통신
           -Android - web 간의 HttpConnection(sales관련 데이터 전송 -> poscontroller에서 받는다)


####   

        
####      2-2.LocalPad : 재고관리를 실시간 모니터링 할 수 있는 테블릿(iot장비와 연동)

        1 . Activity: - 2개의 Activity로 구성
           1-1 현재시간과, 배송정보, 평균 재고량을 나타내는 기능(mainactivity)
           1-2 IOT기계로 부터 받은 데이터 표시(ProgressActivity)
           
        2. 서비스
           - FCM(Firebase Cloud Messaging)

        3. 통신
           - http통신 : 센서데이터를 웹상으로 전달
           - tcp/ip : 센서데이터를 수신


####   



### 4-3 로드셀 구성
   1. 로드셀 회로 설계
   
![로드셀회로](https://user-images.githubusercontent.com/52269210/80945694-54745400-8e27-11ea-92b8-417cec94a9e4.png)


### 4-4 Hadoop 구성

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
>    <img src="https://user-images.githubusercontent.com/52269210/80945872-cba9e800-8e27-11ea-8d83-07ac08954088.jpg" alt="Hadoop" style="zoom: 35%;" />  <img src="https://user-images.githubusercontent.com/52269210/80945940-e7ad8980-8e27-11ea-8fa4-e07b0579c082.jpg" alt="hadoop2" style="zoom:35%;" />


### 4. DB - ERD

![ERD설계](https://user-images.githubusercontent.com/52269210/80946110-483cc680-8e28-11ea-8d84-aa5c375c7824.png)

### 5. 프로젝트  결과

5-1 동영상 시연 : https://youtu.be/6x_5fEjYh4E



5-2 프로젝트 사진(대표 사진 2개 이하로 첨부하였습니다)

> 1.로드셀 장비
>
> <img src="https://user-images.githubusercontent.com/52269210/80946182-6d313980-8e28-11ea-9e08-b6627b0e8b70.png" alt="로드셀" style="zoom:20%;" />    <img src="https://user-images.githubusercontent.com/52269210/80946201-79b59200-8e28-11ea-9454-10219aded0d0.png" alt="로드셀2" style="zoom:20%;" />
>
>       
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

