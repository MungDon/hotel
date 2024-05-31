 function ajaxCall(url,method,param,successFn,errorFn){
        $.ajax({
            url: url,
            method: method,
            data: param,
            success: function (data) {
                if(typeof successFn =="function"){
                    successFn(data);
                }
            },
            error: function () {
                if(typeof errorFn =="function"){
                    errorFn();
                }
            }
        });
    };
    	function logout() {
			ajaxCall("/user/logout", "DELETE", null, function() {
				alert('로그아웃 되었습니다.');
				location.href="/hotel";
			}, function() {
				alert("로그아웃 실패");
			});
		};
