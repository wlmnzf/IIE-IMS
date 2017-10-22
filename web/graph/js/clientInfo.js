_BASE_PATH=""
$(document).ready(function(){
    _BASE_PATH=$("#basepath").val();
    projectfileoptions ={
        showUpload : false,
            showRemove: false,
            language: 'zh',
            allowedPreviewTypes : [ 'image' ],
            allowedFileExtensions : [ 'jpg', 'png'],
            maxFileSize : 2000,
            uploadUrl:_BASE_PATH+"/HeadUpload",
            maxFileCount:1,
            uploadAsync:true,
           showPreview:true,
           dropZoneEnabled:false,
           enctype:'multipart/form-data',

    };
// 文件上传框
    $('input[class=file-3]').each(function() {
        var imageurl = $(this).attr("value");

        if (imageurl) {
            var op = $.extend({
                initialPreview : [ // 预览图片的设置
                    "<img src='" + imageurl + "' class='file-preview-image'>", ]
            }, projectfileoptions);

            $(this).fileinput(op);
        } else {
            $(this).fileinput(projectfileoptions);
        }

        $(this).on("fileerror",function(){

        })

        $(this).on("fileupload",function(event,data){
            console.log(data);

        })

        $(this).on("filebatchuploadsuccess",function(event,data,previewid,index){
            console.log(data);

        })


    });



})