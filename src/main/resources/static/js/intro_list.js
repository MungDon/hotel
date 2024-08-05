$(function () {
    $(document).on("change", ".chk", (event) => {
        const chkBoxValue = $(event.target).val();
        const thenFn = (result) => {
            if (result.isConfirmed) {
                console.log(chkBoxValue);
                const ajaxObj = {
                    url: API_LIST.SELECT_INTRO,
                    method: "post",
                    param: {
                        chkBoxValue: chkBoxValue
                    },
                    successFn: (resultResponse) => {
                        console.log(resultResponse);
                        console.log(resultResponse.isSuccess);
                        if (resultResponse.success) {
                            const thenFn = () => {
                                location.reload();
                            }
                            swalCall("성공", resultResponse.message, "success", thenFn);
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else{
                $(".chk").prop("checked", false);
                location.reload();
                return;
            }
        }
        swalCall("대표글 설정","해당 소개글을 대표글로 설정하시겠습니까?","question",thenFn,"예",true);
    });
    $(document).on("click",".introButton", (event) => {
        const introCount = $(event.target).val();
        if(introCount == 5){
            swalCall("경고","소개글은 최대 5개 까지만 작성 가능합니다.","warning");
            return;
        }
        location.href = PAGE_LIST.INTRO_ADD;
    });

    $(document).on("click",".introListTitle", (event) =>{
        const introSid = $(event.target).data("intro-sid");
        console.log(introSid);
        location.href = PAGE_LIST.INTRO_DETAIL + introSid;
    });
});