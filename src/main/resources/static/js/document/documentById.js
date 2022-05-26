$(document).ready(function () {
    showDocumentById();
    $.getScript("../../js/document/document.js", function () {
        showDocuments()
    });
    $.getScript("../../js/file/file.js", function () {
        showFilesByDocument(docId)
    });
});

function showDocumentById() {
    $.get('/document/' + docId, {text: "Text"}, function (data) {
        console.log(data);
        $("#titleDoc").html(data.title);
        $("#whatDocument").html(data.whatDocument);
        $("#idDoc").html(data.id);
        $("#descriptionDoc").html(data.description);
        let files = data.files;
        console.log(files);
        let document = "";
        for (i = 0; i < data.length; i++) {
            document = document + "<div class=\"card border-dark mb-3\" style=\"max-width: 99%;\">" +
                "<div class=\"card-header\" >" + data[i].whatDocument + "</div>" +
                "<div class=\"card-body\">" +
                "<h5 class=\"card-title\">" + data[i].title + "</h5>" +
                "<h5 class=\"card-title\">" + data[i].description + "</h5>" +
                "<a class=\"card-title\"> href=\"" + data[i].item + "\"</a>" +

                "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +
                "</div></div>"

        }
        // $("#files").html(document);
    });
}

function editDocument() {
    $.ajax({
        url: '/document',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        cache: false,
        data: JSON.stringify({
            whatDocument: $("#whatDocument").val(),
            title: $("#titleDoc").val(),
            description: $("#descriptionDoc").val(),
        }),
        success: function (docId) {
            bindDocumentWithOrg(docId)
        }
    })
}


function bindDocumentWithOrg(docId) {
    $.ajax({
        url: '/document',
        dataType: 'json',
        type: 'PUT',
        cache: false,
        data: {
            entityId: docId,
            linkId: id,
            function: "ADD_ORGANIZATION",
        },
        success: function () {
            removeRowsFromDocument()
            showDocumentsByOrg();
            // showDocuments();
            // showOrganization();
        }
    })
}





