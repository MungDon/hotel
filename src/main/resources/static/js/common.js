// null 체크공동 메서드
const isNull = (chkData, errorText) => {
    if (chkData == null || chkData == "") {
        swalCall("경고", errorText, "warning");
    }
}

// ajaxCall에서 사용할 api 주소를 상수로 관리
const API_LIST = {
    SEND_AUTH_CODE: "/user/send/code",
    USERNAME_VALIDATE: "/user/name/valid",
    AUTH_CODE_VALIDATE: "/user/code/valid"
}
// 단순 페이지 이동 url 상수
const PAGE_LIST = {};

const defaultErrorFn = (errorResponse) => {
    const error = errorResponse.responseJSON;
    swalCall("경고", error.message, "error");
}
const ajaxCall = ({url, method, successFn, param = null, errorFn = defaultErrorFn}) => {
    $.ajax({
        url: url,
        method: method,
        data: param,
        success: (data) => {
            if (typeof successFn == "function") {
                successFn(data);
            }
        },
        error: (errorResponse) => {
            if (typeof errorFn == "function") {
                errorFn(errorResponse);
            }
        }
    });
};
const defaultThenFn = () => {
    return;
}
/*스윗 알러트 공통*/
const swalCall = (title, text, icon, thenFn = defaultThenFn, confirmButtonText = "확인", showCancelButton = false, cancelButtonText = "아니요") => {
    Swal.fire({
        title: title,
        html: text,
        icon: icon,
        confirmButtonText: confirmButtonText,
        showCancelButton: showCancelButton,
        cancelButtonText: cancelButtonText
    }).then((result) => {
        if (typeof thenFn == "function") {
            console.log(thenFn);
            thenFn(result);
        }
    });
};

