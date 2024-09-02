$(function () {
    const innerElement = $(".innerElement"); // 모달 내용박스
    const modal = $("#modalCon");     // 모달창
    const initSlider = () => {
        const sliderInner = $(".slider_inner");
        sliderInner.css('transform', 'translateX(0)');
    }
    initSlider();
    $(document).on("click", ".show_room", (event) => {
        const room_sid = $(event.target).val();
        const reserve_sid = $(event.target).data("reserve-sid");
        const ajaxObj = {
            url: API_LIST.ROOM_DETAIL + room_sid,
            method: "get",
            successFn: (detailData) => {
                createRoomDetailModal(detailData, reserve_sid);
            }
        }
        ajaxCall(ajaxObj);
    });

    // 객실 상세정보
    const createRoomDetailModal = (detailData, reserve_sid) => {
        innerElement.empty();
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
            <button type="button" class="reserve_detail" data-reserve-sid="${reserve_sid}">예약 정보보기</button>
            <button type="button" class="closeBtn">닫기</button>
        </div>
    `;
        innerElement.append(modalContent);
        openModal(modal);
    };
    $(document).on("click", ".moveBtn", (event) => {
        event.preventDefault();
        const sliders = $(".slider");
        const sliderCount = sliders.length;
        const sliderInner = $(".slider_inner");
        const slideWidth = sliders.first().outerWidth();

        let transform = sliderInner.css('transform');
        let currentIndex = 0;

        if (transform !== 'none') {
            const matrix = transform.match(/matrix\(([^)]+)\)/);
            if (matrix) {
                currentIndex = -parseInt(matrix[1].split(',')[4]) / slideWidth;
            }
        }

        if ($(event.target).hasClass("prev")) {
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

    $(document).on("click", ".reserve_cancel", (event) => {
        const reserveCancelObj = $(event.val).val();

        const ajaxObj = {
            url: API_LIST.RESERVE_CANCEL,
            method: "delete",
            contentType: "application/json",
            param: reserveCancelObj,
            successFn: (resultResponse) => {
                if (resultResponse.success) {
                    const thenFn = () =>{
                        location.reload();
                    }
                    swalCall("성공", resultResponse.message,"success",thenFn);
                } else {
                    const thenFn = () =>{
                        location.reload();
                    }
                    swalCall("실패","예약 취소에 실패하였습니다.","error",thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });
});