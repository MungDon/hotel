
$(function(){
    const modalInnerElement = $(".innerElement");   // 모달 내용 요소
    const modal = $("#modalCon");                   // 모달 요소

    /*문의 유형 검색*/
    $(".question_type").change((event) => {
        const questionType = $(event.target).val();
        if(isNull(questionType)){
            location.href = removeQueryParam(location.href, "questionType");
            return;
        }
        location.href = updateQueryParam(location.href, "questionType",questionType);
    });
    /*답변 상태 검색*/
    $(".question_status").change((event) => {
        const questionStatus = $(event.target).val();
        if(isNull(questionStatus)){
            location.href = removeQueryParam(location.href, "questionStatus");
            return;
        }
        location.href = updateQueryParam(location.href, "questionStatus",questionStatus);
    });
    /*문의 상세보기 생성*/
    const createQuestionDetailModal = (event) => {
        const thisTr = $(event.target).closest("tr");
        const questionSid = $(event.target).val();
        const title = thisTr.find(".question_manage_list_title").text();
        const content = thisTr.find(".question_manage_list_content").val();
        const questionType = thisTr.find(".question_manage_list_td_4").text();
        const questionDate = thisTr.find(".question_manage_list_td_5").text();
        const userName = thisTr.find(".question_manage_list_td_3").text();
        const answerContent = thisTr.find(".question_manage_list_answer_content").val();

        modalInnerElement.empty();
        const questionDetailHTML = `
            <div class="question_detail_top">
                <span class="question_detail_type">${questionType}</span>
            </div>
            <div class="question_detail_main">
                <div class="question_detail_box">
                    <span class="question_detail_title">${title}</span>
                    <span class="question_detail_username">고객명 : ${userName}</span>
                    <span class="question_date">등록/수정 일자 : ${questionDate}</span> 
                    <textarea class="question_detail_content" readonly>${content}</textarea>
                </div>
                <div class="question_detail_answer_box">
                    <textarea class="question_detail_answer" placeholder="답변을 입력하세요"></textarea>
                </div>
                <div class="question_detail_btn_box">
                    <button type="button" class="answer_add_btn" value="${questionSid}">답변등록</button>
                    <button type="button" class="cancelBtn">취소</button>
                </div>
            </div>
        `;
        modalInnerElement.append(questionDetailHTML);
        if(!isNull(answerContent)){
            $(".question_detail_answer").val(answerContent).prop("readonly",true);
            const detailBtnBox = $(".question_detail_btn_box");
            detailBtnBox.empty();
            const updateBtnBoxHTML =
                `
                <button type="button" class="answer_update_set_btn" value="${questionSid}">답변수정</button>
                <button type="button" class="answer_delete_btn" value="${questionSid}">삭제</button>
                <button type="button" class="cancelBtn">취소</button>
                `;
            detailBtnBox.append(updateBtnBoxHTML);
        }
        openModal(modal);
    }

    $(".answer_btn").click((event)=>{
        createQuestionDetailModal(event);
    });
    $(".answer_manage_btn").click((event)=>{
        createQuestionDetailModal(event);
    });

    /*수정 셋*/
    $(document).on("click",".answer_update_set_btn", (event) => {
        const questionSid = $(event.target).val();
        const answerContent = $(".question_detail_answer");
        answerContent.prop("readonly", false);
        answerContent.focus();
        const detailBtnBox = $(".question_detail_btn_box");
        detailBtnBox.empty();
        const updateCancelBtnBoxHTML =
            `
              <button type="button" class="answer_update_btn" value="${questionSid}">답변수정</button>
                <button type="button" class="answer_update_cancel_btn" value="${questionSid}">취소</button>
            `;
        detailBtnBox.append(updateCancelBtnBoxHTML);
    })

    /*수정취소*/
    $(document).on("click",".answer_update_cancel_btn", (event)=>{
        const questionSid = $(event.target).val();
        const answerContent = $(".question_detail_answer");
        answerContent.prop("readonly", true);
        const detailBtnBox = $(".question_detail_btn_box");
        detailBtnBox.empty();
        const updateBtnBoxHTML =
            `
               <button type="button" class="answer_update_set_btn" value="${questionSid}">답변수정</button>
                <button type="button" class="answer_delete_btn" value="${questionSid}">삭제</button>
                <button type="button" class="cancelBtn">취소</button>
            `;
        detailBtnBox.append(updateBtnBoxHTML);
    });

    /*답변 삭제*/
    $(document).on("click", ".answer_delete_btn", (event)=>{
        const questionSid = $(event.target).val();
        const thenFn = (result) => {
            if(result.isConfirmed){
                const ajaxObj = {
                    url : API_LIST.ANSWER_DELETE,
                    method : "delete",
                    param : {
                        questionSid : questionSid
                    },
                    successFn : (resultResponse) => {
                        if(resultResponse.success){
                            const thenFn = () => {
                                location.reload();
                            }
                            swalCall("성공", resultResponse.message,"success",thenFn);
                        }else{
                            const thenFn = () => {
                                location.reload();
                            }
                            swalCall("삭제 실패", "답변 삭제에 실패하였습니다.","error",thenFn);
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("답변 삭제","해당 답변을 삭제하시겠습니까?","question",thenFn,"예", true);
    });
    /*수정*/
    $(document).on("click",".answer_update_btn", (event) => {
        const questionSid = $(event.target).val();
        const answerContent = $(".question_detail_answer").val();

        const answerUpdateObj = {
            questionSid : questionSid,
            content : answerContent
        }

        const ajaxObj = {
            url : API_LIST.ANSWER_UPDATE,
            method : "put",
            contentType : "application/json",
            param : JSON.stringify(answerUpdateObj),
            successFn : (resultResponse) => {
                if(resultResponse.success){
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("성공", resultResponse.message,"success",thenFn);
                } else {
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("실패", "답변수정에 실패하였습니다.","error",thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });

    /*답변 등록*/
    $(document).on("click",".answer_add_btn",(event) => {
        const questionSid = $(event.target).val();
        const answerContent = $(".question_detail_answer").val();
        const userSid = $(".user_session").val();

        const answerObj = {
            questionSid : questionSid,
            content : answerContent,
            userSid : userSid
        }

        const ajaxObj = {
            url : API_LIST.ANSWER_ADD,
            method : "post",
            contentType : "application/json",
            param : JSON.stringify(answerObj),
            successFn : (resultResponse) => {
                if(resultResponse.success){
                    const thenFn = () =>{
                        location.reload();
                        return;
                    }
                    swalCall("성공",resultResponse.message,"success",thenFn);
                } else {
                    const thenFn = () =>{
                        location.reload();
                        return;
                    }
                    swalCall("답변 작성 실패","답변 작성에 실패하였습니다","error",thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });

    const removeQueryParam =(url, paramName) => {
        // URL 객체 생성
        let urlObj = new URL(url);

        // URLSearchParams 객체를 가져옴
        let params = urlObj.searchParams;

        // 특정 파라미터 삭제
        params.delete(paramName);

        // 갱신된 URL 반환
        return urlObj.toString();
    }
    const updateQueryParam = (url, paramName, paramValue) => {
        // URL 객체 생성
        let urlObj = new URL(url);

        // URLSearchParams 객체를 가져옴
        let params = urlObj.searchParams;

        // 기존 파라미터가 있는 경우 제거 후 추가
        params.set(paramName, paramValue);

        // 갱신된 URL 반환
        return urlObj.toString();
    }
});