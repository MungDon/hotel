$(function(){
    let floorCnt = 2;   // 층 카운트
    const roomList = initialRoomList;
    const roomSelectOption =  roomList.map(room => {
        return `
            <option value="${room.room_sid}">${room.room_name}</option>
        `;
    }).join("");

    /*목록으로*/
    $(".go_layout_list").click(()=>{
        location.href= PAGE_LIST.HOTEL_LAYOUT_LIST;
    });
    
    /*층 추가*/
    $(".floor_add").click(()=>{
        const layoutAddCon = $(".layout_add_con");
        const floorHTML =
            `
            <div class="floor_box">
                <button type="button" class="room_add">객실 추가</button>
                <button type="button" class="room_delete" disabled>객실 삭제</button>
                <span class="floor_${floorCnt}">${floorCnt}F</span>
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
    $(".floor_delete").click((event)=>{
        const layoutAddCon = $(".layout_add_con");
        const lastFloorBox = layoutAddCon.find(".floor_box").last();
        lastFloorBox.remove();
        floorCnt--;
    });

    /*객실 추가*/
    $(document).on("click",".room_add",(event)=>{
        const floorBox = $(event.target).closest(".floor_box");
        const roomBox = floorBox.find("div").filter(function() {
            return $(this).attr("class").startsWith("room");
        }).last();
        const roomCount = floorBox.find(".room").length;
        console.log(roomCount);
        if (roomCount >= 1) {
            floorBox.find(".room_delete").prop("disabled", false);
        }

        const nextNumber = parseInt(roomBox.find(".room_number").val())+1;
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
    $(document).on("click",".room_delete",(event)=>{
        const floorBox = $(event.target).closest(".floor_box");
        const lastRoomBox =floorBox.find(".room").last();
        const roomCount = floorBox.find(".room").length;
        console.log(roomCount);
        if (roomCount <= 2) {
            floorBox.find(".room_delete").prop("disabled", true);
        }
        lastRoomBox.remove();

    });
});