$(function(){
    $(".emailAuthBtn").click(()=>{
        const email = $("#email").val();
        const action =$("#action").val();
        sendAuthCode(email);
    });

    const sendAuthCode = (email)=>{
        isNull(email,"이메일을 입력해주세요");
        const ajaxObj = {
            url : API_LIST.SEND_AUTH_CODE,
            method : "post",
            param : {
                email : email
            },
            successFn : () =>{
                swalCall("성공","해당 이메일로 인증코드가 전송되었습니다","success");
            }
        };
        ajaxCall(ajaxObj);
    }
});