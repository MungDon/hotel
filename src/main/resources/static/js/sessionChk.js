document.addEventListener('DOMContentLoaded', () => {
    const user_sid = document.getElementById('user_sid').value;
     const user_role = document.getElementById('user_role').value;
    if (user_sid == null || user_sid == '') {
        alert('로그인이 필요합니다.');
             setTimeout(() => {
                    window.location.href = '/user/login';
              }, 0);
    }
    if(user_role=='USER'){
		 alert('권한이 없습니다.');
             setTimeout(() => {
                    window.location.href = '/hotel';
              }, 0);
	}
});
