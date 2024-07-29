$(function(){
    const innerElement = $(".innerElement"); // 모달 내용박스
    const modal = $("#modalCon");     // 모달창
    const user_sid = $("#user_sid");
    const role_user = $("#role_user");

    let reserveObj = {};
    $(".roomDetail").click((event) => { // 클래스가 roomDetail인것을 클릭하면     //필요한 데이터

        const room_sid = $(event.target).data('room-sid'); // 해당 요소의 data 를가져옴
        const startDate = $(".start_date").val();
        const endDate = $(".end_date").val();
        const adultCnt = $(".adult_cnt").val();
        const childCnt = $(".child_cnt").val();
        reserveObj = {
            start_date: startDate,
            end_date: endDate,
            adult_cnt: adultCnt,
            child_cnt: childCnt
        }
        const ajaxObj = {
            url: API_LIST.ROOM_DETAIL + room_sid,
            method: "get",
            successFn: (detailData) => {
                createRoomDetailModal(detailData);
            }
        }
        ajaxCall(ajaxObj);
    });

    const createRoomDetailModal = (detailData) => {
        innerElement.empty();
        const imagesHTML = detailData.images.map(img =>
            `<div class="slider" role="group" aria-label="5/5">
            <img src="/img/${img.img_name}">
            </div>`
        ).join("");

        const optionsHTML = detailData.options.map(option => {
            if (option.option_type === '객실정보') {
                return `<div class="roomInfo">
                        <span><b class="optionName">${option.option_name}</b></span> 
                        <span class="optionValue">${option.option_value}</span>
                    </div>`;
            } else if (option.option_type === '객실이용') {
                return `<div class="roomUse">
                        <span class="UseName"><b>${option.option_name}</b></span>
                        <span>○ ${option.option_value}</span>
                    </div>`;
            }
            return '';
        }).join("");

        const modalContent = `
        <div class='roomName'>
        <span>${detailData.room_name}</span>
        </div>
        <section class="sliderType">
            <div class="slider_wrap">
                <div class="slider_img">
                    <div class="slider_inner">
                        ${imagesHTML}
                    </div>
                </div>
                <div class="slider_btn">
                    <button type="button" class="prev moveBtn" >prev</button>
                    <button type="button" class="next moveBtn" >next</button>
                </div>
            </div>
        </section>
        <h2>객실 상세 정보</h2>
        <div class="roomDetailInfo">
            ${optionsHTML}
        </div>
        <div class="personLimit">
            <div class="totalLimit">
                <h3>최대 투숙 가능인원</h3>
                <span>${detailData.adult_limit + detailData.child_limit}명</span>
            </div>
            <div class="detailLimit">
                <div class="adultLimit">
                    <h3>최대 성인</h3>
                    <span>${detailData.adult_limit}명</span>
                </div>
                <div class="childLimit">
                    <h3>최대 소아</h3>
                    <span>${detailData.child_limit}명</span>
                </div>
            </div>
        </div>
        <div class="price">
            <h3>객실 요금(1박기준)</h3>
            <span>${detailData.price.toLocaleString()} 원</span>
        </div>
        <div class="detailBtns">
            ${user_sid.val() != null &&(role_user.val() == 'STAFF' || role_user.val() == 'ADMIN') ?
            `<button type="button" class="deleteRoom" value="${detailData.room_sid}">삭제하기</button>
                <button type="button" class="updateRoom" value="${detailData.room_sid}">수정하기</button>` :
            ''}
            <button type="button" class="closeBtn">닫기</button>
            <button type="button" class="reserveBtn" value="${detailData.room_sid}">예약하기</button>
        </div>
    `;
        innerElement.append(modalContent);
        openModal(modal);
    };

     $(document).on("click", ".closeBtn, .cancelBtn", () => {
         innerElement.empty();
         closeModal(modal);
     });

     $(document).on("click", ".removeRoom", (event) => {
         let room_sid = $(event.target).val();
         const thenFn = (result) => {
             if (result.isConfirmed) {
                 const ajaxObj = {
                     url: API_LIST.PERMANENTLY_DELETE,
                     method: "delete",
                     param: { room_sid },
                     successFn: () => {
                         swalCall("성공", "객실이 영구 삭제되었습니다.", "success");
                     }
                 };
                 ajaxCall(ajaxObj);
             }
         };
         swalCall("경고", "지금 삭제하면 영구 삭제됩니다<br>삭제하시겠습니까?", "warning", thenFn, "예", true);
     });

     $(document).on("click", ".reserveBtn", (event) => {
         if(isNull(user_sid.val())){
             const thenFn = () => {
                 location.href ="/user/login"
             }
             swalCall("경고","로그인이 필요한 서비스입니다","warning");
             return;
         }
         reserveObj.user_sid = user_sid.val(); // 값을 할당
         reserveObj.room_sid = $(event.target).val();
         const ajaxObj = {
             url: API_LIST.RESERVE_ROOM_FORM,
             method: "get",
             param: reserveObj,
             successFn: () => {
                 $(".detailBtns").remove();
                 createReserveForm(reserveObj);
             }
         };
         ajaxCall(ajaxObj); // AJAX 요청 호출 추가
     });
     const createReserveForm = (reserveObj) => {
         const personCntHTML =
             `<div className="personCount">
                 <span>예약 인원</span>
                 ${reserveObj.adult_cnt != null ? `<span>${reserveObj.adult_cnt} 성인</span>` : ''}
                 ${reserveObj.child_cnt != null ? `<span>${reserveObj.child_cnt} 소아</span>` : ''}
             </div>`;
         innerElement.append(
             `<div class="reserveInfo">
                <h2>예약 정보</h2>
                 <div class="reserveDate">
                 <span class="StayPeriod">숙박 기간</span>
                     <span>${reserveObj.start_date}</span>
                     <span>${reserveObj.end_date}</span>
                 </div>
                 ${personCntHTML}
                 <span>위 예약정보로 예약하시겠습니까?</span>
                 <div class="reserveBtnBox">
                    <button type="button" class="cancelReserveBtn">취소</button>
                    <button type="button" class="reserveCompleteBtn" value="${reserveObj.user_sid}">예약완료</button>
                 </div>
             </div>`
         )
     }
     $(document).on("click",".cancelReserveBtn", () =>{
         $(".reserveInfo").remove();
         innerElement.append(
             `
           <div class="detailBtns">
              ${user_sid.val() != null && (role_user.val() == 'STAFF' || role_user.val() == 'ADMIN') ?
             `<button type="button" class="deleteRoom" value="${reserveObj.room_sid}">삭제하기</button>
              <button type="button" class="updateRoom" value="${reserveObj.room_sid}">수정하기</button>` :
              ''}
              <button type="button" class="closeBtn">닫기</button>
              <button type="button" class="reserveBtn" value="${reserveObj.room_sid}">예약하기</button>
           </div>
             `

         )
     });
 // 휴지통
     $(document).on("click", ".deleteList", function () {
         $(".deleteRooms").toggle();
         let buttonText = $(this).text();


         ajaxCall("/room/delete/list", "GET", null, function (data) {
                 let html = "";

                 data.forEach(function (room) {
                     html += `
                                 <table class="dtable">
                                     <tbody>
                                         <tr>
                                             <td>${room.room_info}</td>
                                         </tr>
                                         <tr>
                                             <td colspan="2">
                                                 <img src="/img/${room.thumbnail.img_name}" style="margin-bottom: 20px; width: 400px">
                                             </td>
                                            </tr>
                             `;

                     room.options.forEach(function (option) {
                         html += `
                                     <tr>
                                         <th>${option.option_name}</th>
                                         <td>${option.option_value}</td>
                                     </tr>
                                     </tbody>
                                     </table>
                                 `;
                     });


                     html += `
                             <button type="button" class="removeRoom" value="${room.room_sid}">삭제하기</button>
                             <button type="button" class="restore" value="${room.room_sid}">복구하기</button>

                              `;

                 });
                 $(".deleteRooms").html(html);
             },
             function () {
                 alert("삭제 리스트 조회실패");
             });

         if (buttonText === "휴지통") {
             $(this).text("닫기");
         } else {
             $(this).text("휴지통");
         }
     });
});