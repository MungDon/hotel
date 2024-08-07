$(function () {
    $(".insertOption").click(() => {
        const typeOptionHTML = `
          <div class="typeAddBox">
            <span>객실 타입 명 : </span><input type="text" name="type_name" class="typeName">
            <span>객실 크기 : </span><input type="text" name="room_size" class="roomSize">
            <span>침대 크기 : </span><input type="text" name="bed_size" class="bedSize">
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
        $(".typeAddBox").each((index,obj) => {
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
                param : roomTypeObjs,
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
        })
    })
});