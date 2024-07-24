$(function () {
    //회원명 중복 검사 상태
    let nameValidate = false;

    //회원 중복 검사
    $(".duplicateTestBtn").click((event) => {
        const name = $("#name").val();
        const nameValidText = $(".nameValidText");
        $(event.target).prop("disabled", true);
        const ajaxObj = {
            url: API_LIST.USERNAME_VALIDATE,
            method: "post",
            param: {
                name: name
            },
            successFn: (successText) => {
                nameValidText.text(successText).css("color", "skyblue");
                nameValidate = true;
            },
            errorFn: (errorResponse) => {
                const error = errorResponse.responseJSON;
                nameValidText.text(error.message).css("color", "red");
                $(event.target).prop("disabled", false);
            }
        };
        ajaxCall(ajaxObj);
    });

    // 회원가입 폼 유효성 검사
    $(".joinBtn").click(async () => {
        const joinForm = $("#joinForm");
        const emailValidText = $(".emailValidText");
        const nameValidText = $(".nameValidText");
        const pwValidText = $(".pwValidText");
        const email = $("#email").val();
        const name = $("#name").val();
        const pw = $("#pw").val();
        const pw2 = $("#pw2").val();
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const passwordPattern = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/;

        try {
            await isValidData(isNull(email), emailValidText, "이메일은 필수입니다!");
            await isValidData(isNull(name), nameValidText, "회원명은 필수입니다!");
            await isValidData(isNull(pw), pwValidText, "비밀번호는 필수입니다!");
            await isValidData(pw !== pw2, pwValidText, "비밀번호와 비밀번호재확인이 일치하지 않습니다.");
            await isValidData(!emailPattern.test(email), emailValidText, "이메일 형식에 맞게 작성해주세요.");
            await isValidData(!passwordPattern.test(pw), pwValidText, "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
        } catch (error) {
            return;
        }
        if (nameValidate) {
            joinForm.submit();
        }
    });
    $("#empChk").change((event) =>{
        if($(event.target).is(":checked")){
            $(".empOnlyBox").css("display","block");
        }else{
            $(".empOnlyBox").css("display","none");
        }
    });

});