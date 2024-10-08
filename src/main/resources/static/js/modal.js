// 모달 열고 닫기
const openModal = (modal) => {
    modal.css("display", "block");
}
const closeModal = (modal) => {
    modal.css("display", "none");
}
// 모달 끄기
$(document).on("click", ".closeBtn, .cancelBtn", () => {
    const modal = $("#modalCon");
    $(".innerElement").empty();
    closeModal(modal);
});
// 모달 외부 클릭 이벤트
$(document).on("click","#modalCon",(event) => {
    if (window.location.pathname !== '/room') {
        $(".innerElement").empty();
        closeModal($(event.target));
    }
});
// 모달 내부 클릭 이벤트 방지
$(document).on('click','.modalBox', (event) => {
    event.stopPropagation();
});