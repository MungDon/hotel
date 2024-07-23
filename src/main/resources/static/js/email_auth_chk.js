$(function () {
    $(".emailAuthBtn").click(() => {
        const email = $("#email").val();
        const action = $("#action").val();
        sendAuthCode(email, action);
    });

    const sendAuthCode = (email, action) => {
        isNull(email, "이메일을 입력해주세요");
        const ajaxObj = {
            url: API_LIST.SEND_AUTH_CODE,
            method: "post",
            param: {
                email: email,
                action: action
            },
            successFn: (response) => {
                swalCall("성공",response, "success");
            }
        };
        ajaxCall(ajaxObj);
    }

    $(".authCodeChk").click(() => {
        const email = $("#email").val();
        const authCode = $("#authCode").val();
        if (isNull(authCode)) {
            swalCall("경고", "인증번호를 입력하세요", "warning");
            return;
        }
        const ajaxObj = {
            url: API_LIST.AUTH_CODE_VALIDATE,
            method: "post",
            param: {
                email: email,
                authCode: authCode
            },
            successFn: () => {
                swalCall("성공", "인증되었습니다.", "success");
            }
        }
        ajaxCall(ajaxObj);
    });
});