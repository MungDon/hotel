	// 에디터 옵션    
    let toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],
        [{ 'header': 1 }, { 'header': 2 }],
        [{ 'list': 'ordered'}, { 'list': 'bullet' }],

        [{ 'color': [] }, { 'background': [] }],
        ['image', 'link'],

    ];

	// 기본 에디터 설정
    function quilljsediterInit(){
        let option = {
            modules: {
                toolbar: toolbarOptions
            },
            theme: 'snow'
        };

        quill = new Quill('#editor', option);
        
        // 에디터의 내용에 변동이 있을시 바로 textarea에 반영
        quill.on('text-change', function() {
          document.getElementById("content").value = quill.root.innerHTML;
        });
        quill.getModule('toolbar').addHandler('image', function () {
            selectLocalImage();
        });
    }
    
    function selectLocalImage() {
        const fileInput = document.createElement('input');
        fileInput.setAttribute('type', 'file');
        fileInput.accept = "image/*";

        fileInput.click();

        fileInput.addEventListener("change", function () {  // change 이벤트로 input 값이 바뀌면 실행

            if ($(this).val() !== "") { // 파일이 있을때만.

                let ext = $(this).val().split(".").pop().toLowerCase();

                if ($.inArray(ext, ["gif", "jpg", "jpeg", "png", "bmp"]) == -1) {

                    alert("jpg, jpeg, png, bmp, gif 파일만 업로드 가능합니다.");
                    return;
                }


                let fileSize = this.files[0].size;

                let maxSize = 20 * 1024 * 1024;

                if (fileSize > maxSize) {

                    alert("업로드 가능한 최대 이미지 용량은 20MB입니다.");

                    return;

                }

                const formData = new FormData();
                const file = fileInput.files[0];
                formData.append('uploadFile', file);

                $.ajax({
                    type: 'post',
                    enctype: 'multipart/form-data',
                    url: '/hotel/file/upload',
                    data: formData,
                    processData: false,
                    contentType: false,
                    dataType: 'text',
                    success: function (data) {
                        const range = quill.getSelection();
                        quill.insertEmbed(range.index, 'image', "/file/display?fileName=" + data);

                    },
                    error: function (err) {
                        console.log('ERROR!! ::');
                        console.log(err);
                    }
                });

            }

        });
    }
    
    
    
    // 확장자 추출 함수
    function extractExtension(base64code){
		// 정규식 패턴
		const mimePattern = /^data:(image\/[^;]+);base64,/;
		
		const match = base64code.match(mimePattern);
		
		if(match){
			const mimeType = match[1];
			const extension = mimeType.split('/')[1];
			return extension;
		}
	};
	
   	function submit(){
		   	const form = document.getElementById("introForm");
		   	
		   	// 이미지 태그들을 모두 가져옴(에디터에 추가한 모든 사진들)
		   	const imgTags = document.querySelectorAll('img');
			
            const ajaxRequests = [];

            imgTags.forEach(function(img) {
				// 모든 이미지 태그의 src 를 가져옴
                let currentSrc = img.getAttribute('src');

                // 이미지가 base64로 인코딩되어 있는지 확인
                if (currentSrc.startsWith('data:image')) {

                    // base64 코드안에 ,기준으로 나눠 배열로 저장
                    const splitDataURI = currentSrc.split(',')
                    
                    // 확장자 추출 함수 호출하여 확장자 추출
					const extension = extractExtension(splitDataURI[0]);

					// data:image/png;base64,~~이런식으로 구성되어있음
					// 따라서 0번 인덱스에 base64란 문자열이 있다는것은 base64 데이터라는것.
					// == 이미지인것을 의미, 이미지 일때만 비동기 요청을 보내 이미지 처리함
                    if (splitDataURI[0].indexOf('base64') >= 0){
						
                        const ajaxRequest = $.ajax({
                            type: 'post',
                            enctype: 'multipart/form-data',
                            url: '/hotel/file/uploadBase64',
                            data: {
								base64 : splitDataURI[1],
                            	extension : extension	
                            },
                            processData: false,
                            contentType: false,
                            dataType: 'text',
                            async: false,// 모든 동작이 끝날때까지 코드가 흘러가지 않음
                            success: function (data) {
                                img.setAttribute('src', "/file/display?fileName=" + data);
                            },
                            error: function (err) {
                                console.log('ERROR!! ::');
                                console.log(err);
                            }
                        });
						// 모든 AJAX 요청을 배열에 담음
                        ajaxRequests.push(ajaxRequest);

                    }
                }
            });
			// ajaxRequests 이 배열이 0이라는것은 이미지가 없는 에디터라는것
            if (ajaxRequests.length===0){
                form.onsubmit();
            }
            // 모든 AJAX 요청이 완료된 후에 폼 제출
            //$.when은 AJAX 요청과 같은 Deferred 객체의 완료를 기다리는데에 사용
            //apply 이건 ajaxRequests 배열의 각요소를 when 에 인자로 전달하여 여러개의 AJAX 요청이 완료될때까지 기다림
            //done 는 모든 요청이 성공적으로 완료될시에 실행될 콜백 함수를 지정함
            $.when.apply($, ajaxRequests).done(function () {
                form.onsubmit();// 요청이 다 성공적으로 완료되면 서밋함
            }).fail(function () {
                alert("이미지 처리 실패");
            });
	   }