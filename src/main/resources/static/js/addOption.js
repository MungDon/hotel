
let optionCount = 1;
let useOptionCount = 1;
function addOption() {
	let html = ""
	html += `
            <div class="options">
                옵션명&nbsp;<input type="text" class="o1" id="options[${optionCount}].option_name" name="options[${optionCount}].option_name">&nbsp;
                내용&nbsp;<input type="text" class="o1" id="options[${optionCount}].option_value" name="options[${optionCount}].option_value">&nbsp;
                <button type="button" class="addoptionBtn" onclick="removeOption(this)">옵션 제거</button>
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
                옵션명&nbsp;<input type="text" class="o2 id="useOptions[${useOptionCount}].option_name" name="useOptions[${optionCount}].option_name">&nbsp;
                내용&nbsp;<textarea  class="o2 id="useOptions[${useOptionCount}].option_value" name="useOptions[${optionCount}].option_value"></textarea>&nbsp;
                <button type="button" class="addoptionBtn" onclick="removeOption(this)">옵션 제거</button>
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

  function validateForm() {
            const optionNames = document.querySelectorAll('input[name$=".option_name"]');
            const requiredOptions = ['객실크기', '침대타입'];
            const enteredOptions = Array.from(optionNames).map(input => input.value.trim());
			const startDate = document.getElementById("startDate").value;
			const endDate = document.getElementById("endDate").value;
            for (const requiredOption of requiredOptions) {
                if (!enteredOptions.includes(requiredOption)) {
                    alert(`"${requiredOption}" 옵션을 입력해주세요.`);
                    return false;
                }
            }
            if(new Date(startDate) > new Date(endDate)){
				alert('끝날짜가 시작날짜보다 빠릅니다.');
				return false;
			}
            return true;
        }
  
