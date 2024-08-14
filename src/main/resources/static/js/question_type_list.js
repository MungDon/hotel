$(function () {
    $(".question_type_add_btn").click(() => {
        location.href = PAGE_LIST.QUESTION_TYPE_ADD_FORM;
    });

    $(document).on("click", ".update_set_btn", (event) => {
        const questionTypeSid = $(event.target).val();
        const thisTr = $(event.target).closest("tr");
        const questionTypeInput = thisTr.find("input");
        const questionTypeManageBtnBox = thisTr.find(".question_type_list_manage_btn_box");

        questionTypeInput.prop("readonly", false);
        questionTypeInput.css("outline", "1px solid black");
        questionTypeManageBtnBox.empty();
        const changeManageBtn =
            `
                <button type="button" class="update_btn" value="${questionTypeSid}">수정</button>
                <button type="button" class="update_cancel_btn" value="${questionTypeSid}">취소</button>
            `;
        questionTypeManageBtnBox.append(changeManageBtn);
    });

    $(document).on("click", ".update_cancel_btn", (event) => {
        const questionTypeSid = $(event.target).val();
        const thisTr = $(event.target).closest("tr");
        const questionTypeInput = thisTr.find("input");
        const questionTypeManageBtnBox = thisTr.find(".question_type_list_manage_btn_box");

        questionTypeInput.prop("readonly", true);
        questionTypeInput.css("outline", "none");
        questionTypeManageBtnBox.empty();
        const changeManageBtn =
            `
                <button type="button" class="update_set_btn" value="${questionTypeSid}">수정</button>
                <button type="button" class="delete_btn" value="${questionTypeSid}">삭제</button>
            `;
        questionTypeManageBtnBox.append(changeManageBtn);
    });

    $(document).on("click", ".update_btn", (event) => {
        const questionTypeSid = $(event.target).val();
        const thisTr = $(event.target).closest("tr");
        const questionTypeName = thisTr.find(".question_type_name").val();
        const formData = new FormData();

        formData.append("questionTypeSid", questionTypeSid);
        formData.append("questionTypeName", questionTypeName);

        const ajaxObj = {
            url: API_LIST.QUESTION_TYPE_UPDATE,
            method: "put",
            param: formData,
            contentType : false,
            processData : false,
            successFn: (resultResponse) => {
                if (resultResponse.success) {
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("성공", resultResponse.message, "success", thenFn);
                } else {
                    swalCall("수정 실패","문의 타입 수정 실패","error");
                    return;
                }
            }
        }
        ajaxCall(ajaxObj);
    });

    $(document).on("click", ".delete_btn", (event) => {
        const questionTypeSid = $(event.target).val();
        const questionTypeListCnt = $("#questionTypeListCnt").val();


        if (questionTypeListCnt == 1) {
            swalCall("경고", "한 개의 문의타입은 존재해야합니다!", "warning");
            return;
        }
        const thenFn = (result) => {
            if (result.isConfirmed) {
                const ajaxObj = {
                    url: API_LIST.QUESTION_TYPE_DELETE,
                    method: "delete",
                    param: {
                        questionTypeSid : questionTypeSid
                    },
                    successFn: (resultResponse) => {
                        if (resultResponse.success) {
                            const thenFn = () => {
                                location.reload();
                            }
                            swalCall("성공", resultResponse.message, "success", thenFn);
                        } else {
                            swalCall("실패", "문의타입 삭제 실패", "error");
                            return;
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("문의타입 삭제", "문의타입 삭제 시 등록된 모든 문의가 지워집니다.<br>해당 문의타입을 삭제하시겠습니까?", "question", thenFn, "예", true);
    });
});