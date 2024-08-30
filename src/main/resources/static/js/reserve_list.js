$(function(){
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
});