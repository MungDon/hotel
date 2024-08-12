$(function () {
    const $sliderInner = $(".slider_inner");
    const $slides = $(".slider");
    const slideWidth = $slides.first().outerWidth();
    const slidesCount = $slides.length;


    $slides.each(function () {
        const $clone = $(this).clone();
        $sliderInner.append($clone);
    });


    const totalSlides = slidesCount * 2;
    $sliderInner.css('width', `${totalSlides * slideWidth}px`);

    let currentIndex = 0;
    let isAnimating = false;
    let autoSlideInterval;

    $(".moveBtn.prev").on("click", () => moveSlide(-1));
    $(".moveBtn.next").on("click", () => moveSlide(1));

    const moveSlide = (direction) => {
        if (isAnimating) return;

        isAnimating = true;
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
                isAnimating = false;
            }, 800);
        } else if (newIndex === slidesCount - 1 && direction === -1) {
            setTimeout(() => {
                $sliderInner.css({
                    transition: 'none',
                    transform: `translateX(-${(slidesCount - 1) * slideWidth}px)`
                });
                currentIndex = slidesCount - 1;
                isAnimating = false;
            }, 800);
        } else {
            currentIndex = newIndex;
            setTimeout(() => {
                isAnimating = false;
            }, 800);
        }
    }

    const startAutoSlide= () => {
        autoSlideInterval = setInterval(() => {
            moveSlide(1);
        }, 3500);
    }

    const stopAutoSlide = () => {
        clearInterval(autoSlideInterval);
    }

    startAutoSlide();


    $(".moveBtn").on("mouseenter", () => stopAutoSlide());
    $(".moveBtn").on("mouseleave", () => startAutoSlide());
});