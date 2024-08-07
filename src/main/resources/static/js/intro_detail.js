$(function (){

    //목록으로
    $(".goIntroList").click(()=> {
        location.href=PAGE_LIST.INTRO_LIST;
    });

    //수정하기
    $(".introUpdate").click((event)=>{
        const hotelSid = $(event.target).val();
        location.href = PAGE_LIST.INTRO_UPDATE + hotelSid;
    });

    const deleteIntroValidate = () => {
        const introCnt = $("#introCnt").val();
        const status = $("#status").val();

        if(introCnt == 1){
            swalCall("경고","최소 한개의 소개글은 존재해야합니다.","warning");
            return false;
        }
        if(status == '공개'){
            swalCall("경고","해당 소개글은 공개 소개글입니다, 변경 후 삭제해주세요.","warning");
            return false;
        }
        return true;
    }

    //삭제하기
    $(".introDelete").click((event)=>{
        const hotelSid = $(event.target).val();
        const isValid =  deleteIntroValidate();
        if(!isValid){
            return;
        }
        const thenFn = (result) => {
            if(result.isConfirmed){
                const ajaxObj = {
                    url : API_LIST.INTRO_DELETE,
                    method : "delete",
                    param : {
                        hotel_sid : hotelSid
                    },
                    successFn : (resultResponse) => {
                        if(resultResponse.success){
                            const thenFn = () => {
                                location.href = PAGE_LIST.INTRO_LIST;
                            }
                            swalCall("성공",resultResponse.message,"success",thenFn);
                        } else {
                            swalCall("삭제 실패","소개글 삭제 실패","error");
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("소개글 삭제","해당 소개글을 삭제 하시겠습니까?","question",thenFn,"예",true);
    });
});