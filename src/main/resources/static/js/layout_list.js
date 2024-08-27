$(function(){
    
    /*구조 등록하기 폼*/
    $(".layout_add_form").click(()=>{
        location.href = PAGE_LIST.HOTEL_ADD_FORM;
    });

    $(".layout_update_set").click(()=>{
        const layoutListBtnBox = $(".layout_list_btn_box");
        $(".floor_box").find("button").css("display","block");
        $(".room").find("select").prop("disabled", false);
        layoutListBtnBox.empty();

        const layoutUpdateBtnBoxHTML =
            `
                
            `;
    });
});