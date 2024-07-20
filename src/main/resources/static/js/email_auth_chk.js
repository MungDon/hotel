$(function(){
    $(".emailAuthBtn").click(()=>{
        const email = $("#email").val();
        isNull(email,"이메일을 입력해주세요");
        
    });

});