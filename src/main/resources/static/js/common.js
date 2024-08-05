// null 체크공동 메서드
const isNullAlert = (chkData, errorText) => {
    if (chkData == null || chkData == "") {
        swalCall("경고", errorText, "warning");
    }
}
const isNull = (chkData) => {
    if (chkData == null || chkData == "") {
        return true;
    }
    return false;
}
// ajaxCall에서 사용할 api 주소를 상수로 관리
const API_LIST = {
    SEND_AUTH_CODE: "/user/send/code",
    USERNAME_VALIDATE: "/user/name/valid",
    AUTH_CODE_VALIDATE: "/user/code/valid",
    ROOM_DETAIL : "/room/detail/",
    PERMANENTLY_DELETE : "/room/remove",
    RESERVE_ROOM_FORM : "/reserve",
    DELETE_ROOM : "/room/delete",
    DELETE_ROOM_LIST : "/room/delete/list",
    CANCEL_RESERVATION : "/reserve/cancel",
    PAYMENT_VALID : "/payment/valid",
    PAYMENT : "/reserve/payment",
    SELECT_INTRO : "/hotel/select/intro"
}
// 단순 페이지 이동 url 상수
const PAGE_LIST = {
    LOGIN_PAGE : "/user/login",
    MAIN_PAGE : "/hotel",
    INTRO_ADD : "/hotel/management/intro/add",
    INTRO_DETAIL : "/hotel/management/intro/detail/"

};

const defaultErrorFn = (errorResponse) => {
    const error = errorResponse.responseJSON;
    swalCall("경고", error.message, "error");
}

const ajaxCall = ({url, method, successFn,contentType = "application/x-www-form-urlencoded; charset=UTF-8", param = null, errorFn = defaultErrorFn}) => {
    $.ajax({
        url: url,
        method: method,
        contentType:contentType ,
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

