$(function () {
    $(document).on("click", ".moveBtn", (event)=> {
        console.log("이미지 슬라이드");
        block_btn();
        const sliders = $(".slider");
        const sliderCount = sliders.length;
        const slideWidth = sliders.first().outerWidth();
        const sliderInner = $(".slider_inner");
        const currentTransform = sliderInner.css('transform');
        const currentIndex = currentTransform === 'none' ? 0 : Math.abs(parseInt(currentTransform.split(',')[4]) / slideWidth);

        let newIndex = 0;
        if ($(event.target).hasClass("prev")) {
            newIndex = (currentIndex - 1 + sliderCount) % sliderCount;
        } else {
            newIndex = (currentIndex + 1) % sliderCount;
        }

        gotoSlider(newIndex);
    });

    const gotoSlider = (index) => {
        const sliders = $(".slider");
        const sliderInner = $(".slider_inner");
        const slideWidth = sliders.first().outerWidth();
        const newTransform = `translateX(-${index * slideWidth}px)`;
        sliderInner.css('transform', newTransform);
    };

    const block_btn = () => {
        $('.slider_btn button').css({pointerEvents: 'none'});
        setTimeout(function () {
            $('.slider_btn button').css({pointerEvents: 'auto'});
        }, 800);
    };


    // 객실 삭제 (논리) - 객실 상세보기 연결
    $(document).on("click", ".deleteRoom", () => {
        const room_sid = $(this).val();
        const thenFn = (result) => {
            if (result.isConfirmed) {
                const ajaxObj = {
                    url: API_LIST.DELETE_ROOM,
                    method: "delete",
                    param: room_sid,
                    successFn: () => {
                        const thenFn = () => {
                            location.reload();
                        }
                        swalCall("성공","삭제되었습니다, <br>삭제된 객실은 휴지통에서 복구가능합니다","success", thenFn);
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        };
        swalCall("경고", "객실을 삭제 하시겠습니까?", "warning", thenFn);
    });
    $(document).on("click", ".updateRoom",  () => {
        let room_sid = $(this).val();
        location.href = "/room/update/" + room_sid;
    });

});