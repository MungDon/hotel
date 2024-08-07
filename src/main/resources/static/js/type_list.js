$(function(){
    $(document).on("click",".roomTypeUpdateSetBtn",(event)=>{
        const typeSid = $(event.target).val();
        const thisTr = $(event.target).closest('tr');
        thisTr.find('input').prop('readonly',false);
        const btnBox = thisTr.find(".typeManageBtnBox");
        btnBox.empty();
        const createUpdateBtns = `
             <button type="button" class="roomTypeUpdateBtn" value="${typeSid}">수정</button>
             <button type="button" class="roomTypeUpdateCancelBtn" value="${typeSid}">취소</button>
        `;
        btnBox.append(createUpdateBtns);
    });

    $(document).on("click",".roomTypeUpdateBtn", (event) =>{
        const thisTr = $(event.target).closest('tr');
        const typeSid = $(event.target).val();
        const typeName = thisTr.find('.typeName').val();
        const roomSize = thisTr.find('.roomSize').val();
        const bedSize = thisTr.find('.bedSize').val();
        const roomTypeObj = {
            room_type_sid : typeSid,
            type_name : typeName,
            room_size : roomSize,
            bed_size : bedSize
        };

        const ajaxObj = {
            url : API_LIST.ROOM_TYPE_UPDATE,
            method : "put",
            param : roomTypeObj,
            successFn : (resultResponse) =>{
                if(resultResponse.success){
                    const thenFn = () => {
                        thisTr.find('input').prop("readonly",true);
                    }
                    swalCall("성공",resultResponse.message,"success",thenFn);
                } else{
                    swalCall("수정 실패","수정실패","error");
                    return;
                }
            }
        }
        ajaxCall(ajaxObj);
    });
    $(document).on("click",".roomTypeUpdateCancelBtn", (event) => {
        const typeSid = $(event.target).val();
        const thisTr = $(event.target).closest('tr');
        thisTr.find('input').prop('readonly',true);
        const btnBox = thisTr.find(".typeManageBtnBox");
        const createUpdateBtns = `
             <button type="button" class="roomTypeUpdateSetBtn" value="${typeSid}">수정</button>
             <button type="button" class="roomTypeDeleteBtn" value="${typeSid}">삭제</button>
        `;
        btnBox.append(createUpdateBtns);
    });

    $(document).on("click",".roomTypeDeleteBtn", (event) => {
        const typeSid = $(event.target).val();
        const thenFn = (result) => {
            if(result.isConfirmed){
                const ajaxObj = {
                    url : API_LIST.ROOM_TYPE_DELETE,
                    method : "delete",
                    param : {
                        room_type_sid : typeSid
                    },
                    successFn : (resultResponse) => {
                        if(resultResponse.success){
                            const thenFn = () => {
                                location.reload();
                            }
                            swalCall("성공",resultResponse.message,"success",thenFn);
                        } else {
                            swalCall("삭제 실패","삭제 실패","error");
                            return;
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("객실 유형 삭제", "해당 객실 유형을 삭제하시겠습니까?","question",thenFn);
    });
});