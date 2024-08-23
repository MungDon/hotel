$(function () {

    $(document).on("click", ".moveBtn", (event) => {
        event.preventDefault();
        const sliders = $(".slider");
        const sliderCount = sliders.length;
        const slideWidth = sliders.first().outerWidth();
        let currentIndex = Math.abs(parseInt($(".slider_inner").css('transform').split(',')[4]) / slideWidth);


        if ($(this).hasClass("prev")) {
            currentIndex = (currentIndex - 1 + sliderCount) % sliderCount;
        } else {
            currentIndex = (currentIndex + 1) % sliderCount;
        }

        gotoSlider(currentIndex);
    });

    const gotoSlider = (index) => {
        const sliders = $(".slider");
        const sliderInner = $(".slider_inner");
        const slideWidth = sliders.first().outerWidth();
        const newTransform = `translateX(-${index * slideWidth}px)`;
        sliderInner.css('transform', newTransform);
    };

    // 객실 삭제 (논리) - 객실 상세보기 연결
    $(document).on("click", ".deleteRoom", (event)=> {
        const room_sid = $(event.target).val();
        swalCall("경고", "객실을 삭제 하시겠습니까?", "warning", (result) => {
            if (result.isConfirmed) {
                const ajaxObj = {
                    url: API_LIST.DELETE_ROOM,
                    method: "delete",
                    param: room_sid,
                    successFn: () => {
                        swalCall("성공", "삭제되었습니다, <br>삭제된 객실은 휴지통에서 복구가능합니다", "success", () => {
                            location.reload();
                        });
                    }
                };
                ajaxCall(ajaxObj);
            }
        });
    });

    $(document).on("click", ".updateRoom", function() {
        const room_sid = $(this).val();
        location.href = "/room/update/" + room_sid;
    });
});
