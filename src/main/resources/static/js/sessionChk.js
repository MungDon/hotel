document.addEventListener('DOMContentLoaded', function() {
    const user_sid = document.getElementById('user_sid').value;
    if (user_sid == null || user_sid == '') {
        alert('로그인이 필요합니다.');
        console.log('작동함?');
        location.href='/user/login';
    }
});