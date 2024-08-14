$(function(){
    $(".question_add_btn").click(() =>{
        const userSid = $("#userSession").val();
        const title = $(".question_title").val();
        const questionTypeSid = $(".question_type").val();
        const content = $(".question_content").val();

        const questionObj = {
            userSid : userSid,
            title : title,
            questionTypeSid : questionTypeSid,
            content : content
        }
        const ajaxObj = {
            url : API_LIST.QUESTION_ADD,
            method : "post",
            contentType : "application/json",
            param : JSON.stringify(questionObj),
            successFn : (resultResponse) =>{
                if(resultResponse.success){
                    const thenFn = () => {
                        location.href= PAGE_LIST.QUESTION_LIST;
                    }
                    swalCall("성공", resultResponse.message,"success",thenFn);
                } else {
                    const thenFn = () => {
                        location.href= PAGE_LIST.QUESTION_LIST;
                    }
                    swalCall("실패" ,"문의 등록에 실패하였습니다","error",thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });
});