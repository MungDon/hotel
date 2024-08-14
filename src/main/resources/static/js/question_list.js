$(function(){
    $(".question_add_btn").click(()=>{
        location.href=PAGE_LIST.QUESTION_ADD_FORM;
    });
    $(".question_title").click((event)=>{
        const questionSid = $(event.target).data("question-sid");
    });
});