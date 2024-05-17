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
          document.getElementById("taskDetails").value = quill.root.innerHTML;
        });
        
    }
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
   	function snubmit(){
		   	const form = document.getElementById();
		   	
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
					const extension = extractExtension(splitDataURI[0]);
					// data:image/png;base64,~~이런식으로 구성되어있음
					// 따라서 0번 인덱스에 base64란 문자열이 있다는것은 base64 데이터라는것.
					// == 이미지인것을 의미, 이미지 일때만 비동기 요청을 보내 이미지 처리함
                    if (splitDataURI[0].indexOf('base64') >= 0){
						
                        const ajaxRequest = $.ajax({
                            type: 'post',
                            enctype: 'multipart/form-data',
                            url: '/file/uploadBase64',
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

                        ajaxRequests.push(ajaxRequest);

                    }
                }
            });

            if (ajaxRequests.length===0){
                form.onsubmit;
            }
            // 모든 AJAX 요청이 완료된 후에 폼 제출
            $.when.apply($, ajaxRequests).done(function () {
                form.onsubmit;
            }).fail(function () {
                alert("이미지 처리 실패");
            });
	   }