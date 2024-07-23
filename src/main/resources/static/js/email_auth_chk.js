$(function () {
    
    let authStatus = false;  // 인증상태
    const modal = $("#modalCon");     // 모달창
    const innerElement = $(".innerElement"); // 모달 내용박스

    /*모달 안 내용생성*/
    const createModal = () => {
        innerElement.empty();
        innerElement.append("<input type='number' id='authCode' placeholder='인증코드를 입력해주세요'>");
        innerElement.append("<button type='button' class='authCodeChk'>인증완료</button>");
        innerElement.append("<button type='button' class='resendBtn'>재전송</button>")
        openModal(modal);
    }
    
    /*이메일 인증 버튼 클릭 시 */
    $(".emailAuthBtn").click(() => {
        const email = $("#email").val();
        const action = $("#action").val();
        $("#email").prop("readonly",true);
        $(".emailAuthBtn").prop("disabled",true);
        sendAuthCode(email, action);
    });
    /*이메일 인증 재전송 버튼 클릭 시 */
    $(document).on("click",".resendBtn",(event)=>{
        const email = $("#email").val();
        const action = $("#action").val();
        $(event.target).prop("disabled",true);
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

    /*모달끄기버튼*/
    $(document).on("click",".cancelBtn", ()=>{
        innerElement.empty();
        $("#email").prop("readonly",false);
        $(".emailAuthBtn").prop("disabled",false);
        closeModal(modal);
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
                    $(".resendBtn").prop("disabled",false);
                }
                swalCall("성공", response, "success", thenFn);
            }
        };
        ajaxCall(ajaxObj);
    }



});