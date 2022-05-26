// $(document).ready(function () {
//     showFiles();
// })

function uploadFile() {
    var $input = $("#item");
    var fd = new FormData;
    fd.append("file", $input.prop('files')[0]);
    fd.append("title", $("#titleFile").val());
    fd.append("fileType", $("#fileType").val());
    $.ajax({
        url: '/files',
        type: "POST",
        data: fd,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        // accept: '*/*',
    }).done(function (fileId) {
        if(fileId<0){
            alert("Файл не завантажено")
        }else{
            bindFileWithDoc(fileId);
        }


    })
}

function bindFileWithDoc(fileId) {
    $.ajax({
        url: '/file',
        dataType: 'json',
        type: 'PUT',
        cache: false,
        data: {
            entityId: fileId,
            linkId: docId,
            function: "ADD_DOCUMENT",
        },
        success: function () {
            // removeRowsFromDocument()
            // showDocumentsByOrg();
            // showDocuments();
            // showOrganization();
        }
    })
}

function showFilesByDocument(docId) {
    $.get('/file/by/' + docId, {text: "Text", function: "DOCUMENT"}, function (data) {
        console.log(data);
        showEnyFiles(data, "files");

    })
}

function showEnyFiles(data, tegId) {
    let file = "";
    for (i = 0; i < data.length; i++) {
        file = "<div class=\"card border-warning text-warning mb-3 \" style=\"max-width: 99%; margin:0;\">" +
            "<div class=\"modal-header\" >" + data[i].title +
            "<div>" +
            // "<a type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
            // "href=\"../document/" + data[i].id + "\">&#9999</a>"+
            "<button type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
            "onclick = \"deleteFile(" + data[i].id + ")\">&#128465</button>"+
            "</div>" +"</div>" +
            "<div class=\"card-body\">" +
            "<h5 class=\"card-title\">" + data[i].fileType + "</h5>" +
            "<h3 class=\"card-title\">Створено: " + data[i].description + "</h3>" +
            "< iframe src = \"https://docs.google.com/viewer?url=\""+ data[i].item +"\" > </ iframe >"+
            // "<a class=\"card-title\"> href=\"" +  + "\"</a>" +

            "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +

            "</div>"
        $("#"+tegId).append(file)
        // $("#license").and("<div th:insert=\"fragments/modal/add/addFile::addFile\">document</div>")
    }
}
