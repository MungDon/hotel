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
    $(".room_name").click((event)=>{
        const reserveSid = $(event.target).data("reserve-sid");

        const ajaxObj = {
            url : API_LIST.RESERVE_DETAIL_USER,
            method : "get",
            param : {
                reserveSid : reserveSid
            },
            successFn : (reserveDetail) => {
                if(!isNull(reserveDetail)){
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
        modalInnerElement.empty();
        const reserveDetailHTML =
            `
            
            `;
        modalInnerElement.append(reserveDetailHTML);
        openModal(modal);
    }
});