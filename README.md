# Hotel Project

&nbsp;     
&nbsp;    

## Project Overview
> - 기여도 : 100%
> - 담당 기능 : 전체

&nbsp;     
&nbsp;  

## Project Tech Stack
> - **Framework** : Spring Boot (3.2.2)
> - **IDE** : IntelliJ
> - **DB** :  PostgreSQL
> - **SQLMapper** : Mybatis
> - **Language** : Java(JDK 17)
> - **Build Tool** : Gradle
> - **VCS** : Git

&nbsp;     
&nbsp;    

## Project Architecture
```text
 src
  ├─config
  │      DataBaseConfig.java : HikariCP 를 이용해 데이터 소스를 설정하고, 애플리케이션과 데이터베이스를 연결 풀을 관리하기 위한 파일
  │      EmailConfig.java : 애플리케이션에서 이메일 발송을 위한 STMP 설정 파일
  │      MvcConfig.java : 실제 이미지 파일이 저장된 폴더를 애플리케이션에서 접근할 수 있도록 설정한 파일
  │      PaymentConfig.java : PG 통합 관리 서비스인 IamPort API 를 사용하기 위한 설정 파일
  │      RedisConfig.java : Redis 연결 설정 파일
  │
  ├─controller
  │      AnswerController.java : 문의 답변과 관련된 요청을 처리하는 컨트롤러
  │      BannerController.java : 호텔 메인 배너와 관련된 요청을 처리하는 컨트롤러
  │      CustomerManageController.java : 관리자의 회원관리와 관련된 요청을 처리하는 컨트롤러
  │      HotelController.java : 호텔 소개글의 관련된 요청을 처리하는 컨트롤러
  │      HotelLayoutController.java : 호텔 구조관리의 관련된 요청을 처리하는 컨트롤러
  │      PaymentController.java : 결제 관련된 요청을 처리하는 컨트롤러
  │      QuestionController.java : 문의와 관련된 요청을 처리하는 컨트롤러
  │      QuestionTypeController.java : 문의 유형의 관련된 요청을 처리하는 컨트롤러
  │      ReservationController.java : 예약과 관련된 요청을 처리하는 컨트롤러
  │      RoomController.java : 객실과 관련된 요청을 처리하는 컨트롤러
  │      RoomTypeController.java : 객실 유형과 관련된 요청을 처리하는 컨트롤러
  │      UserController.java : 회원과 관련된 요청을 처리하는 컨트롤러
  │
  ├─dto
  │  │  Pagination.java : 페이징 정보를 계산하여 저장하는 DTO
  │  │  ResPaging.java : 제네릭을 사용한 페이징 응답 DTO로, 페이징된 결과와 필요한 목록 데이터를 포함하고 있는 응답 DTO 
  │  │  Search.java : 공통된 페이지 데이터를 가지고있는 최상위 페이징 요청 DTO (검색해야하는 데이터는 각각 다르기에 공통된 데이터만 뽑음)
  │  │  SearchDto.java : Search 클래스를 상속하고 검색데이터를 저장하는 요청 DTO
  │  │
  │  ├─request
  │  │  ├─answer
  │  │  │      ReqAnswerAdd.java : 문의 답변 등록 요청 DTO
  │  │  │      ReqAnswerUpdate.java : 문의 답변 수정 요청 DTO
  │  │  │
  │  │  ├─banner
  │  │  │      ReqBannerAdd.java : 배너 등록 요청 DTO
  │  │  │      ReqBannerDelete.java : 배너 삭제 요청 DTO
  │  │  │      ReqBannerImg.java : 배너 이미지 요청 DTO
  │  │  │      ReqBannerUpdate.java : 배너 수정 요청 DTO
  │  │  │
  │  │  ├─hotel
  │  │  │      ReqEdtorImg.java : 에디터 이미지 요청 DTO
  │  │  │      ReqHotelImg.java : 호텔 소개 이미지 요청 DTO
  │  │  │      ReqIntroAdd.java : 호텔 소개 등록 요청 DTO
  │  │  │      ReqIntroUpdate.java 호탤 소개 수정 요청 DTO
  │  │  │
  │  │  ├─layout
  │  │  │      ReqLayoutAdd.java : 호텔 구조 등록 요청 DTO
  │  │  │      ReqLayoutRoomAdd.java : 호텔 구조에서 객실 등록 요청 DTO
  │  │  │      ReqLayoutUpdate.java : 구조 수정 요청 DTO
  │  │  │
  │  │  ├─payment
  │  │  │      ReqPaymentInfoAdd.java : 결제 정보 등록 요청 DTO
  │  │  │
  │  │  ├─question
  │  │  │      QuestionSearchDTO.java : 문의 목록(검색) 요청 DTO
  │  │  │      ReqQuestionAdd.java : 문의 등록 요청 DTO
  │  │  │      ReqQuestionUpdate.java : 문의 수정 요청 DTO
  │  │  │
  │  │  ├─questiontype
  │  │  │      ReqQuestionTypeAdd.java : 문의 유형 등록 요청 DTO
  │  │  │      ReqQuestionTypeUpdate.java : 문의 유형 수정 요청 DTO
  │  │  │
  │  │  ├─reservation
  │  │  │      ReqReservationAdd.java : 예약 등록 요청 DTO
  │  │  │      ReqReserveCancel.java : 예약 취소 요청 DTO
  │  │  │      ReserveSearchDTO.java : 예약 현황 목록(검색) 요청 DTO
  │  │  │
  │  │  ├─room
  │  │  │      ReqOptions.java : 객실 등록 중 옵션 정보 요청 DTO
  │  │  │      ReqRoomAdd.java : 객실 등록 요청 DTO
  │  │  │      ReqRoomImg.java : 객실 등록 중 이미지 정보 요청 DTO
  │  │  │      ReqRoomUpdate.java : 객실 수정 요청 DTO
  │  │  │
  │  │  ├─roomtype
  │  │  │      ReqRoomTypeAdd.java : 객실 유형 등록 요청 DTO
  │  │  │      ReqRoomTypeUpdate.java : 객실 유형 수정 요청 DTO
  │  │  │      ReqTypeImg.java : 객실 유형 이미지 요청 DTO
  │  │  │
  │  │  └─user
  │  │          ReqAuthCodeChk.java : 이메일 인증코드 인증 요청 DTO
  │  │          ReqCustomerStatusUpdate.java : 회원 가입 상태 수정 요청 DTO
  │  │          ReqEmpApproval.java : 직원 가입 수락 및 거절 요청 DTO
  │  │          ReqSendAuthCode.java : 이메일 인증 코드 전송 요청 DTO
  │  │          ReqUserAdd.java : 회원 가입 요청 DTO
  │  │          ReqUserLogin.java : 로그인 요청 DTO
  |  |          CustomerSearchDTO.java : 관리자 페이지에서 유저 목록(검색) 요청 DTO
  │  │
  │  └─response
  │      │  ResponseDTO.java : 공통 응답 형식을 정의하는 DTO
  │      │
  │      ├─banner
  │      │      ResBannerList.java : 배너 목록 응답 DTO
  │      │
  │      ├─hotel
  │      │      ResHotelIntro.java : 호텔 소개글 내용 응답 DTO
  │      │      ResIntroDetail.java : 호텔 소개글 상세 응답 DTO
  │      │      ResIntroList.java : 호텔 소개글 목록 응답 DTO 
  │      │
  │      ├─layout
  │      │      ResLayoutList.java : 호텔 구조 목록 응답 DTO
  │      │      ResRoomLayout.java : 호텔 구조 중 객실 목록 응답 DTO
  │      │      ResTodayReserveList.java : 호텔 구조 중 오늘 예약 현황 목록 응답 DTO
  │      │
  │      ├─question
  │      │      ResQuestionList.java : 문의 목록 응답 DTO
  │      │
  │      ├─questiontype
  │      │      ResQuestionTypeList.java : 문의 유형 목록 응답 DTO
  │      │
  │      ├─reservation
  │      │      ResReserveCancel.java : 예약 취소 응답 DTO
  │      │      ResReserveInfo.java : 예약 정보 상세 응답 DTO
  │      │      ResReserveList.java : 예약 현황 목록 응답 DTO
  │      │
  │      ├─room
  │      │      ResOptions.java : 객실 옵션 정보 응답 DTO
  │      │      ResRoomDetail.java : 객실 상세 응답 DTO
  │      │      ResRoomImg.java : 객실 이미지 정보 응답 DTO
  │      │      ResRoomList.java : 객실 목록 응답 DTO
  │      │
  │      ├─roomtype
  │      │      ResRoomTypeDetail.java : 객실 유형 상세 응답 DTO
  │      │      ResRoomTypeList.java : 객실 유형 목록 응답 DTO
  │      │
  │      └─user
  │              ResCustomerManageList.java : 관리자 페이지에서 회원 관리 목록 응답 DTO
  │              ResUserInfo.java : 회원 정보 응답 DTO
  │              ResUserLogin.java : 회원 로그인 응답 DTO
  │
  ├─enums
  │      IamPortUrl.java : IamPort 요청 URL을 나타내는 enum
  │      ImgType.java : 이미지의 타입을 나타내는 enum
  │      IntroStatus.java : 소개글 공개 여부를 나타내는 enum
  │      OptionType.java : 객실 옵션의 유형을 나타내는 enum
  │      QuestionStatus.java : 문의 유형을 나태내는 enum
  │      ReservationStatus.java : 예약 상태를 나타내는 enum
  │      Role.java : 회원권한을 나타내는 enum
  │      UserAuthStatus.java : 회원의 인증 상태를 나타내는 enum
  │      UserDeleteYN.java : 회원의 탈퇴 여부를 나타내는 enum
  │      UserManageCode.java : 회원의 삭제와 복구를 나타내고 그에 따른 로직을 나타내는 enum
  │
  ├─Exception
  │      CustomException.java : 커스텀 예외 클래스(동기)
  │      ErrorCode.java : 내가 원하는 상태코드와 에러메세지를 나타낸 enum
  │      ErrorResponse.java : 발생한 커스텀 에러와 응답 데이터 포맷을 설정해주는 파일
  │      GlobalExceptionHandler.java : 전역에서 발생한 커스텀 예외 및 유효성 검사 예외 등 모든 예외를 핸들링하는 클래스(동기)
  │      RestCustomException.java : 커스텀 예외 클래스(동기)
  │      RestGlobalExceptionHandler.java :전역에서 발생한 커스텀 예외 및 유효성 검사 예외 등 모든 예외를 핸들링하는 클래스(비동기)
  │
  ├─mapper
  │      AnswerMapper.java : 문의답변과 관련된 데이터베이스 접근 담당
  │      BannerMapper.java : 배너와 관련된 데이터 베이스 접근담당
  │      CustomerManageMapper.java : 회원관리와 관련된 데이터베이스 접근 담당
  │      HotelLayoutMapper.java : 호텔 구조와 관련된 데이터베이스 접근 담당
  │      HotelMapper.java : 호탤 소개와 관련된 데이터베이스 접근 담당
  │      PaymentMapper.java : 결제와 관련된 데이터베이스 접근 담당
  │      QuestionMapper.java : 문의와 관련된 데이터베이스 접근 담당
  │      QuestionTypeMapper.java : 문의 유형과 관련된 데이터베이스 접근 담당
  │      ReservationMapper.java : 예약과 관련된 데이터베이스 접근 담당
  │      RoomMapper.java : 객실과 관련된 데이터베이스 접근 담당
  │      RoomTypeMapper.java : 객실 유형과 관련된 데이터베이스 접근 담당
  │      UserMapper.java : 회원과 관련된 데이터베이스 접근 담당
  │
  ├─service
  │      AnswerService.java : 문의 응답 관련 비즈니스 로직 처리 
  │      BannerService.java : 배너 관련 비즈니스 로직 처리
  │      CustomerManageService.java : 회원관리 관련 비즈니스 로직 처리
  │      EmailService.java : 이메일 인증 관련 비즈니스 로직 처리
  │      HotelLayoutService.java : 호텔 구조 관련 비즈니스 로직 처리
  │      HotelService.java : 호텔 소개 관련 비즈니스 로직 처리
  │      PaymentService.java : 결제 관련 비즈니스 로직 처리
  │      QuestionService.java : 문의 관련 비즈니스 로직 처리
  │      QuestionTypeService.java : 문의 유형 관련 비즈니스 로직 처리
  │      ReservationService.java : 예약 관련 비즈니스 로직 처리
  │      RoomService.java : 객실 관련 비즈니스 로직 처리
  │      RoomTypeService.java : 객실 유형 관련 비즈니스 로직 처리
  │      UserService.java : 회원 관련 비즈니스 로직 처리
  │
  ├─user
  │      empApproveSignup.java : 사원 가입 허가 처리 클래스
  │      EmpDecisionSignup.java : 사원 가입 처리 공통 인터페이스
  │      empRejectSignup.java : 사원 가입 불허 처리 클래스
  │      FindPwUserValidateStrategy.java : 비밀번호 찾기 중 회원 검사 클래스
  │      SignUpUserValidateStrategy.java : 회원가입 중 회원 검사 클래스
  │      UserValidateStrategy.java : 회원 검사 공통 인터페이스
  │
  └─util
          CommonUtils.java : 전역에서 사용하는 공통된 메서드를 모아놓은 유틸 클래스
          IamPortKeys.java : Properties 파일에 있는 IamPort 관련 키들을 정의한 컴포넌트 클래스
          RedisUtil.java : Redis 의 CRUD 기능을 정의한 유틸 클래스
          ScheduleUtils.java : 스케쥴링 기능을 정의한 유틸 클래스
```
&nbsp;     
&nbsp;    

## 아쉬운 점  
> 동기와 비동기 방식을 혼용하면서, 하나의 컨트롤러에서 일일이 `@ResponseBody`를 붙인 점이 아쉬웠습니다.    
> 동기 또는 비동기 중 하나의 방식으로 통일해 작성하는 것이 더 나았을 것 같습니다.    
> 전역 예외 처리를 동기 방식으로 구현해 에러 페이지를 띄우도록 했으나, 비동기 방식이 추가되면서 제대로 작동하지 않았고,   
> 그로 인해 비동기 전용 예외 핸들러와 예외 클래스를 따로 작성하면서 혼란이 생겼습니다.   
> 이 부분 역시 개선이 필요하다고 생각합니다.    
> 또한, DB 테이블 구성에서도 불필요한 테이블이 있었다고 느꼈습니다.     
> 일부 테이블은 물리 삭제 대신 논리 삭제를 적용하거나,    
> 삭제된 정보를 별도로 관리하는 전용 테이블을 만들어 백업을 대비했어야 했는데, 이를 고려하지 못한 점이 아쉽습니다.    
> 이미지 등록 기능에서도 중복 코드를 통일하지 못한 점과,   
> 테스트 중 사용자 입장에서 이미지 미리 보기가 없었던 점이 불편했습니다.       
> 이 기능을 모두 구현하지 못한 것도 아쉬움으로 남습니다.    
> 그리고 동시 입력 문제를 많이 고민했지만 답을 찾지 못했었는데,   
> 최근 포트폴리오를 작성하면서 좋은 아이디어가 떠올랐습니다.         
> 동시 입력한 모든 유저의 예약 정보를 무조건 "임시 예약"으로 저장해 두기 때문에 결제하기 버튼을 눌렀을 때,      
> 같은 예약 정보에서 가장 빠른 순서의 유저만 성공 처리하고 나머지는 실패로 돌리면 어떨까 하는 생각이 들었습니다.     
> 다음번에는 이 방법을 적용해 지인의 도움을 받아 동시 입력 테스트를 진행해 보겠습니다.     
