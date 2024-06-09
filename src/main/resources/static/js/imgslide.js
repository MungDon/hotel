    const sliderWrap = document.querySelector(".slider_wrap");
    const sliderImg = document.querySelector(".slider_img");       // 보여지는 영역
    const sliderInner = document.querySelector(".slider_inner");   // 움직이는 영역
    const slider = document.querySelectorAll(".slider");            // 이미지

    // 이미지 갯수에 따라 닷메뉴 생성되도록 만들어야함

    let currentIndex = 0;                       // 현재 이미지
    let sliderCount = slider.length;            // 이미지 갯수
    let sliderWidth = sliderImg.offsetWidth;    // 이미지 가로값
    let dotIndex = "";


    // 이미지 이동
    function gotoSlider(num){
        sliderInner.style.transition = "all 400ms";
        sliderInner.style.transform = "translateX("+ -sliderWidth * num +"px)";
        currentIndex = num;
    }

    // 버튼 클릭했을 때
    document.querySelectorAll(".slider_btn a").forEach((btn, index) => {
        btn.addEventListener("click", () => {
            let prevIndex = (currentIndex + (sliderCount -1)) % sliderCount;
            					//2				3	//2				3 = 4%3
            let nextIndex = (currentIndex + 1) % sliderCount;

            if(btn.classList.contains("prev")){
                gotoSlider(prevIndex);
            } else {
                gotoSlider(nextIndex);
            }
        });
    })

