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
            <button type="button" class="deleteRoom" value="${detailData.room_sid}">삭제하기</button>
            <button type="button" class="updateRoom" value="${detailData.room_sid}">수정하기</button>
            <button type="button" class="closeBtn">닫기</button>
        </div>
    `;
        innerElement.append(modalContent);
        openModal(modal);
    };
});