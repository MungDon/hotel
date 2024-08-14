$(function(){
    $(".insertOption").click(() => {
        const questionTypeOptionHTML = `
        <div class="question_type_add_box">
            <input type="text" name="type_name" class="question_type_name" placeholder="문의 타입명">
            <button type="button" class="insertOption">+</button>
        </div>
        `
        $(".question_type_add_con").append(questionTypeOptionHTML);
    });

    $(document).on("click", ".removeOptionBtn", (event) => {
        $(event.target).closest(".question_type_add_box").remove();
    });

    $(".saveBtn").click(() => {
        const formData = new FormData();
        $(".question_type_add_box").each((index, objDOM) => {
            const obj = $(objDOM);
            const type_name = obj.find(".type_name").val();

            formData.append(`type_name`, type_name);
        });
        const ajaxObj = {
            url: API_LIST.QUESTION_TYPE_ADD,
            method: "post",
            contentType: false,
            processData : false,
            param: formData,
            successFn: (resultResponse) => {
                if (resultResponse.success) {
                    const thenFn = () => {
                        location.href = PAGE_LIST.QUESTION_TYPE_LIST;
                    }
                    swalCall("성공", resultResponse.message, "success", thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });
    $(".listBtn").click(() => {
        location.href = PAGE_LIST.QUESTION_TYPE_LIST;
    });
});