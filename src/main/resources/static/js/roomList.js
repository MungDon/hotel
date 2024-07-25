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
            startDate: startDate,
            endDate: endDate,
            adultCnt: adultCnt,
            childCnt: childCnt
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
        innerElement.append(
            `<div class='roomName'>
					<span>${detailData.room_name}</span>
				</div>`
        );
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
            ${user_sid != null && role_user == 'STAFF' ?
            `<button type="button" class="removeRoom" value="${detailData.room_sid}">삭제하기</button>
                <button type="button" class="updateRoom" value="${detailData.room_sid}">수정하기</button>` :
            ''}
            <button type="button" class="closeBtn">닫기</button>
            <button type="button" class="reserveBtn" value="${detailData.room_sid}">예약하기</button>
        </div>
    `;
        innerElement.append(modalContent);
        openModal(modal);
    };

    $(document).on("click", ".moveBtn", (event)=> {
        console.log("이미지 슬라이드");
        block_btn();
        const sliders = $(".slider");
        const sliderCount = sliders.length;
        const slideWidth = sliders.first().outerWidth();
        const sliderInner = $(".slider_inner");
        const currentTransform = sliderInner.css('transform');
        const currentIndex = currentTransform === 'none' ? 0 : Math.abs(parseInt(currentTransform.split(',')[4]) / slideWidth);

        let newIndex = 0;
        if ($(event.target).hasClass("prev")) {
            newIndex = (currentIndex - 1 + sliderCount) % sliderCount;
        } else {
            newIndex = (currentIndex + 1) % sliderCount;
        }

        gotoSlider(newIndex);
    });

    const gotoSlider = (index) => {
        const sliders = $(".slider");
        const sliderInner = $(".slider_inner");
        const slideWidth = sliders.first().outerWidth();
        const newTransform = `translateX(-${index * slideWidth}px)`;
        sliderInner.css('transform', newTransform);
    };

    const block_btn = () => {
        $('.slider_btn button').css({pointerEvents: 'none'});
        setTimeout(function () {
            $('.slider_btn button').css({pointerEvents: 'auto'});
        }, 800);
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
         reserveObj.user_sid = user_sid.val(); // 값을 할당
         reserveObj.room_sid = $(event.target).val();
         const ajaxObj = {
             url: API_LIST.RESERVE_ROOM_FORM,
             method: "get",
             param: reserveObj,
             successFn: () => {
                 createReserveForm(reserveObj);
             }
         };
         ajaxCall(ajaxObj); // AJAX 요청 호출 추가
     });
     const createReserveForm = (reserveObj) => {
         const personCntHTML =
             `<div className="personCount">
                 ${reserveObj.adultCnt != null ? `<span>${reserveObj.adultCnt} 성인</span>` : ''}
                 ${reserveObj.childCnt != null ? `<span>${reserveObj.childCnt} 소아</span>` : ''}
             </div>`;
         innerElement.append(
             `<div class="reserveInfo">
                 <div class="reserveDate">
                     <span>${reserveObj.start_date}</span>
                     <span>${reserveObj.end_date}</span>
                 </div>
                 ${personCntHTML}
             </div>`
         )
     }
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