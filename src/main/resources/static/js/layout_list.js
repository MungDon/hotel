$(function () {

    /*구조 등록하기 폼*/
    $(document).on("click", ".layout_add_form", () => {
        if(initialRoomList.length == 0){
            const thenFn = () => {
                location.href = PAGE_LIST.HOTEL_LAYOUT_LIST;
            }
            swalCall("경고", "등록된 객실이 없습니다 객실 먼저 등록해주세요","warning",thenFn);
        }
        location.href = PAGE_LIST.HOTEL_ADD_FORM;
    });

    /*수정완료*/
    $(document).on("click",".layout_update",() => {
        let layoutData = [];
        $(".floor_box").each((floorIndex, floorElement) => {
            let floorData = {
                floorName: $(floorElement).find("span").text(),
                rooms: []
            }
            $(floorElement).find(".room").each((roomIndex, roomElement) => {
                let roomData = {
                    roomSid: $(roomElement).find(".room_sid").val(),
                    roomNumber: $(roomElement).find(".room_number").val()
                }
                floorData.rooms.push(roomData);
            });
            layoutData.push(floorData);
        });

        const ajaxObj = {
            url: API_LIST.HOTEL_LAYOUT_UPDATE,
            method: "put",
            contentType: "application/json",
            param: JSON.stringify(layoutData),
            successFn: (resultResponse) => {
                if (resultResponse.success) {
                    const thenFn = () => {
                        location.href = PAGE_LIST.HOTEL_LAYOUT_LIST;
                    }
                    swalCall("성공", resultResponse.message, "success", thenFn);
                } else {
                    swalCall("실패", "예기치 못한 에러가 발생하였습니다.", "error");
                }
            }
        }
        ajaxCall(ajaxObj);
    });


    $(document).on("click",".layout_update_set",() => {
        const layoutListBtnBox = $(".layout_add_btn_box");
        const floorBox = $(".floor_box");
        floorBox.find("button").css("display", "block");
        const roomCount = floorBox.find(".room").length;
        if (roomCount >= 1) {
            floorBox.find(".room_delete").prop("disabled", false);
        }
        $(".room").find("select").prop("disabled", false);
        layoutListBtnBox.empty();

        const layoutUpdateBtnBoxHTML =
            `
            <button type="button" class="layout_update">수정</button>
            <button type="button" class="layout_update_cancel">취소</button>
            <button type="button" class="floor_add">층 추가</button>
            <button type="button" class="floor_delete">층 삭제</button>
            <button type="button" class="reload">새로고침</button>
            `;
        layoutListBtnBox.append(layoutUpdateBtnBoxHTML);
    });

    $(document).on("click", ".reload", () => {
        location.reload();
    });

    $(document).on("click", ".layout_update_cancel", () => {
        const layoutListBtnBox = $(".layout_add_btn_box");
        layoutListBtnBox.empty();
        const layoutListIsEmpty = layoutList.length === 0;
        let layoutUpdateCancelBtnBoxHTML = '';
        if (layoutListIsEmpty) {
            layoutUpdateCancelBtnBoxHTML =
                `
                <button type="button" class="layout_add_form">등록</button>
                `
        } else {
            layoutUpdateCancelBtnBoxHTML =
                `
                <button type="button" class="layout_update_set">수정</button>
                <button type="button" class="remove_all">초기화</button>
                `;
        }
        layoutListBtnBox.append(layoutUpdateCancelBtnBoxHTML);
    });

    $(document).on("click",".remove_all",()=>{
        const thenFn = (result) => {
            if(result.isConfirmed){
                const ajaxObj  = {
                    url : API_LIST.HOTEL_LAYOUT_DELETE_ALL,
                    method : "delete",
                    successFn : (resultResponse) => {
                        if(resultResponse.success){
                            const thenFn = () =>{
                                location.reload();
                            }
                            swalCall("성공", resultResponse.message,"success",thenFn);
                        }else{
                            const thenFn = () =>{
                                location.reload();
                            }
                            swalCall("실패", "예기치 못한 에러로 <br> 삭제에 실패하였습니다.","error",thenFn);
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("전체 삭제","모든 구성을 초기화시키겠습니까?","question",thenFn,"예",true);
    });
});