$(function () {
    
    let authStatus = false;  // 인증상태
    const modal = $("#modalCon");     // 모달창

    /* 모달창유무에 따른 액션*/
    if(modal.css('display') === 'block'){
        sendCodeAction();
    }
    if(modal.css('display') === 'none' && !authStatus){
        cancelAction();
    }
    
    /*모달 안 내용생성*/
    const createModal = () => {
        const innerElement = $(".innerElement");
        innerElement.append("<input type='number' id='authCode' placeholder='인증코드를 입력해주세요'>");
        innerElement.append("<button type='button' class='authCodeChk'>인증완료</button>");
        innerElement.append("<button type='button' class='resendBtn'>재전송</button>")
        console.log(modal)
        openModal(modal);
    }
    
    /*이메일 인증 버튼 클릭 시 */
    $(".emailAuthBtn").click(() => {
        const email = $("#email").val();
        const action = $("#action").val();
        sendAuthCode(email, action);
    });
    /*이메일 인증 재전송 버튼 클릭 시 */
    $(document).on("click",".resendBtn",()=>{
        const email = $("#email").val();
        const action = $("#action").val();
        sendAuthCode(email, action);
    });

    /*인증완료 버튼 클릭 시*/
    $(document).on("click", ".authCodeChk", () => {
        const email = $("#email").val();
        const authCode = $("#authCode").val();
        isNull(authCode, "인증 코드를 입력하세요.");
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
                    authStatus =true;
                }
                swalCall("성공", "인증되었습니다.", "success", thenFn);
            }
        }
        ajaxCall(ajaxObj);
    });
    
    /*인증 번호 전송*/
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
                const thenFn = () => {
                    createModal();
                }
                swalCall("성공", response, "success", thenFn);
            }
        };
        ajaxCall(ajaxObj);
    }

    const sendCodeAction = () =>{
        $(".emailAuthBtn").prop("disabled",true);
        $("#email").prop("readonly" ,true);
    }
    const cancelAction = () =>{
        $(".emailAuthBtn").prop("disabled",false);
        $("#email").prop("readonly" ,false);
    }


});