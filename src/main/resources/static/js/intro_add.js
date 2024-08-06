$(function () {
    $(".addCancel").click(() => {
        const fileNames = [];
        const thenFn = (result) => {
            if (result.isConfirmed) {
                $('#editor img').each(function () {
                    const src = $(this).attr('src');

                    const url = new URL(src);
                    const params = new URLSearchParams(url.search);

                    const fileName = params.get('fileName');
                    fileNames.push(fileName);
                });
                const ajaxObj = {
                    url: API_LIST.INTRO_ADD_CANCEL,
                    method: "post",
                    contentType: 'application/json',
                    param: JSON.stringify(fileNames),
                    successFn: (resultResponse) => {
                        if (resultResponse.success) {
                            console.log(resultResponse.message);
                            const thenFn = () => {
                                location.href = PAGE_LIST.INTRO_LIST;
                            }
                            swalCall("성공","작성이 취소되었습니다", "success", thenFn);
                        }
                    }
                }
                ajaxCall(ajaxObj);
            } else {
                return;
            }
        }
        swalCall("경고", "취소하면 내용이 사라집니다. 취소하시겠습니까?", "question", thenFn);
    });
});
