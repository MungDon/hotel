$(function(){
    const modalInnerElement = $(".innerElement");   // 모달 내용 요소
    const modal = $("#modalCon");                   // 모달 요소

    /*권한 유형 검색*/
    $(".role_type").change((event) => {
        const roleType = $(event.target).val();
        if(isNull(roleType)){
            location.href = removeQueryParam(location.href, "role");
            return;
        }
        location.href = updateQueryParam(location.href, "role",roleType);
    });
    /*답변 상태 검색*/
    $(".delete_status").change((event) => {
        const deleteStatus = $(event.target).val();
        if(isNull(deleteStatus)){
            location.href = removeQueryParam(location.href, "deleteYN");
            return;
        }
        location.href = updateQueryParam(location.href, "deleteYN",deleteStatus);
    });
    /*문의 상세보기 생성*/
    const createQuestionDetailModal = (event) => {
        const thisTr = $(event.target).closest("tr");
        const userSid = $(event.target).val();
        const userName = thisTr.find(".customer_manage_list_username").text();
        const userEmail = thisTr.find(".customer_manage_list_user_email").val();
        const deleteStatus = thisTr.find(".customer_manage_list_td_3").text();
        const role = thisTr.find(".customer_manage_list_td_4").text();
        const userDate = thisTr.find(".customer_manage_list_td_5").text();
        const empNumber = isNull(thisTr.find(".customer_manage_list_emp_number").val()) ? '-' : thisTr.find(".customer_manage_list_emp_number").val() ;

        modalInnerElement.empty();
        const customerDetailHTML = `
            <div class="customer_detail_top">
                <span class="customer_detail_type">${role}</span>
            </div>
            <div class="customer_detail_main">
                <div class="customer_detail_box">
                    <span class="customer_detail_username">유저명 : ${userName}</span>
                    <span class="customer_detail_user_email">${userEmail}</span>
                    <span class="delete_status">탈퇴여부 : ${deleteStatus}</span> 
                    <span class="emp_number">사원번호 : ${empNumber}</span> 
                    <span class="customer_date">가입/수정 일자 : ${userDate}</span> 
                </div>
                <div class="customer_detail_btn_box">
                    <button type="button" class="customer_delete_btn" value="${userSid}">강제탈퇴</button>
                    <button type="button" class="cancelBtn">취소</button>
                </div>
            </div>
        `;
        modalInnerElement.append(customerDetailHTML);
        if(role=='직원가입대기'){
            const detailBtnBox = $(".customer_detail_btn_box");
            detailBtnBox.empty();
            const updateBtnBoxHTML =
                `
                <button type="button" class="signup_approve_btn" value="${userSid}">가입승인</button>
                <button type="button" class="signup_reject_set_btn" value="${userSid}">가입거절</button>
                <button type="button" class="cancelBtn">취소</button>
                `;
            detailBtnBox.append(updateBtnBoxHTML);
        }
        openModal(modal);
    }
    $(".customer_manage_btn").click((event)=>{
        createQuestionDetailModal(event);
    });
    /*가입 승인*/
    $(document).on("click",".signup_approve_btn",(event)=>{
        const userSid = $(event.target).val();
        const decisionMessage = "approve";

        const empSignupObj = {
            userSid : userSid,
            decisionMessage : decisionMessage
        }

        const ajaxObj = {
            url : API_LIST.EMP_SIGNUP_DECIDE,
            method : "put",
            contentType : "application/json",
            param : JSON.stringify(empSignupObj),
            successFn : (resultResponse) => {
                if(resultResponse.success){
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("성공", resultResponse.message,"success", thenFn);
                }else{
                    const thenFn = () => {
                        location.reload();
                    }
                    swalCall("승인 작업 실패", "승인에 실패하였습니다","error", thenFn);
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