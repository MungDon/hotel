$(function(){
    $(".duplicateTestBTn").click(()=>{
        const name = $("#name").val();
        const ajaxObj = {
            url : API_LIST.USERNAME_VALIDATE,
            method : "post",
            param : {
                name : name
            },
            successFn : (successText)=>{
                $(".nameValidText").text(successText);
            },
            errorFn : (errorResponse) =>{
                const error = errorResponse.responseJSON;
                $(".nameValidText").text(error.message);
            }
        };
        ajaxCall(ajaxObj);
    });
});