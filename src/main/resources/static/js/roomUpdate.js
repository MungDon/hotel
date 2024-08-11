  $(function() {
	$(document).on("click", ".removeImg", (event)=> {
		$(event.target).parent().remove();
		const room_img_sid = $(event.target).val();
		const current_img = $(event.target).closest(".currentImg").val();
		const ajaxObj = {
			url : API_LIST.DELETE_ROOM_IMG,
			method : "delete",
			param : {
				room_img_sid : room_img_sid,
				current_img : current_img
			},
			successFn : (resultResponse) => {
				if(resultResponse.success){
					swalCall("성공",resultResponse.message,"success");
				}
			}
		}
		ajaxCall(ajaxObj);
	});
});
