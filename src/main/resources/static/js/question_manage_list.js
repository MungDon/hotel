$(function(){
    $(".question_type").change((event) => {
        const questionType = $(event.target).val();
        if(isNull(questionType)){
            location.href = removeQueryParam(location.href, "questionType");
            return;
        }
        location.href = updateQueryParam(location.href, "questionType",questionType);
    });
    $(".question_status").change((event) => {
        const questionStatus = $(event.target).val();
        if(isNull(questionStatus)){
            location.href = removeQueryParam(location.href, "questionStatus");
            return;
        }
        location.href = updateQueryParam(location.href, "questionStatus",questionStatus);
    });
    $(".answer_btn").click(()=>{

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