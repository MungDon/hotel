$(function () {

    let authStatus = false;  // 인증상태
    const modal = $("#modalCon");     // 모달창
    const innerElement = $(".innerElement"); // 모달 내용박스
    const emailValidText = $(".emailValidText"); // 이메일 경고텍스트 박스

    /*모달 안 내용생성*/
    const createModal = () => {
        innerElement.empty();
        innerElement.append("<input type='text' id='authCode' placeholder='인증코드를 입력해주세요'>");
        innerElement.append(
            "<div class='authBtnBox'>" +
            "<button type='button' class='authCodeChk'>인증완료</button>" +
            "<button type='button' class='resendBtn'>재전송</button>" +
            "</div>"
        );
        openModal(modal);
    }
    const emailValid = (email) => {
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if(!emailPattern.test(email)){
            emailValidText.text("이메일을 형식에 맞게 작성하세요.").css("color", "red");
            return false;
        }
        return  true;
    }
    /*이메일 인증 버튼 클릭 시 */
    $(".emailAuthBtn").click(() => {
        const email = $("#email").val();
        const action = $("#action").val();
        console.log(emailValid(email));
        if(!emailValid(email)){
            return;
        }
        emailValidText.empty();
        $("#email").prop("readonly", true);
        $(".emailAuthBtn").prop("disabled", true);
        sendAuthCode(email, action);
    });
    /*이메일 인증 재전송 버튼 클릭 시 */
    $(document).on("click", ".resendBtn", (event) => {
        const email = $("#email").val();
        const action = $("#action").val();
        emailValid(email);
        $(event.target).prop("disabled", true);
        sendAuthCode(email, action);
    });

    /*인증완료 버튼 클릭 시*/
    $(document).on("click", ".authCodeChk", () => {
        const email = $("#email").val();
        const authCode = $("#authCode").val();
        isNullAlert(authCode, "인증 코드를 입력하세요.");
        const ajaxObj = {
            url: API_LIST.AUTH_CODE_VALIDATE,
            method: "post",
            param: {
                email: email,
                authCode: authCode
            },
            successFn: () => {
                const thenFn = () => {
                    closeModal(modal);
                    authStatus = true;
                    $("#authStatus").val("인증됨");
                    emailValidText.text("이메일 인증 완료!").css("color", "skyblue");
                }
                swalCall("성공", "인증되었습니다.", "success", thenFn);
            }
        }
        ajaxCall(ajaxObj);
    });

    /*모달끄기버튼*/
    $(document).on("click", ".cancelBtn", () => {
        innerElement.empty();
        $("#email").prop("readonly", false);
        $(".emailAuthBtn").prop("disabled", false);
        closeModal(modal);
    });
    /*인증 번호 전송*/
    const sendAuthCode = (email, action) => {
        isNullAlert(email, "이메일을 입력해주세요");
        const ajaxObj = {
            url: API_LIST.SEND_AUTH_CODE,
            method: "post",
            param: {
                email: email,
                action: action
            },
            successFn: (response) => {
                const thenFn = () => {
                    createModal();
                    $(".resendBtn").prop("disabled", false);
                }
                swalCall("성공", response, "success", thenFn);
            },
            errorFn: (errorResponse) => {
                const error = errorResponse.responseJSON;
                const thenFn = () =>{
                    $("#email").prop("readonly", false);
                    $(".emailAuthBtn").prop("disabled", false);
                }
                swalCall("경고", error.message, "error",thenFn);
            }
        };
        ajaxCall(ajaxObj);
    }


});