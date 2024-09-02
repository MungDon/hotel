$(function () {
    const initSlider = () => {
        const sliderInner = $(".slider_inner");
        sliderInner.css('transform', 'translateX(0)');
    }
    initSlider();
    $(document).on("click", ".moveBtn", (event) => {
        event.preventDefault();
        const sliders = $(".slider");
        const sliderCount = sliders.length;
        const sliderInner = $(".slider_inner");
        const slideWidth = sliders.first().outerWidth();

        let transform = sliderInner.css('transform');
        let currentIndex = 0;

        if (transform !== 'none') {
            const matrix = transform.match(/matrix\(([^)]+)\)/);
            if (matrix) {
                currentIndex = -parseInt(matrix[1].split(',')[4]) / slideWidth;
            }
        }

        if ($(event.target).hasClass("prev")) {
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
        const thenFn = (result) => {
            if (result.isConfirmed) {
                const ajaxObj = {
                    url: API_LIST.DELETE_ROOM,
                    method: "delete",
                    param: {
                        room_sid : room_sid
                    },
                    successFn: () => {
                        swalCall("성공", "해당 객실이 삭제 되었습니다.", "success", () => {
                            location.reload();
                        });
                    }
                };
                ajaxCall(ajaxObj);
            }else{
                return;
            }
        }
        swalCall("경고", "객실을 삭제 하시겠습니까?", "warning", thenFn,"예",true);
    });

    $(document).on("click", ".updateRoom", (event) => {
        const room_sid = $(event.target).val();
        location.href = PAGE_LIST.ROOM_UPDATE_FORM + room_sid;
    });
});
