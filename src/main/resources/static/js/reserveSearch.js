

function reserveValidate(){
	const startDateInput = document.getElementById("start_date");
	const endDateInput = document.getElementById("end_date");
	if(startDateInput.value == "" || endDateInput.value == ""){
		alert("날짜를 입력해주세요");
		return false;
	};
	if(new Date(startDateInput.value) > new Date(endDateInput.value)){
		alert('끝날짜를 시작날짜보다 더 뒤로 설정해주세요.');
		return false;
			}
	return true;
}

	$(document).ready(() => {
		const today = new Date().toISOString().split('T')[0];
		const reserveDays = $(".search_reserve_days").val();
		document.getElementById("start_date").setAttribute('min', today);
		document.getElementById("end_date").setAttribute('min', today);
		if(!isNull(reserveDays)) {
			document.getElementById("result").value = reserveDays;
		} else {
			document.getElementById("result").value = '박';
		}
	});
	// 성인 인원 기본으로 1명 default 설정
    $(document).on("change",".search_date",() => {
	    const startDate = $("#start_date").val();
        const endDate = $("#end_date").val();
        const start = new Date(startDate);
        const end = new Date(endDate);
		if(isNull(startDate)||isNull(endDate)){
			return;
		}
        const $adultCntInput = $("#adult_cnt");
        if ($adultCntInput.val() === "0") {
            $adultCntInput.val(1);
        }

            
        const differenceInTime = end.getTime() - start.getTime();
        const differenceInDays = differenceInTime / (1000 * 3600 * 24);
		const reserveDays = Math.ceil(differenceInDays);
        if(isNaN(differenceInDays)){
			return;
		}else{
        	document.getElementById("result").value = `${reserveDays} 박`;
        }
    });