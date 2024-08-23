$(function(){
    const innerElement = $(".innerElement"); // 모달 내용박스
    const modal = $("#modalCon");     // 모달창
    const initSlider = () => {
        const sliderInner = $(".slider_inner");
        sliderInner.css('transform', 'translateX(0)');
    }
    initSlider();
    $(".room_add_btn").click(() => {
        location.href= PAGE_LIST.ROOM_ADD_FORM;
    });

    $(".room_manage_btn").click((event)=>{
        const room_sid = $(event.target).val();

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
        const imagesHTML = detailData.images.map(img =>
            `<div class="slider" role="group" aria-label="5/5">
            <img src="/img/${img.img_name}">
            </div>`
        ).join("");

        const optionsHTML = detailData.options.map(option => {
                return `<div class="roomInfo">
                        <span><b class="optionName">${option.option_name}</b></span> 
                        <span class="optionValue">${option.option_value}</span>
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
        <h2>객실 상세 정보</h2>
        <div class="roomDetailInfo">
            ${optionsHTML}
            <span><b>객실 크기</b> ${detailData.room_size}</span>
            <span><b>침대 크기</b> ${detailData.bed_size}</span>
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
            <button type="button" class="deleteRoom" value="${detailData.room_sid}">삭제하기</button>
            <button type="button" class="updateRoom" value="${detailData.room_sid}">수정하기</button>
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
});