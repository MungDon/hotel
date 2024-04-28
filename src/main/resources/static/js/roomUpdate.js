$(function() {
	$(document).on("click", ".removeImg", function() {
		$(this).parent().remove();
		let room_img_sid = $(this).val();

		ajaxCall("/room/img/delete", "DELETE", {room_img_sid}, function() {
			alert('이미지가 삭제되었습니다.');

		}, function() {
			alert("삭제 실패");
		})

	});
});
