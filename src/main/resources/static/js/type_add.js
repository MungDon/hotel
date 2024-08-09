$(function () {
    $(".insertOption").click(() => {
        const typeOptionHTML = `
          <div class="typeAddBox">
           <input type="text" name="type_name" class="typeName" placeholder="객실 타입 명">
            <input type="text" name="room_size" class="roomSize" placeholder="객실 크기">
                        <select class="bedSize"  name="bed_size">
                <option value="">==< 침대크기 >==</option>
                <option value="싱글">싱글</option>
                <option value="슈퍼싱글">슈퍼싱글</option>
                <option value="더블">더블</option>
                <option value="퀸">퀸</option>
                <option value="킹">킹</option>
                <option value="슈퍼킹">슈퍼킹</option>
            </select>
            <button type="button" class="removeOptionBtn">-</button>
          </div>
        `
        $(".typeAddCon").append(typeOptionHTML);
    });

    $(document).on("click", ".removeOptionBtn", (event) => {
        $(event.target).closest(".typeAddBox").remove();
    })

    $(".saveBtn").click(() => {
        const formData = new FormData();
        $(".typeAddBox").each((index, objDOM) => {
            const obj = $(objDOM);
            const typeName = obj.find(".typeName").val();
            const roomSize = obj.find(".roomSize").val();
            const bedSize = obj.find(".bedSize").val();
            const typeImg = obj.find(".typeImg")[0].files[0];

            formData.append(`typeAdd[${index}].type_name`, typeName);
            formData.append(`typeAdd[${index}].room_size`, roomSize);
            formData.append(`typeAdd[${index}].bed_size`, bedSize);
            formData.append(`typeAdd[${index}].typeImg`, typeImg);
        });
        const ajaxObj = {
            url: API_LIST.ROOM_TYPE_ADD,
            method: "post",
            contentType: false,
            param: formData,
            successFn: (resultResponse) => {
                if (resultResponse.success) {
                    const thenFn = () => {
                        location.href = PAGE_LIST.ROOM_TYPE_LIST;
                    }
                    swalCall("성공", resultResponse.message, "success", thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });

    $(".listBtn").click(() => {
        location.href = PAGE_LIST.ROOM_TYPE_LIST;
    });
});