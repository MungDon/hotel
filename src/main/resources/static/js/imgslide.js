$(document).ready(function() {
    const sliderWrap = $(".slider_wrap");
    const sliderImg = $(".slider_img");       // 보여지는 영역
    const sliderInner = $(".slider_inner");   // 움직이는 영역
    const sliders = $(".slider");             // 이미지

    let currentIndex = 0;                      // 현재 이미지
    let sliderCount = sliders.length;          // 이미지 갯수
    let sliderWidth;

    // 이미지 로드가 완료된 후 슬라이더 초기화
    const setupSlider = () => {
        sliderWidth = sliderImg.width();        // 이미지 가로값

        sliderInner.css("width", sliderWidth * sliderCount + "px");

        // 이미지 이동
        const gotoSlider = (num) => {
            sliderInner.css({
                "transition": "all 400ms",
                "transform": "translateX(" + (-sliderWidth * num) + "px)"
            });
            currentIndex = num;
        }

        // 버튼 클릭했을 때
        $(document).on("click", ".slider_btn a", function(event) {
            event.preventDefault(); // 기본 링크 동작 방지

            let prevIndex = (currentIndex + (sliderCount - 1)) % sliderCount;
            let nextIndex = (currentIndex + 1) % sliderCount;

            if ($(this).hasClass("prev")) {
                gotoSlider(prevIndex);
            } else {
                console.log(nextIndex); // 버튼 클릭 로그 확인
                gotoSlider(nextIndex);
            }
        });

        // Initial display
        gotoSlider(currentIndex);
    }

    // Ensure images are loaded before setting up the slider
    const images = sliderImg.find("img");
    let imagesLoaded = 0;

    images.each(function() {
        $(this).on("load", function() {
            imagesLoaded++;
            if (imagesLoaded === images.length) {
                setupSlider();
            }
        }).each(function() {
            if (this.complete) $(this).load(); // Cache issue
        });
    });
});
