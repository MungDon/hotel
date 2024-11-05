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
  │      DataBaseConfig.java
  │      EmailConfig.java
  │      MvcConfig.java
  │      PaymentConfig.java
  │      RedisConfig.java
  │
  ├─controller
  │      AnswerController.java
  │      BannerController.java
  │      CustomerManageController.java
  │      HotelController.java
  │      HotelLayoutController.java
  │      PaymentController.java
  │      QuestionController.java
  │      QuestionTypeController.java
  │      ReservationController.java
  │      RoomController.java
  │      RoomTypeController.java
  │      UserController.java
  │
  ├─dto
  │  │  Pagination.java
  │  │  ResPaging.java
  │  │  Search.java
  │  │  SearchDto.java
  │  │
  │  ├─request
  │  │  ├─answer
  │  │  │      ReqAnswerAdd.java
  │  │  │      ReqAnswerUpdate.java
  │  │  │
  │  │  ├─banner
  │  │  │      ReqBannerAdd.java
  │  │  │      ReqBannerDelete.java
  │  │  │      ReqBannerImg.java
  │  │  │      ReqBannerUpdate.java
  │  │  │
  │  │  ├─hotel
  │  │  │      ReqEdtorImg.java
  │  │  │      ReqHotelImg.java
  │  │  │      ReqIntroAdd.java
  │  │  │      ReqIntroUpdate.java
  │  │  │
  │  │  ├─layout
  │  │  │      ReqLayoutAdd.java
  │  │  │      ReqLayoutRoomAdd.java
  │  │  │      ReqLayoutUpdate.java
  │  │  │
  │  │  ├─payment
  │  │  │      ReqPaymentInfoAdd.java
  │  │  │
  │  │  ├─question
  │  │  │      QuestionSearchDTO.java
  │  │  │      ReqQuestionAdd.java
  │  │  │      ReqQuestionUpdate.java
  │  │  │
  │  │  ├─questiontype
  │  │  │      ReqQuestionTypeAdd.java
  │  │  │      ReqQuestionTypeUpdate.java
  │  │  │
  │  │  ├─reservation
  │  │  │      ReqReservationAdd.java
  │  │  │      ReqReserveCancel.java
  │  │  │      ReserveSearchDTO.java
  │  │  │
  │  │  ├─room
  │  │  │      ReqOptions.java
  │  │  │      ReqRoomAdd.java
  │  │  │      ReqRoomImg.java
  │  │  │      ReqRoomUpdate.java
  │  │  │
  │  │  ├─roomtype
  │  │  │      ReqRoomTypeAdd.java
  │  │  │      ReqRoomTypeUpdate.java
  │  │  │      ReqTypeImg.java
  │  │  │
  │  │  └─user
  │  │          ReqAuthCodeChk.java
  │  │          ReqCustomerStatusUpdate.java
  │  │          ReqEmpApproval.java
  │  │          ReqSendAuthCode.java
  │  │          ReqUserAdd.java
  │  │          ReqUserLogin.java
  │  │
  │  └─response
  │      │  ResponseDTO.java
  │      │
  │      ├─banner
  │      │      ResBannerList.java
  │      │
  │      ├─hotel
  │      │      ResHotelIntro.java
  │      │      ResIntroDetail.java
  │      │      ResIntroList.java
  │      │
  │      ├─layout
  │      │      ResLayoutList.java
  │      │      ResRoomLayout.java
  │      │      ResTodayReserveList.java
  │      │
  │      ├─question
  │      │      ResQuestionList.java
  │      │
  │      ├─questiontype
  │      │      ResQuestionTypeList.java
  │      │
  │      ├─reservation
  │      │      ResReserveCancel.java
  │      │      ResReserveInfo.java
  │      │      ResReserveList.java
  │      │
  │      ├─room
  │      │      ResOptions.java
  │      │      ResRoomDetail.java
  │      │      ResRoomImg.java
  │      │      ResRoomList.java
  │      │
  │      ├─roomtype
  │      │      ResRoomTypeDetail.java
  │      │      ResRoomTypeList.java
  │      │
  │      └─user
  │              CustomerSearchDTO.java
  │              ResCustomerManageList.java
  │              ResUserInfo.java
  │              ResUserLogin.java
  │
  ├─enums
  │      IamPortUrl.java
  │      ImgType.java
  │      IntroStatus.java
  │      OptionType.java
  │      QuestionStatus.java
  │      ReservationStatus.java
  │      Role.java
  │      UserAuthStatus.java
  │      UserDeleteYN.java
  │      UserManageCode.java
  │
  ├─Exception
  │      CustomException.java
  │      ErrorCode.java
  │      ErrorResponse.java
  │      GlobalExceptionHandler.java
  │      RestCustomException.java
  │      RestGlobalExceptionHandler.java
  │
  ├─mapper
  │      AnswerMapper.java
  │      BannerMapper.java
  │      CustomerManageMapper.java
  │      HotelLayoutMapper.java
  │      HotelMapper.java
  │      PaymentMapper.java
  │      QuestionMapper.java
  │      QuestionTypeMapper.java
  │      ReservationMapper.java
  │      RoomMapper.java
  │      RoomTypeMapper.java
  │      UserMapper.java
  │
  ├─service
  │      AnswerService.java
  │      BannerService.java
  │      CustomerManageService.java
  │      EmailService.java
  │      HotelLayoutService.java
  │      HotelService.java
  │      PaymentService.java
  │      QuestionService.java
  │      QuestionTypeService.java
  │      ReservationService.java
  │      RoomService.java
  │      RoomTypeService.java
  │      UserService.java
  │
  ├─user
  │      empApproveSignup.java
  │      EmpDecisionSignup.java
  │      empRejectSignup.java
  │      FindPwUserValidateStrategy.java
  │      SignUpUserValidateStrategy.java
  │      UserValidateStrategy.java
  │
  └─util
          CommonUtils.java
          IamPortKeys.java
          RedisUtil.java
          ScheduleUtils.java
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
