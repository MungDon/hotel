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
   	function snubmit(){
		   	const form = document.getElementById();
		   	
		   	// 이미지 태그들을 모두 가져옴(에디터에 추가한 모든 사진들)
		   	const imgTags = document.querySelectorAll('img');
			
            const ajaxRequests = [];

            imgTags.forEach(function(img) {
                var currentSrc = img.getAttribute('src');

                // 이미지가 base64로 인코딩되어 있는지 확인
                if (currentSrc.startsWith('data:image')) {

                    // base64 데이터 추출
                    const splitDataURI = currentSrc.split(',')

                    if (splitDataURI[0].indexOf('base64') >= 0){
                        const ajaxRequest = $.ajax({
                            type: 'post',
                            enctype: 'multipart/form-data',
                            url: '/file/uploadBase64',
                            data: splitDataURI[1],
                            processData: false,
                            contentType: false,
                            dataType: 'text',
                            async: false,
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
                return true;
            }
            // 모든 AJAX 요청이 완료된 후에 폼 제출
            $.when.apply($, ajaxRequests).done(function () {
                return true;
            }).fail(function () {
                return false;
            });
	   }