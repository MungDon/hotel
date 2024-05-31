
let optionCount = 1;
let useOptionCount = 1;
function addOption() {
	let html = ""
	html += `
            <div class="options">
                방 정보 옵션명<input type="text" id="options[${optionCount}].option_name" name="options[${optionCount}].option_name">
                내용<input type="text" id="options[${optionCount}].option_value" name="options[${optionCount}].option_value">
                <button type="button" onclick="removeOption(this)">옵션 제거</button>
            </div>
        `;
	$("#plusOptions").append(html);
	optionCount++;
	console.log(optionCount);
}

function addUseOption() {
	let html = ""
	html += `
            <div class="UseOptions">
                방이용 옵션명<input type="text" id="useOptions[${useOptionCount}].option_name" name="useOptions[${optionCount}].option_name">
                내용<textarea id="useOptions[${useOptionCount}].option_value" name="useOptions[${optionCount}].option_value"></textarea>
                <button type="button" onclick="removeOption(this)">옵션 제거</button>
            </div>
        `;
	$("#plusUseOptions").append(html);
	useOptionCount++;
	console.log(optionCount);
}


function removeOption(button) {
   	$(button).parent().remove();
  }	
	
function backList() {
	location.href = "/room"
}
