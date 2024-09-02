$(function(){
    const modalInnerElement = $(".innerElement");   // 모달 내용 요소
    const modal = $("#modalCon");                   // 모달 요소
    $(".reservation_type").on("change",(event)=>{
        const reservationType = $(event.target).val();
        if(isNull(reservationType)){
            location.href =removeQueryParam(location.href,"reservationType");
            return;
        }
        location.href = updateQueryParam(location.href,"reservationType",reservationType);
    });

    const removeQueryParam =(url, paramName) => {
        // URL 객체 생성
        let urlObj = new URL(url);

        // URLSearchParams 객체를 가져옴
        let params = urlObj.searchParams;

        // 특정 파라미터 삭제
        params.delete(paramName);

        // 갱신된 URL 반환
        return urlObj.toString();
    }
    const updateQueryParam = (url, paramName, paramValue) => {
        // URL 객체 생성
        let urlObj = new URL(url);

        // URLSearchParams 객체를 가져옴
        let params = urlObj.searchParams;

        // 기존 파라미터가 있는 경우 제거 후 추가
        params.set(paramName, paramValue);

        // 갱신된 URL 반환
        return urlObj.toString();
    }
    $(document).on("click",".reserve_detail",(event)=>{
        const reserveSid = $(event.target).data("reserve-sid");

        const ajaxObj = {
            url : API_LIST.RESERVE_DETAIL,
            method : "get",
            param : {
                reserveSid : reserveSid
            },
            successFn : (reserveDetail) => {
                if(isNull(reserveDetail)){
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("에러","상세보기 데이터  가져오기 실패", "error",thenFn);
                }
                createReserveDetail(reserveDetail);
            }
        }
        ajaxCall(ajaxObj);
    });

    const createReserveDetail = (reserveDetail) => {
        modalInnerElement.empty()
        const reserveCancelObj = {
            reserveSid : reserveDetail.reserve_sid,
            reserveNumber : reserveDetail.reserve_number
        }
        const reserveCancelObjJSON = JSON.stringify(reserveCancelObj);
        const reserveDetailHTML =
            `
            <section class="reserve_detail_box">
                <article class="reserve_detail_top">
                    <span>예약정보</span>
                </article>
                <article class="reserve_detail_content">
                    <span>예약 객실</span>
                    <div class="reserve_detail_room_name">${reserveDetail.room_name}</div>
                    <button type="button" class="show_room" data-reserve-sid="${reserveDetail.reserve_sid}" value="${reserveDetail.room_sid}">객실 정보 보러가기</button>
                    <br/>
                    <br/>
                    <span>예약자 정보</span>
                    <div class="reserve_detail_user_name"><b>예약자 명 |</b>  ${reserveDetail.user_name}</div>
                    <div class="reserve_detail_user_email"><b>예약자 이메일 |</b> ${reserveDetail.user_email}</div>
                    <div class="reserve_detail_reserve_number"><b>예약 번호 |</b> ${reserveDetail.reserve_number}</div>
                    <br/>
                    <span>예약 인원</span>
                    <div class="reserve_detail_adult_cnt"><b>성인 |</b> ${reserveDetail.adult_cnt}</div>
                    <div class="reserve_detail_child_cnt"><b>소아 |</b> ${reserveDetail.child_cnt}</div>
                    <br/>
                    <span>예약 일정</span>
                    <div class="reserve_detail_check_in"><b>체크인 |</b> ${reserveDetail.start_date}</div>
                    <div class="reserve_detail_check_out"><b>체크아웃 |</b> ${reserveDetail.end_date}</div>
                </article>
            </section>
            <section class="reserve_detail_btn_box">
                <button type="button" class="reserve_cancel" value="${reserveCancelObjJSON}">예약 취소</button>
                <button type="button" class="closeBtn">닫기</button>
            </section>
            `;
        modalInnerElement.append(reserveDetailHTML);
        openModal(modal);
    }
});