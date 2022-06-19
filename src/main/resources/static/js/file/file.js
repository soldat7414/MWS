// $(document).ready(function () {
//     showFiles();
// })

function uploadFile() {
    let $input = $("#item");
    let fd = new FormData;
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
            removeRowsFrom('files')
            showFilesByDocument(docId)

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
        var urlFile = "localhost:8080/files/" + data[i].fileType + "/" + data[i].item + "&embedded=true"
        console.log(urlFile)
        file = "<div class=\"card border-warning text-warning mb-3 \" style=\"max-width: 99%; margin:0;\">" +
            "<div class=\"modal-header\" >" + data[i].title +
            "<div>" +
            // "<a type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
            // "href=\"../document/" + data[i].id + "\">&#9999</a>"+
            "<button type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
            "onclick = \"deleteFile(" + data[i].id + ")\">&#128465</button>" +
            "</div>" + "</div>" +
            "<div class=\"card-body row\"><div class=\"col-6\">" +
            "<h5 class=\"card-title\">" + data[i].fileType + "</h5>" +
            "<h3 class=\"card-title\">Створено: " + data[i].description + "</h3></div><div class=\"col-6\">" +
            // "<iframe src = \"https://docs.google.com/viewer?url=\""+ data[i].item +"\" > </ iframe >"+

            "<iframe X-Frame-Options = DENY  width = '100% ' height =' 100% ' frameborder ='1' src = \"https://docs.google.com/viewer?url=http://"+urlFile+"\" > </ iframe >" +
            // "<iframe X-Frame-Options = DENY  width = '100% ' height =' 100% ' frameborder ='1' src = \"https://docs.google.com/viewer?url=http://localhost:8080/files/'data[i].fileType'/'data[i].title'&embedded=true\" > </ iframe >" +

        "<a href=\""+urlFile+"\" class=\"btn btn-bd-primary\" onclick=\"ga('send', 'event', 'Getting started', 'Download', 'Download source');\">Download source</a>" +
        "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +

        "</div></div>"
        $("#" + tegId).append(file)
        // $("#license").and("<div th:insert=\"fragments/modal/add/addFile::addFile\">document</div>")
    }
}

function deleteFile(fileId) {
    $.ajax({
        url: '/file/'+fileId,
        dataType: 'json',
        type: 'DELETE',
        cache: false,
        // data: {
        //     entityId: fileId,
        //     linkId: docId,
        //     function: "ADD_DOCUMENT",
        // },
        success: function () {
            $.getScript("../../js/utils/editDOM.js", function () {
                removeRowsFrom('files')
            });
            showFilesByDocument(docId)
        }
    })

}

