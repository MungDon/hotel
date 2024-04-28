function ajaxCall(url,method,param,successFn,errorFn){
        $.ajax({
            url: url,
            method: method,
            data: {param:param},
            success: function (data) {
                if(typeof successFn =="function"){
                    successFn(data);
                }
            },
            error: function () {
                if(typeof errorFn =="function"){
                    errorFn();
                }
            }
        });
    };
