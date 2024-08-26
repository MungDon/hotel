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
    LOGOUT : "/user/logout",
    SEND_AUTH_CODE: "/user/send/code",
    USERNAME_VALIDATE: "/user/name/valid",
    AUTH_CODE_VALIDATE: "/user/code/valid",
    ROOM_DETAIL : "/room/detail/",
    PERMANENTLY_DELETE : "/room/remove",
    RESERVE_ROOM_FORM : "/reserve",
    DELETE_ROOM : "/room/delete",
    DELETE_ROOM_LIST : "/room/delete/list",
    DELETE_ROOM_IMG  : "/room/img/delete",
    CANCEL_RESERVATION : "/reserve/cancel",
    PAYMENT_VALID : "/payment/valid",
    PAYMENT : "/reserve/payment",
    SELECT_INTRO : "/hotel/select/intro",
    INTRO_ADD_CANCEL : "/hotel/management/intro/add/cancel",
    INTRO_DELETE : "/hotel/management/intro/delete",
    DELETE_IMG : "/hotel/delete/img",
    ROOM_TYPE_ADD : "/type/add",
    ROOM_TYPE_UPDATE : "/type/update",
    ROOM_TYPE_DELETE : "/type/delete",
    FIND_ROOM_TYPE_DATA: "/type/find/detail",
    BANNER_ADD : "/banner/add",
    BANNER_UPDATE : "/banner/update",
    DELETE_BANNER : "/banner/delete",
    QUESTION_TYPE_ADD :"/question/type/add",
    QUESTION_TYPE_UPDATE : "/question/type/update",
    QUESTION_TYPE_DELETE : "/question/type/delete",
    QUESTION_ADD : "/question/add",
    QUESTION_UPDATE : "/question/update",
    QUESTION_DELETE : "/question/delete",
    ANSWER_ADD : "/answer/add",
    ANSWER_UPDATE : "/answer/update",
    ANSWER_DELETE : "/answer/delete",
    EMP_SIGNUP_DECIDE : "/customer/manage/emp/decide",
    CUSTOMER_UPDATE_STATUS : "/customer/manage/update/status"
}
// 단순 페이지 이동 url 상수
const PAGE_LIST = {
    LOGIN_PAGE : "/user/login",
    MAIN_PAGE : "/hotel",
    INTRO_ADD : "/hotel/management/intro/add",
    INTRO_DETAIL : "/hotel/management/intro/detail/",
    INTRO_LIST : "/hotel/management/intro",
    INTRO_UPDATE : "/hotel/management/intro/update/",
    ROOM_TYPE_LIST : "/type",
    ROOM_TYPE_ADD_FORM : "/type/add",
    BANNER_LIST : "/banner",
    BANNER_ADD_FORM : "/banner/add",
    QUESTION_TYPE_LIST : "/question/type",
    QUESTION_TYPE_ADD_FORM : "/question/type/add",
    QUESTION_ADD_FORM : "/question/add",
    QUESTION_LIST : "/question",
    ROOM_ADD_FORM : "/room/add",
    ROOM_UPDATE_FORM : "/room/update/",
    ROOM_MANAGE_LIST : "/room/manage",
    HOTEL_LAYOUT_LIST : "/hotel/layout"
};

const defaultErrorFn = (errorResponse) => {
    const error = errorResponse.responseJSON;
    swalCall("경고", error.message, "error");
}

const ajaxCall = ({url, method, successFn,contentType = "application/x-www-form-urlencoded; charset=UTF-8",processData=true, param = null, errorFn = defaultErrorFn}) => {
    $.ajax({
        url: url,
        method: method,
        contentType:contentType ,
        processData: processData,
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
$(function(){
    $(".logout").click(() => {
        const ajaxObj = {
            url : API_LIST.LOGOUT,
            method : "delete",
            successFn : () => {
                const thenFn = () => {
                    location.href=PAGE_LIST.MAIN_PAGE;
                }
                swalCall("로그아웃","로그아웃되었습니다.","success",thenFn);
            }
        }
        ajaxCall(ajaxObj);
    });
})
