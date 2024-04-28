
	let optionCount = 1;

    function addOption(){
        
       	let html =""
       		html += `
            <div class="options">
                옵션명<input type="text" id="options[${optionCount}].option_name" name="options[${optionCount}].option_name">
                내용<input type="text" id="options[${optionCount}].option_value" name="options[${optionCount}].option_value">
                <button type="button" onclick="removeOption(this)">옵션 제거</button>
            </div>
        `;
        $("#plusOptions").append(html);
        optionCount++;
        console.log(optionCount);
    }

    function removeOption(button) {
        $(button).parent().remove();
    }
    
    function backList(){
    	location.href="/room"
    }
    