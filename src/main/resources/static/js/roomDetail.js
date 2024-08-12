$(function () {
    const $sliderInner = $(".slider_inner");
    const $slides = $(".slider");
    const slideWidth = $slides.first().outerWidth();
    const slidesCount = $slides.length;

    // Clone slides and append to the slider_inner
    $slides.each(function () {
        const $clone = $(this).clone();
        $sliderInner.append($clone);
    });

    // Set the width of the slider_inner to accommodate all slides
    const totalSlides = slidesCount * 2; // Number of total slides after cloning
    $sliderInner.css('width', `${totalSlides * slideWidth}px`);

    let currentIndex = 0;
    let isAnimating = false; // Flag to check if animation is in progress

    $(".moveBtn.prev").on("click", () => moveSlide(-1));
    $(".moveBtn.next").on("click", () => moveSlide(1));

    function moveSlide(direction) {
        if (isAnimating) return; // Prevent further clicks while animating

        isAnimating = true; // Set animation flag
        const newIndex = (currentIndex + direction + slidesCount) % slidesCount;
        $sliderInner.css({
            transition: 'transform 0.8s ease',
            transform: `translateX(-${(currentIndex + direction) * slideWidth}px)`
        });

        if (newIndex === 0 && direction === 1) {
            setTimeout(() => {
                $sliderInner.css({
                    transition: 'none',
                    transform: `translateX(0px)`
                });
                currentIndex = 0;
                isAnimating = false; // Reset animation flag
            }, 800);
        } else if (newIndex === slidesCount - 1 && direction === -1) {
            setTimeout(() => {
                $sliderInner.css({
                    transition: 'none',
                    transform: `translateX(-${(slidesCount - 1) * slideWidth}px)`
                });
                currentIndex = slidesCount - 1;
                isAnimating = false; // Reset animation flag
            }, 800);
        } else {
            currentIndex = newIndex;
            setTimeout(() => {
                isAnimating = false; // Reset animation flag
            }, 800); // Matches the duration of the transition
        }
    }

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
