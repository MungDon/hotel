$(function () {
    const lastFloorName = $(".floor").last().text();
    let floorCnt = parseInt(lastFloorName.replace('F', ''),10)+1;
    const roomList = initialRoomList;
    if(roomList.length == 0){
        const thenFn = () => {
            location.href = PAGE_LIST.HOTEL_LAYOUT_LIST;
        }
        swalCall("경고", "등록된 객실이 없습니다 객실 먼저 등록해주세요","warning",thenFn);
    }
    const roomSelectOption = roomList.map(room => {
        return `
            <option value="${room.room_sid}">${room.room_name}</option>
        `;
    }).join("");

    /* 호텔 구성 저장 */
    $(".layout_add").click(() => {
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
            url: API_LIST.HOTEL_LAYOUT_ADD,
            method: "post",
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

    /*목록으로*/
    $(".go_layout_list").click(() => {
        location.href = PAGE_LIST.HOTEL_LAYOUT_LIST;
    });

    /*층 추가*/
    $(document).on("click",".floor_add",() => {
        const layoutAddCon = $(".layout_add_con");
        const layoutAddBtnBox = $(".layout_add_btn_box");
        layoutAddBtnBox.find(".floor_delete").prop("disabled", false);
        const floorHTML =
            `
            <div class="floor_box">
                <button type="button" class="room_add">객실 추가</button>
                <button type="button" class="room_delete" disabled>객실 삭제</button>
                <span class="floor">${floorCnt}F</span>
                <div class="room">
                    <select class="room_sid">
                        ${roomSelectOption}
                    </select>
                    <input type="text" class="room_number" placeholder="ex)101호" value="${floorCnt}01호">
                </div>
            </div>
            `;
        layoutAddCon.append(floorHTML);
        floorCnt++;
    });
    /*층 삭제*/
    $(document).on("click",".floor_delete",(event) => {
        const layoutAddCon = $(".layout_add_con");
        const layoutAddBtnBox = $(".layout_add_btn_box");
        const lastFloorBox = layoutAddCon.find(".floor_box").last();
        const floorCount = layoutAddCon.find(".floor_box").length;
        if (floorCount <= 2) {
            layoutAddBtnBox.find(".floor_delete").prop("disabled", true);
        }
        lastFloorBox.remove();
        floorCnt--;
    });

    /*객실 추가*/
    $(document).on("click", ".room_add", (event) => {
        const floorBox = $(event.target).closest(".floor_box");
        const roomBox = floorBox.find("div").filter(function () {
            return $(this).attr("class").startsWith("room");
        }).last();
        const roomCount = floorBox.find(".room").length;
        console.log(roomCount);
        if (roomCount >= 1) {
            floorBox.find(".room_delete").prop("disabled", false);
        }
        if (roomCount >= 9) {
            floorBox.find(".room_add").prop("disabled", true);
        }

        const nextNumber = parseInt(roomBox.find(".room_number").val()) + 1;
        const roomHTML =
            `
            <div class="room">
                <select class="room_sid">
                    ${roomSelectOption}
                </select>
                <input type="text" class="room_number" placeholder="ex)101호" value="${nextNumber}호">
            </div>
            `;
        floorBox.append(roomHTML);
    });

    /*객실 삭제*/
    $(document).on("click", ".room_delete", (event) => {
        const floorBox = $(event.target).closest(".floor_box");
        const lastRoomBox = floorBox.find(".room").last();
        const roomCount = floorBox.find(".room").length;
        console.log(roomCount);
        if (roomCount <= 2) {
            floorBox.find(".room_delete").prop("disabled", true);
        }
        if (roomCount <= 10) {
            floorBox.find(".room_add").prop("disabled", false);
        }
        lastRoomBox.remove();
    });
});