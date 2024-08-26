$(function () {
    const getInfoOptionIndex = (targetId) =>{
        let maxIndex = -1;
        const regex = new RegExp(`${targetId}\\[(\\d+)\\]`);
        $('.'+targetId +' input').each(function() {
            const name = $(this).attr('name');
            const match = name.match(regex);
            if (match) {
                const index = parseInt(match[1], 10);
                if (index > maxIndex) {
                    maxIndex = index;
                }
            }
        });
        return maxIndex + 1; // 새 인덱스는 현재 최대값 + 1
    }

    let optionCount = getInfoOptionIndex("options");
    let useOptionCount =  getInfoOptionIndex("useOptions");

    $(".room_add_btn").click(() => {
        const addForm = $(".roomAddForm");
        const errorText = $(".error_text").text();
        console.log(errorText);
        if (errorText) {
            swalCall("경고", "객실 유형이 잘못되었습니다.", "warning");
            return;
        }

        // 유효성 검사 추가 작성
        addForm.submit();
    });
    $(".room_type").change((event) => {
        const roomTypeSid = $(event.target).val();

        if (isNull(roomTypeSid)) {
            $(".preview_room_option").empty();
            return;
        }
        const ajaxObj = {
            url: API_LIST.FIND_ROOM_TYPE_DATA,
            method: "post",
            param: {
                roomTypeSid: roomTypeSid
            },
            successFn: (roomTypeData) => {
                if (!isNull(roomTypeData)) {
                    $(".preview_room_option").empty();
                    const typeDetailHTML =
                        `
                            <span>객실 사이즈 | ${roomTypeData.room_size}㎡</span>
                            <span>침대 사이즈 | ${roomTypeData.bed_size}</span>
                        `;
                    $(".preview_room_option").append(typeDetailHTML);
                } else {
                    swalCall("경고", "객실 타입 데이터 불러오기 실패", "warning");
                    return;
                }
            },
            errorFn: (errorResponse) => {
                const error = errorResponse.responseJSON;
                $(".preview_room_option").empty();
                const errorTextHTML =
                    `
                    <span class="error_text">${error.message}</span>
                    `;
                $(".preview_room_option").append(errorTextHTML);
                $(".preview_room_option span").hide().fadeIn(1000).css("color", "red"); // 1000ms = 1초
            }
        }
        ajaxCall(ajaxObj);
    });
    $(document).on("click",".add_info_option",()=>{
        const addInfoOptionHTML = `
            <div class="options">
                옵션명&nbsp;<input type="text" class="o1" id="options[${optionCount}].optio n_name" name="options[${optionCount}].option_name">&nbsp;
                내용&nbsp;<input type="text" class="o1" id="options[${optionCount}].option_value" name="options[${optionCount}].option_value">&nbsp;
                <button type="button" class="remove_option_btn">옵션 제거</button>
            </div>
        `;
        $("#plusOptions").append(addInfoOptionHTML);
        console.log(optionCount);
        optionCount++;
        console.log(optionCount);
    });


    $(document).on("click",".add_use_option", ()=>{
        const addUseOptionHTML =
        `
            <div class="useOptions">
                옵션명&nbsp;<input type="text" class="o2" id="useOptions[${useOptionCount}].option_name" name="useOptions[${useOptionCount}].option_name">&nbsp;
                내용&nbsp;<textarea  class="o2 textarea" id="useOptions[${useOptionCount}].option_value" name="useOptions[${useOptionCount}].option_value"></textarea>&nbsp;
                <button type="button" class="remove_option_btn">옵션 제거</button>
            </div>
        `;
        $("#plusUseOptions").append(addUseOptionHTML);
        useOptionCount++;
    });

    $(document).on("click", ".remove_option_btn", (event) => {
        $(event.target).closest("div").remove();
    });

    $(".back_list_btn").click(() => {
        location.href = PAGE_LIST.ROOM_MANAGE_LIST;
    });
});