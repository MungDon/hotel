$(function () {
    $(".banner_add_btn").click(() => {
        location.href = PAGE_LIST.BANNER_ADD_FORM;
    });

    $(document).on("click", ".update_set_btn", (event) => {
        const banner_sid = $(event.target).val();
        const thisTr = $(event.target).closest("tr");
        const bannerInput = thisTr.find("input");
        const bannerManageBtnBox = thisTr.find(".banner_list_manage_btn_box");
        const fileInput = thisTr.find(".banner_img_input");

        bannerInput.prop("readonly", false);
        bannerInput.css("outline", "1px solid black");
        bannerManageBtnBox.empty();
        fileInput.css("display", "block");
        const changeManageBtn =
            `
                <button type="button" class="update_btn" value="${banner_sid}">수정</button>
                <button type="button" class="update_cancel_btn" value="${banner_sid}">취소</button>
            `;
        bannerManageBtnBox.append(changeManageBtn);
    });

    $(document).on("click", ".update_cancel_btn", (event) => {
        const banner_sid = $(event.target).val();
        const thisTr = $(event.target).closest("tr");
        const bannerInput = thisTr.find("input");
        const bannerManageBtnBox = thisTr.find(".banner_list_manage_btn_box");
        const fileInput = thisTr.find(".banner_img_input");

        bannerInput.prop("readonly", true);
        bannerInput.css("outline", "none");
        fileInput.css("display", "none");
        bannerManageBtnBox.empty();
        const changeManageBtn =
            `
                <button type="button" class="update_set_btn" value="${banner_sid}">수정</button>
                <button type="button" class="delete_btn" value="${banner_sid}">삭제</button>
            `;
        bannerManageBtnBox.append(changeManageBtn);
    });

    $(document).on("click", ".update_btn", (event) => {
        const banner_sid = $(event.target).val();
        const thisTr = $(event.target).closest("tr");
        const bannerName = thisTr.find(".banner_name").val();
        const bannerUrl = thisTr.find(".banner_url").val();
        const bannerNewImg = thisTr.find(".banner_img_input")[0].files[0];
        const currentImg = thisTr.find(".currentImg").val();
        const formData = new FormData();

        formData.append("banner_sid", banner_sid);
        formData.append("banner_name", bannerName);
        formData.append("banner_url", bannerUrl);
        formData.append("current_img", currentImg);

        if (!isNull(bannerNewImg)) {
            formData.append("banner_img", bannerNewImg);
        }
        const ajaxObj = {
            url: API_LIST.BANNER_UPDATE,
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
                    swalCall("수정 실패","배너 수정 실패","error");
                    return;
                }
            }
        }
        ajaxCall(ajaxObj);
    });

    $(document).on("click", ".delete_btn", (event) => {
        const bannerSid = $(event.target).val();
        const bannerListCnt = $("#bannerListCnt").val();
        const thisTr = $(event.target).closest("tr");
        const currentImg = thisTr.find(".currentImg").val();

        const deleteDataObj = {
            banner_sid: bannerSid,
            current_img: currentImg
        }

        if (bannerListCnt == 2) {
            swalCall("경고", "두 개의 배너는 존재해야합니다!", "warning");
            return;
        }
        const thenFn = (result) => {
            if (result.isConfirmed) {
                const ajaxObj = {
                    url: API_LIST.DELETE_BANNER,
                    method: "delete",
                    param: deleteDataObj,
                    successFn: (resultResponse) => {
                        if (resultResponse.success) {
                            const thenFn = () => {
                                location.reload();
                            }
                            swalCall("성공", resultResponse.message, "success", thenFn);
                        } else {
                            swalCall("실패", "배너 삭제 실패", "error");
                            return;
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("배너 삭제", "해당 배너를 삭제하시겠습니까?", "question", thenFn, "예", true);
    });
});