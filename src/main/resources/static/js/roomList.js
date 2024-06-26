	let roomDetailWindow;
	$(document).ready(function() {
	    $('.roomDetail').click(function() { // 클래스가 roomDetail인것을 클릭하면
	        //필요한 데이터
	        let room_sid = $(this).data('room-sid'); // 해당 요소의 data 를가져옴
            let startDate = $('input[name="start_date"]').val();
            let endDate = $('input[name="end_date"]').val();
            let adultCnt = $('input[name="adult_cnt"]').val();
            let childCnt = $('input[name="child_cnt"]').val();
            
            // 팝업창 옵션
	        let popupWidth = 600;
	        let popupHeight = 500;
	        let popupX = Math.round(window.screenX + (window.outerWidth/2) - (popupWidth/2));
	        let popupY = Math.round(window.screenY + (window.outerHeight/2) - (popupHeight/2));
	        
	        let url = '/room/detail/'+room_sid+'?start_date='+startDate +'&end_date='+endDate+'&adult_cnt='+adultCnt+'&child_cnt='+childCnt;
	       	let options = 'width='+popupWidth+', height='+popupHeight+', top='+popupY+', left='+popupX;
	        if (roomDetailWindow && !roomDetailWindow.closed) {
	            roomDetailWindow.close();
	        }
	        
	        roomDetailWindow = window.open(url, 'Room Details', options);
	    });
	});
    // 휴지통에 있는 객실 복구
    $(document).on("click", ".restore", function() {
		let room_sid = $(this).val();
		if (!confirm("방을 복구하시겠습니까?")) {
			return false;
		}
		ajaxCall("/room/restore", "PUT", {room_sid}, function() {
			alert('방이 복구되었습니다.');
			location.href = "/room";
		}, function() {
			alert("방 복구 실패");
		})
	});
	
	// 휴지통에 있는 객실 영구 삭제
	$(document).on("click", ".removeRoom", function() {
		let room_sid = $(this).val();
		if (!confirm("방을 영구 삭제하시겠습니까?")) {
			return false;
		}
		ajaxCall("/room/remove", "DELETE", {room_sid}, function() {
			alert("방이 영구 삭제되었습니다.");
			location.href = "/room";
		}, function() {
			alert("방 영구 삭제 실패 운영자에게 문의해주세요");
		})
	});
	
	// 예약하기 버튼
	$(document).on("click", ".reserveBtn", function (){
		const options = 'width=700, height=600, top=50, left=50, scrollbars=no';
		window.open('','_self',options);
	});
	
	// 휴지통
	$(document).on("click", ".deleteList", function() {
		$(".deleteRooms").toggle();
		let buttonText = $(this).text();
		

		ajaxCall("/room/delete/list", "GET", null, function(data) {
			let html = "";

			data.forEach(function(room) {
				html += `
	                            <table class="dtable">
	                                <tbody>
	                                    <tr>
	                                        <td>${room.room_info}</td>
	                                    </tr>
	                                    <tr>
	                                        <td colspan="2">
	                                            <img src="/img/${room.thumbnail.img_name}" style="margin-bottom: 20px; width: 400px">
	                                        </td>
                                   		</tr>
	                        `;

				room.options.forEach(function(option) {
					html += `
	                                <tr>
	                                    <th>${option.option_name}</th>
	                                    <td>${option.option_value}</td>
	                                </tr>
	                                </tbody>
	                                </table>
	                            `;
				});
				

	                             
	                          
				html += `
	            			<button type="button" class="removeRoom" value="${room.room_sid}">삭제하기</button>
	                        <button type="button" class="restore" value="${room.room_sid}">복구하기</button>
	                           
	                         `;

			});
			$(".deleteRooms").html(html);
		},
			function() {
				alert("삭제 리스트 조회실패");
			});

		if (buttonText === "휴지통") {
			$(this).text("닫기");
		} else {
			$(this).text("휴지통");
		}
	});