$(function(){
    $(document).on("click",".roomTypeUpdateSetBtn",(event)=>{
        const typeSid = $(event.target).val();
        const thisTr = $(event.target).closest('tr');
        thisTr.find('input').prop('readonly',false);
        thisTr.find('input').css('outline',"1px solid black");
        const btnBox = thisTr.find(".typeManageBtnBox");
        const line2 = thisTr.find(".line_2");
        line2.append(`
            <input type="file" name="typeImg" class="type_img_input">
        `);
        btnBox.empty();
        const createUpdateBtns = `
             <button type="button" class="roomTypeUpdateBtn" value="${typeSid}">수정</button>
             <button type="button" class="roomTypeUpdateCancelBtn" value="${typeSid}">취소</button>
        `;
        btnBox.append(createUpdateBtns);
    });

    $(document).on("click",".roomTypeUpdateBtn", (event) =>{
        const formData = new FormData();
        const thisTr = $(event.target).closest('tr');
        const typeSid = $(event.target).val();
        const typeName = thisTr.find('.typeName').val();
        const roomSize = thisTr.find('.roomSize').val();
        const bedSize = thisTr.find('.bedSize').val();
        const currentImg = thisTr.find('.currentImg').val();
        const typeImg = thisTr.find(".type_img_input")[0].files[0];
        formData.append('type_name', typeName);
        formData.append('typeSid', typeSid);
        formData.append('room_size', roomSize);
        formData.append('bed_size', bedSize);
        formData.append('current_img', currentImg);
        if(!isNull(typeImg)){
            formData.append('type_img', typeImg);
        }
        console.log(typeImg);
        const ajaxObj = {
            url : API_LIST.ROOM_TYPE_UPDATE,
            method : "put",
            param : formData,
            contentType: false,
            processData : false,
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
        thisTr.find('input').css('outline',"none");
        thisTr.find(".type_img_box").css("display","none");
        thisTr.find(".type_img_input").remove();
        const btnBox = thisTr.find(".typeManageBtnBox");
        btnBox.empty();
        const createUpdateBtns = `
             <button type="button" class="roomTypeUpdateSetBtn" value="${typeSid}">수정</button>
             <button type="button" class="roomTypeDeleteBtn" value="${typeSid}">삭제</button>
        `;
        btnBox.append(createUpdateBtns);
    });

    $(document).on("click",".roomTypeDeleteBtn", (event) => {
        const typeListSize = $("#typeListSize").val();

        if(typeListSize == 1){
            swalCall("경고","객실타입은 1개 이상 등록해야합니다","warning");
            return;
        }
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
        swalCall("객실 유형 삭제", "해당 객실 유형을 삭제하시겠습니까?","question",thenFn,"예",true);
    });
    $(".typeAddBtn").click(() => {
        location.href = PAGE_LIST.ROOM_TYPE_ADD_FORM;
    });
});