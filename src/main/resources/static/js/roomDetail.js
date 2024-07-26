let reserveFormWindow;
$(document).ready(function() {
	$('.roomInfo').each(function() {
		var optionName = $(this).find('.optionName').text().trim();
		var valueCell = $(this).find('.optionValue');
		if (optionName === '객실크기') {
			valueCell.text(valueCell.text().trim() + " ㎡");
		}
	});

	$('.reserveBtn').click(function() { 
		let user_sid = $(this).val();
		if(user_sid == null || user_sid === ""){
			alert("로그인이 필요한 서비스입니다.");
			window.opener.location.href = "/user/login";
			window.close();
		}
		let room_sid = $('input[name="room_sid"]').val();
		let startDate = $('input[name="start_date"]').val();
		let endDate = $('input[name="end_date"]').val();
		let adultCnt = $('input[name="adult_cnt"]').val();
		let childCnt = $('input[name="child_cnt"]').val();

		let url = '/reserve?room_sid=' + room_sid + '&start_date=' + startDate + '&end_date=' + endDate + '&adult_cnt=' + adultCnt + '&child_cnt=' + childCnt;
		let popupWidth = 600;
		let popupHeight = 500;
		let popupX = Math.round(window.screenX + (window.outerWidth / 2) - (popupWidth / 2));
		let popupY = Math.round(window.screenY + (window.outerHeight / 2) - (popupHeight / 2));
		let options = 'width=' + popupWidth + ', height=' + popupHeight + ', top=' + popupY + ', left=' + popupX;
		if (reserveFormWindow && !reserveFormWindow.closed) {
			reserveFormWindow.close();
		}

		reserveFormWindow = window.open(url, 'Room Details', options);
	});
});

$(document).on("click", ".removeRoom", function() {
	let room_sid = $(this).val();
	if (!confirm("방을 삭제하시겠습니까?")) {
		return false;
	}
	console.log("체크");
	$.ajax({
		type: 'delete',
		async: false,
		url: '/room/delete',
		data: {
			room_sid: room_sid
		},
		success: function() {
			alert('방이 삭제되었습니다.');
			window.opener.location.href = "/room";
			window.close();

		},
		error: function() {
			alert("방 삭제 실패");
		}
	});
});
$(document).on("click", ".updateRoom", function() {
		let room_sid = $(this).val();
		window.opener.location.href = "/room/update/"+room_sid;
		window.close();
});
