$(function () {
    $(document).on("click", ".removeImg", (event) => {
        const room_img_sid = $(event.target).val();
        const current_img_box = $(event.target).closest(".current_img_box");
        const current_img = current_img_box.find(".currentImg").val();
        const thenFn = (result) => {
            if (result.isConfirmed) {
                const ajaxObj = {
                    url: API_LIST.DELETE_ROOM_IMG,
                    method: "delete",
                    param: {
                        room_img_sid: room_img_sid,
                        current_img: current_img
                    },
                    successFn: () => {
                        const thenFn = () =>{
                            location.reload();
                        }
                        swalCall("성공", "이미지 삭제 완료", "success",thenFn);
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("이미지 삭제", "해당 이미지를 삭제하시겠습니까?", "question", thenFn, "예", true);
    });

    $(".room_update_btn").click(() => {
        const roomUpdateForm = $(".roomUpdateForm");

        roomUpdateForm.submit();
    });
});
