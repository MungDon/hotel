$(function(){
    $(".question_type").change((event) => {
        const questionType = $(event.target).val();
        if(isNull(questionType)){
            return;
        }
        location.href = "?questionType="+ questionType;
    });
});