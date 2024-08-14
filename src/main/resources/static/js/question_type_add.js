$(function(){
    $(".insertOption").click(() => {
        const questionTypeOptionHTML = `
        <div class="question_type_add_box">
            <input type="text" name="type_name" class="question_type_name" placeholder="문의 타입명">
            <button type="button" class="removeOptionBtn">-</button>
        </div>
        `
        $(".question_type_add_con").append(questionTypeOptionHTML);
    });

    $(document).on("click", ".removeOptionBtn", (event) => {
        $(event.target).closest(".question_type_add_box").remove();
    });

    $(".saveBtn").click(() => {
        const addList = [];
        $(".question_type_add_box").each((index, objDOM) => {
            const obj = $(objDOM);
            const typeName = obj.find(".question_type_name").val();
            const addObj = {
                typeName :typeName
            }
            addList.push(addObj);
        });
        const ajaxObj = {
            url: API_LIST.QUESTION_TYPE_ADD,
            method: "post",
            contentType : "application/json",
            param: JSON.stringify(addList),
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