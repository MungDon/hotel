$(function(){
    $(".insertOption").click(() => {
        const bannerOptionHTML = `
           <div class="banner_add_box">
               <input type="file" name="banner_img" class="banner_img">
               <input type="text" name="banner_name" class="banner_name" placeholder="배너 명">
               <input type="url" name="banner_url" class="banner_url" placeholder="이미지에 들어갈 url">
               <button type="button" class="removeOptionBtn">-</button>
           </div>
        `
        $(".banner_add_con").append(bannerOptionHTML);
    });

    $(document).on("click", ".removeOptionBtn", (event) => {
        $(event.target).closest(".banner_add_box").remove();
    });

    $(".saveBtn").click(() => {
        const formData = new FormData();
        $(".banner_add_box").each((index, objDOM) => {
            const obj = $(objDOM);
            const banner_name = obj.find(".banner_name").val();
            const banner_url = obj.find(".banner_url").val();
            const banner_img = obj.find(".banner_img")[0].files[0];

            formData.append(`bannerAdd[${index}].banner_name`, banner_name);
            formData.append(`bannerAdd[${index}].banner_url`, banner_url);
            formData.append(`bannerAdd[${index}].banner_img`, banner_img);
        });
        const ajaxObj = {
            url: API_LIST.BANNER_ADD,
            method: "post",
            contentType: false,
            processData : false,
            param: formData,
            successFn: (resultResponse) => {
                if (resultResponse.success) {
                    const thenFn = () => {
                        location.href = PAGE_LIST.BANNER_LIST;
                    }
                    swalCall("성공", resultResponse.message, "success", thenFn);
                }
            }
        }
        ajaxCall(ajaxObj);
    });
    $(".listBtn").click(() => {
        location.href = PAGE_LIST.BANNER_LIST;
    });
});