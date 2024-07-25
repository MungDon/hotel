$(function () {
    const innerElement = $(".innerElement"); // 모달 내용박스
    const modal = $("#modalCon");     // 모달창
    const user_sid = $("#user_sid");
    const role_user = $("#role_user");
    $(".roomDetail").click((event) => { // 클래스가 roomDetail인것을 클릭하면     //필요한 데이터
        const room_sid = $(event.target).data('room-sid'); // 해당 요소의 data 를가져옴
        const startDate = $(".start_date").val();
        const endDate = $(".end_date").val();
        const adultCnt = $(".adult_cnt").val();
        const childCnt = $(".child_cnt").val();
        const reserveObj = {
            startDate: startDate,
            endDate: endDate,
            adultCnt: adultCnt,
            childCnt: childCnt
        }
        const ajaxObj = {
            url: API_LIST.ROOM_DETAIL + room_sid,
            method: "get",
            successFn: (detailData) => {
                createRoomDetailModal(detailData, reserveObj);
            }
        }
        ajaxCall(ajaxObj);
    });

    const createRoomDetailModal = (detailData, reserveData) => {
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
                    <a href="#" class="prev" role="button" aria-label="왼쪽 이미지">prev</a>
                    <a href="#" class="next" role="button" aria-label="오른쪽 이미지">next</a>
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
            <button type="button" class="closeBtn" onclick="backList()">닫기</button>
            <button type="button" class="reserveBtn" value="${user_sid}">예약하기</button>
        </div>
    `;
        innerElement.append(modalContent);
        openModal(modal);
    }

    // 이벤트 위임으로 동적 요소에 이벤트 핸들러 적용
    $(document).on("click", ".slider_btn a", function(event) {
        event.preventDefault();
        const sliders = $(".slider");
        const sliderCount = sliders.length;
        const slideWidth = sliders.first().outerWidth();
        let currentIndex = Math.abs(parseInt($(".slider_inner").css('transform').split(',')[4]) / slideWidth);


        if ($(this).hasClass("prev")) {
            currentIndex = (currentIndex - 1 + sliderCount) % sliderCount;
        } else {
            currentIndex = (currentIndex + 1) % sliderCount;
        }

        gotoSlider(currentIndex);
    });

    const gotoSlider = (index) => {
        const sliders = $(".slider");
        const sliderInner = $(".slider_inner");
        const slideWidth = sliders.first().outerWidth();
        const newTransform = `translateX(-${index * slideWidth}px)`;
        sliderInner.css('transform', newTransform);
    };

// 휴지통에 있는 객실 복구
    $(document).on("click", ".slider_btn a", function(event) {
        event.preventDefault();
        const sliders = $(".slider");
        const sliderCount = sliders.length;
        const slideWidth = sliders.first().outerWidth();
        const sliderInner = $(".slider_inner");
        const currentTransform = sliderInner.css('transform');
        const currentIndex = currentTransform === 'none' ? 0 : Math.abs(parseInt(currentTransform.split(',')[4]) / slideWidth);

        let newIndex = 0;
        if ($(this).hasClass("prev")) {
            newIndex = (currentIndex - 1 + sliderCount) % sliderCount;
        } else {
            newIndex = (currentIndex + 1) % sliderCount;
        }

        gotoSlider(newIndex);
    });

// 휴지통에 있는 객실 영구 삭제
    $(document).on("click", ".removeRoom", function () {
        let room_sid = $(this).val();
        if (!confirm("방을 영구 삭제하시겠습니까?")) {
            return false;
        }
        ajaxCall("/room/remove", "DELETE", {room_sid}, function () {
            alert("방이 영구 삭제되었습니다.");
            location.href = "/room";
        }, function () {
            alert("방 영구 삭제 실패 운영자에게 문의해주세요");
        })
    });

// 예약하기 버튼
    $(document).on("click", ".reserveBtn", function () {
        const options = 'width=700, height=600, top=50, left=50, scrollbars=no';
        window.open('', '_self', options);
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