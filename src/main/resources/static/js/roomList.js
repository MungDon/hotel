$(function() {
	pagination();
	function pagination() {

		//ResPaging의 멤버인 List<T> 타입의 list 를 의미
		const list = /*[[${rooms.list}]]*/[];

		// Respaging 의 멤버인 pagination을 의미
		const pagination = /*[[${rooms.pagination}]]*/{};

		// @ModelAttribute를 이용해서 뷰로 전달한 searchDto 타입의 객체인 search 를 의미
		const search = /*[[${search}]]*/{};

		// 리스트에 출력되는 게시글 번호를 처리하기 위해 사용되는 변수 (리스트에서 번호는 페이지 정보를 이용해서 계산해야함)
		let num = pagination.totalRecordCount - ((search.page - 1) * search.recordSize);

		// 페이지 렌더링
		drawPage(pagination, search);
	}

	function drawPage(pagination, search) {
		if (!pagination || !search) {
			document.querySelector('.paging').innerHTML = '';
			throw new Error('Missing required parameters...');
		}

		let html = '';

		if (pagination.existPrevPage) {
			html += `
					<a href="javascript:void(0);" onclick="movePage(1)" class="firstPage">첫페이지</a>
					<a href="javascript:void(0);" onclick="movePage(${pagination.startPage - 1})" class="prevPage">이전페이지</a>
				`;
		}
		html += '<p>';
		for (let i = pagination.startPage; i <= pagination.endPage; i++) {
			html += (i !== search.page)
				? `<a href="javascript:void(0);" onclick="movePage(${i});">${i}</a>`
				: `<span class="on">${i}</span>`
		}
		html += '</p>';

		if (pagination.existNextPage) {
			html += `
					<a href="javascript:void(0);" onclick="movePage(${pagination.endPage + 1});" class="page_bt_next">다음페이지</a>
					<a href="javascript:void(0);" onclick="movePage(${pagination.totalPageCount});" class="page_bt_last">마지막 페이지</a>
				`;
		}
		document.querySelector('.paging').innerHTML = html;
	}

	function movePage(page) {

		const queryParams = {
			page: (page) ? page : 1,
			recordSize: 10,
			pageSize: 10
		}

		location.href = location.pathname + '?' + new URLSearchParams(queryParams).toString();
	}

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
	                        `;

				room.options.forEach(function(option) {
					html += `
	                                <tr>
	                                    <th>${option.option_name}</th>
	                                    <td>${option.option_value}</td>
	                                </tr>
	                            `;
				});
				room.images.forEach(function(img) {

					html += `
	                                    <tr>
	                                        <td colspan="2">
	                                            <img src="/img/${img.img_name}" style="margin-bottom: 20px; width: 400px">
	                                        </td>
	                                    </tr>
	                                </tbody>
	                         
	                        `;
				});
				html += `
	            			<button type="button" class="removeRoom" value="${room.room_sid}">삭제하기</button>
	                        <button type="button" class="restore" value="${room.room_sid}">복구하기</button>
	                           </table>
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


	$(document).on("click", ".restore", function() {
		let room_sid = $(this).val();
		if (!confirm("방을 복구하시겠습니까?")) {
			return false;
		}
		ajaxCall("/room/restore", "PUT", { room_sid }, function() {
			alert('방이 복구되었습니다.');
			location.href = "/room";
		}, function() {
			alert("방 복구 실패");
		})
	});
	$(document).on("click", ".removeRoom", function() {
		let room_sid = $(this).val();
		if (!confirm("방을 영구 삭제하시겠습니까?")) {
			return false;
		}
		ajaxCall("/room/remove", "DELETE", { room_sid }, function() {
			alert("방이 영구 삭제되었습니다.");
			location.href = "/room";
		}, function() {
			alert("방 영구 삭제 실패 운영자에게 문의해주세요");
		})
	});
});