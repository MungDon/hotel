$(function () {
    const innerElement = $(".innerElement"); // 모달 내용박스
    const modal = $("#modalCon");     // 모달창
    const user_sid = $("#user_sid");
    const role_user = $("#role_user");

    let reserveObj = {};

    // 객실 상세보기
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
            child_cnt: childCnt,
            room_sid: room_sid,
            user_sid: user_sid.val()
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

    // 객실 상세정보
    const createRoomDetailModal = (detailData) => {
        const imagesHTML = detailData.images
            .filter(img => img.img_type !== 'thumbnail')
            .map(img =>
                `<div class="slider" role="group" aria-label="5/5">
            <img src="/img/${img.img_name}">
            </div>`
            ).join("");

        const infoOptionsHTML = detailData.infoOptions.map(option => {
            return `<div class="roomInfo">
                        <span><b class="optionName">${option.option_name}</b></span> 
                        <span class="optionValue">${option.option_value}</span>
                    </div>`;
        }).join("");
        const useOptionsHTML = detailData.useOptions.map(option => {
            const formattedOptionValue = option.option_value.replace(/\n/g, '<br>');
            return `<div class="roomInfo">
                        <span><b class="optionName">${option.option_name}</b></span><br> 
                        <span class="optionValue">${formattedOptionValue}</span>
                    </div>`;
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
                    <button type="button" class="moveBtn prev">prev</button>
                    <button type="button" class="moveBtn next">next</button>
                </div>
            </div>
        </section>
        <h2>객실 설명</h2>
        <div class="roomDetailIntro">
            <h4>${detailData.room_info}</h4>
        </div>
        <h2>객실 상세 정보</h2>
        <div class="roomDetailInfo">
            <span><b>객실 크기</b> ${detailData.room_size}㎡</span>
            <span><b>침대 크기</b> ${detailData.bed_size}</span>
            ${infoOptionsHTML}
            <br>
            ${useOptionsHTML}
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
            <button type="button" class="closeBtn">닫기</button>
            <button type="button" class="reserveBtn" value="${detailData.room_sid}">예약하기</button>
        </div>
    `;
        innerElement.append(modalContent);
        openModal(modal);
    };

    //객실삭제(물리) - 휴지통 연결
    $(document).on("click", ".removeRoom", (event) => {
        let room_sid = $(event.target).val();
        const thenFn = (result) => {
            if (result.isConfirmed) {
                const ajaxObj = {
                    url: API_LIST.PERMANENTLY_DELETE,
                    method: "delete",
                    param: {
                        room_sid: room_sid
                    },
                    successFn: () => {
                        swalCall("성공", "객실이 영구 삭제되었습니다.", "success");
                    }
                }
                ajaxCall(ajaxObj);
            }
        };
        swalCall("경고", "지금 삭제하면 영구 삭제됩니다<br>삭제하시겠습니까?", "warning", thenFn, "예", true);
    });

    //예약버튼
    $(document).on("click", ".reserveBtn", (event) => {
        if (isNull(user_sid.val())) {
            const thenFn = () => {
                location.href = PAGE_LIST.LOGIN_PAGE
            }
            swalCall("경고", "로그인이 필요한 서비스입니다", "warning", thenFn);
            return;
        }


        const ajaxObj = {
            url: API_LIST.RESERVE_ROOM_FORM,
            method: "post",
            param: reserveObj,
            successFn: () => {
                $(".detailBtns").remove();
                createReserveForm(reserveObj);
            }
        };
        ajaxCall(ajaxObj); // AJAX 요청 호출 추가
    });

    // 예약정보 보여주기
    const createReserveForm = (reserveObj) => {
        const personCntHTML =
            `<div class ="personCount">
                 <span class="personText">예약 인원</span>
                 ${reserveObj.adult_cnt != null ? `<span>${reserveObj.adult_cnt} 성인</span>` : ''}
                 ${reserveObj.child_cnt != null ? `<span>${reserveObj.child_cnt} 소아</span>` : ''}
             </div>`;
        innerElement.append(
            `<div class="reserveInfo">
                <h2>예약 정보</h2>
                 <div class="stayPeriodBox">
                 <span class="stayPeriod">숙박 기간</span>
                    <div class="reserveDate">
                     <span>${reserveObj.start_date} ~ </span>
                     <span>${reserveObj.end_date}</span>
                    </div>
                 </div>
                 ${personCntHTML}
                 <div class="buyerName">
                    <span>예약자 명</span>
                    <input type="text" id="buyerName">
                 </div>
                 <div class="phoneNumberBox">
                    <span>핸드폰</span>
                    <span>010</span>
                    <input type="text" id="firstNum">
                    <input type="text" id="secondNum">
                 </div>
                 <span class="askText">위 예약정보로 진행하시겠습니까?</span>
                 <div class="reserveBtnBox">
                    <button type="button" class="cancelReserveBtn" value="${reserveObj.user_sid}">취소</button>
                    <button type="button" class="reserveCompleteBtn">신용카드 결제</button>
                 </div>
             </div>`
        )
    }

    // 주문번호 생성
    function createReserveNum() {
        const date = new Date();
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");

        let orderNum = year + month + day;
        for (let i = 0; i < 5; i++) {
            orderNum += Math.floor(Math.random() * 8);
        }
        orderNum += user_sid.val();
        return parseInt(orderNum);
    }

    //예약 진행
    $(document).on("click", ".reserveCompleteBtn", () => {
        const reserveNumber = createReserveNum();
        const roomName = $(".roomName").text().trim();
        const priceText = $(".price span").text();
        const price = priceText.replace("원","").trim();
        const phone = "010-" + $("#firstNum").val() + "-" + $("#secondNum").val();
        const buyerName = $("#buyerName").val();
        console.log(reserveNumber);
        let paymentObj = {
            reserveNumber: reserveNumber,
            roomName: roomName,
            price: price,
            phone: phone,
            user_sid: user_sid.val()
        }

        IMP.init("imp41837584");
        IMP.request_pay({
                pg: "html5_inicis.INIpayTest",
                pay_method: "card",
                merchant_uid: reserveNumber,
                name: roomName,
                amount: price,
                buyer_name: buyerName,
                buyer_email: "",
                buyer_tel: phone,
                buyer_addr: "서울특별시 강남구 신사동",
                buyer_postcode: "01181"
            }, function(response) {
                if (response.success) {
                    console.log("결제 성공 후 검사 진행");

                    const imp_uid = response.imp_uid;
                    const ajaxObj = {
                        url: API_LIST.PAYMENT_VALID,
                        method: "post",
                        param: {
                            imp_uid: imp_uid
                        },
                        successFn: (impData) => {
                            if (price == impData.response.amount) {
                                console.log("결제 검사 후 결제내역 저장 진행");

                                const ajaxObj = {
                                    url: API_LIST.PAYMENT,
                                    method: "post",
                                    param: paymentObj,
                                    successFn: (resultResponse) => {
                                        if(resultResponse.isSuccess){
                                            const thenFn = () => {
                                                location.href = PAGE_LIST.MAIN_PAGE;
                                            }
                                            swalCall("결제 성공", resultResponse.message, "success", thenFn);
                                        }
                                    }
                                }
                                ajaxCall(ajaxObj);
                            } else {
                                console.log("결제 실패 진행");
                                swalCall("결제 실패", "결제가 실패되었습니다.", "error");
                                return;
                            }
                        }
                    }
                    ajaxCall(ajaxObj);
                }
            }
        );
    });
    // 예약 중 취소
    $(document).on("click", ".cancelReserveBtn", () => {
        console.log(user_sid.val());
        const ajaxObj = {
            url: API_LIST.CANCEL_RESERVATION,
            method: "delete",
            param: {
                user_sid: user_sid.val()
            },
            successFn: (response) => {
                console.log(response.message);
                $(".reserveInfo").remove();
                innerElement.append(
                    `<div class="detailBtns">
                        <button type="button" class="closeBtn">닫기</button>
                        <button type="button" class="reserveBtn" value="${reserveObj.room_sid}">예약하기</button>
                    </div>`
                )
            }
        }
        ajaxCall(ajaxObj);
    });

    // 휴지통
    $(document).on("click", ".deleteList", function () {
        $(".deleteRooms").toggle();
        const buttonText = $(this).text();
        const ajaxObj = {
            url: API_LIST.DELETE_ROOM_LIST,
            method: "get",
            successFn: (deleteList) => {
                createDeleteRoomList(deleteList);
            }
        }
        ajaxCall(ajaxObj);
        if (buttonText === "휴지통") {
            $(this).text("닫기");
        } else {
            $(this).text("휴지통");
        }
    });

    const createDeleteRoomList = (deleteList) => {
        let html = "";
        deleteList.forEach(function (room) {
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
    }
});