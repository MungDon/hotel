$(function(){
    const modalInnerElement = $(".innerElement");   // 모달 내용 요소
    const modal = $("#modalCon");                   // 모달 요소
    const questionStatus = $(".question_list_td_4");
    questionStatus.each((index, text) => {
        const $text = $(text).text();
        if($text == '답변완료'){
            $(text).css("color","green");
        }else{
            $(text).css("color","red");
        }
    });


    // 문의등록하기폼
    $(".question_add_btn").click(()=>{
        location.href=PAGE_LIST.QUESTION_ADD_FORM;
    });

    // 문의 상세보기
    $(".question_title").click((event)=>{
        const thisTr = $(event.target).closest("tr");
        const questionSid = $(event.target).data("question-sid");
        const title = thisTr.find(".question_title").text();
        const content = thisTr.find(".question_content").val();
        const questionType = thisTr.find(".question_type").val();
        const questionDate = thisTr.find(".question_list_td_3").text();
        const answerContent = thisTr.find(".answer_content").val();
        const adminName = thisTr.find(".admin_name").val();
        const answerCreatedDate = thisTr.find(".answer_created_date").val();
        const answerModifiedDate = thisTr.find(".answer_modified_date").val();

        const answerDate = new Date(answerCreatedDate) == new Date(answerModifiedDate) ?answerCreatedDate : answerModifiedDate;
        modalInnerElement.empty();
        const questionDetailHTML = `
            <div class="question_detail_top">
                <span class="question_detail_type">${questionType}</span>
            </div>
            <div class="question_detail_main">
                <div class="question_detail_box">
                    <input type="text" class="question_detail_title" value="${title}" readonly>
                    <span class="question_date">등록/수정 일자 : ${questionDate}</span> 
                    <textarea class="question_detail_content" readonly>${content}</textarea>
                </div>
                <div class="question_detail_answer_box">
                    <div class="question_detail_answer_top">
                        <span class="question_detail_answer">${adminName}</span>
                        <span  class="answer_date">${answerDate}</span>
                    </div>
                    ⤷ <span class="answer">${answerContent}</span>
                </div>
                <div class="question_detail_btn_box">
                    <button type="button" class="update_set_btn" value="${questionSid}">수정</button>
                    <button type="button" class="delete_btn" value="${questionSid}">삭제</button>
                </div>
            </div>
        `;
        modalInnerElement.append(questionDetailHTML);
        if(isNull(answerContent)){
            $(".question_detail_answer_box").css("display","none");
        } else {
            $(".update_set_btn").remove();
        }
        openModal(modal);
    });

    // 문의 수정 셋
    $(document).on("click", ".update_set_btn", (event) => {
        const questionSid = $(event.target).val();
        const btnBox = $(".question_detail_btn_box");
        const titleInput = $(".question_detail_title");
        titleInput.prop("readonly", false);
        titleInput.css("outline", "1px solid black");
        $(".question_detail_content").prop("readonly", false);
        btnBox.empty();
        const changeBtnBox = `
             <button type="button" class="update_btn" value="${questionSid}">수정</button>
             <button type="button" class="update_cancel_btn" value="${questionSid}">취소</button>
        `;
        btnBox.append(changeBtnBox);
    });

    // 수정취소 시 버튼 구성 복구
    const createBtnBoxForCancel = (questionSid) => {
        const btnBox = $(".question_detail_btn_box");
        const titleInput = $(".question_detail_title");
        titleInput.prop("readonly", true);
        titleInput.css("outline", "none");
        $(".question_detail_content").prop("readonly", true);
        btnBox.empty();
        const changeBtnBox =
            `
            <button type="button" class="update_set_btn" value="${questionSid}">수정</button>
            <button type="button" class="delete_btn" value="${questionSid}">삭제</button>
        `;
        btnBox.append(changeBtnBox);
    }

    // 수정 취소
    $(document).on("click",".update_cancel_btn",(event) => {
        const questionSid = $(event.target).val();
        createBtnBoxForCancel(questionSid);
    });

    // 수정하기
    $(document).on("click",".update_btn",(event) =>{
        const questionSid = $(event.target).val();
        const title = $(".question_detail_title").val();
        const content = $(".question_detail_content").val();

        const questionUpdateObj = {
            questionSid : questionSid,
            title : title,
            content : content
        }

        const ajaxObj = {
            url : API_LIST.QUESTION_UPDATE,
            method : "put",
            contentType : "application/json",
            param : JSON.stringify(questionUpdateObj),
            successFn : (resultResponse) => {
                if(resultResponse.success){
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("수정 성공", resultResponse.message, "success",thenFn);
                }else{
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("수정 실패", "문의 수정에 실패하였습니다.", "error",thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });

    // 삭제하기
    $(document).on("click", ".delete_btn", (event) => {
        const questionSid = $(event.target).val();
        const thenFn = (result) => {
            if(result.isConfirmed){
                const ajaxObj = {
                    url : API_LIST.QUESTION_DELETE,
                    method : "delete",
                    param : {
                        questionSid : questionSid
                    },
                    successFn : (resultResponse) => {
                        if(resultResponse.success){
                            const thenFn = () => {
                                location.reload();
                            }
                            swalCall("삭제 성공", resultResponse.message,"success",thenFn);
                        } else {
                            swalCall("삭제 실패", "문의 삭제에 실패하였습니다.","error");
                            return;
                        }
                    }
                }
                ajaxCall(ajaxObj);
            }else {
                return;
            }
        }
        swalCall("문의 삭제","해당 문의를 삭제하시겠습니까?", "question",thenFn,"예",true);
    });
});