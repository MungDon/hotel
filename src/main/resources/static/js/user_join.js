    //회원명 중복 검사 상태
    let nameValidate = false;
$(function () {
    //경고 텍스트 삽입
    const isValidData = (conditionalStatement, TextElement, warningText) => {
        return new Promise((resolve,reject)=>{
            if (conditionalStatement) {
                TextElement.text(warningText).css("color", "red");
                reject(new Error("is not ValidData"));
            }
            resolve();
        });
    }


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
                $("#name").prop("readonly",true);
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
        console.log(authStatus);
        if(!authStatus){
            swalCall("경고","이메일 인증을 해주세요", "warning");
            return;
        }
        if(!nameValidate){
            swalCall("경고","회원명 중복 검사을 해주세요", "warning");
            return;
        }
        const empInput = $("#empNum").val();
        const empChkBox = $("#empChk");
        const joinForm = $("#joinForm");
        const emailValidText = $(".emailValidText");
        const nameValidText = $(".nameValidText");
        const pwValidText = $(".pwValidText");
        const empValidText = $(".empValidText");
        const email = $("#email").val();
        const name = $("#name").val();
        const pw = $("#pw").val();
        const pw2 = $("#pw2").val();
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        const passwordPattern = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/;
        const empPattern = /^([0-9]{10})$/;

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
        if(empChkBox.is(":checked")&&isNull(empInput)){
            empValidText.text("사원번호를 입력하세요.").css("color","red");
            return;
        }
        if(empChkBox.is(":checked")&&!empPattern.test(empInput)){
            empValidText.text("사원번호 10자를 입력하세요.").css("color","red");
            return;
        }
        if (nameValidate&&authStatus) {
            joinForm.submit();
        }
    });
    $("#empChk").change((event) =>{
        if($(event.target).is(":checked")){
            $(".empOnlyBox").css("display","block");
        }else{
            $(".empOnlyBox").css("display","none");
            $("#empNum").val("");
            $(".empValidText").empty();
        }
    });

});