$(function () {
    $(".insertOption").click(() => {
        const typeOptionHTML = `
          <div class="typeAddBox">
           <input type="text" name="type_name" class="typeName" placeholder="객실 타입 명">
            <input type="text" name="room_size" class="roomSize" placeholder="객실 크기">
            <input type="text" name="bed_size" class="bedSize" placeholder="침대 크기">
            <button type="button" class="removeOptionBtn">-</button>
          </div>
        `
        $(".typeAddCon").append(typeOptionHTML);
    });

    $(document).on("click", ".removeOptionBtn", (event) => {
        $(event.target).closest(".typeAddBox").remove();
    })

    $(".saveBtn").click(() => {
        const roomTypeObjs = [];
        $(".typeAddBox").each((index,objDOM) => {
            const obj = $(objDOM);
            const typeName = obj.find(".typeName").val();
            const roomSize = obj.find(".roomSize").val();
            const bedSize = obj.find(".bedSize").val();
            roomTypeObjs.push({
                type_name : typeName,
                room_size : roomSize,
                bed_size : bedSize
            })

            const ajaxObj = {
                url : API_LIST.ROOM_TYPE_ADD,
                method : "post",
                contentType : "application/json",
                param :JSON.stringify(roomTypeObjs),
                successFn : (resultResponse) =>{
                    if(resultResponse.success){
                        const thenFn = () => {
                            location.href = PAGE_LIST.ROOM_TYPE_LIST;
                        }
                        swalCall("성공", resultResponse.message,"success",thenFn);
                    }
                }
            }
            ajaxCall(ajaxObj);
        });
    });

    $(".listBtn").click(()=>{
        location.href = PAGE_LIST.ROOM_TYPE_LIST;
    });
});