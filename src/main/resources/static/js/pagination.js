$(function () {
    const existPrevPage = $(".existPrevPage").val();
    const existNextPage = $(".existNextPage").val();
    const startPage = $(".startPage").val();
    const endPage = $(".endPage").val();
    const totalPageCount = $(".totalPageCount").val();
    const totalRecordCount = $(".totalRecordCount").val();
    const page = $(".page").val();
    const recordSize = $(".recordSize").val();
    // 페이지 번호를 계산하는 함수
    const calculateItemNumber = (currentPage, index) => {
        return totalRecordCount - ((currentPage - 1) * recordSize) - index;
    };
    let pageHTML = '';
    const createPagination = () => {
        if (existPrevPage) {
            pageHTML += `
                <button type="button" class="first_page_btn all_page_btn" value="1">첫페이지</button>
                <button type="button" class="prev_btn all_page_btn" value="${startPage - 1}">이전 페이지</button>
            `;
        }
        for (let i = startPage; i <= endPage; i++) {
            if (i != page) {
                pageHTML += `<button type="button" class="page_num all_page_btn" value="${i}">${i}</button>`;
            }
            if (i == page) {
                pageHTML +=  `<span class="target_num">${i}</span>`;
            }
        }

        if (existNextPage) {
            pageHTML += `
                <button type="button" class="next_page_btn all_page_btn" value="${endPage + 1}">다음 페이지</button>
                <button type="button" class="end_page_btn all_page_btn" value="${totalPageCount}">마지막 페이지</button>
            `;
        }
        $(".pagination").append(pageHTML);

        $(".question_manage_list_tr_2").each(function(index) {
            const itemNumber = calculateItemNumber(page, index);
            $(this).find(".question_manage_list_td_1").text(itemNumber);
        });
    }
    createPagination();
    $(document).on("click", ".all_page_btn", (event) => {
        let pageData = $(event.target).val();
        if (isNull(pageData)) {
            pageData = 1;
        }
        const pageParams = {
            page: pageData,
            recodeSize: 10,
            pageSize: 10
        }
        location.href = location.pathname + '?' + new URLSearchParams(pageParams);
    });
});