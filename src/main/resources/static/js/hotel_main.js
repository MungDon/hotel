$(function(){
    const modal = $("#modalCon");
    const modalInnerElement = $(".innerElement");
    $(".type_img").click((event)=>{
        const roomTypeElement = $(event.target).closest("td").find("input");
        const imgSrc = roomTypeElement.prev("img").attr("src");
        const typeName = roomTypeElement.filter(".type_name").val();
        const bedSize = roomTypeElement.filter(".bed_size").val();
        const roomSize = roomTypeElement.filter(".room_size").val();

        const modalInnerHTML =
            `
            <div class="type_detail_top">
                <span class="type_detail_type_name">${typeName}</span>
            </div>
            <img src=${imgSrc} class="type_detail_img">
            <div class="type_detail_info">
                <span class="room_size">객실 사이즈 | ${roomSize}㎡</span>
                <span class="type_detail_bed_size">침대 사이즈 | ${bedSize}</span>
            </div>
            `;

        modalInnerElement.append(modalInnerHTML);
        openModal(modal);
    });
});