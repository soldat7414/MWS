$(document).ready(function () {
    showDocuments();
});

function showDocuments() {
    $.get('/document', {text: "Text"}, function (data) {
        console.log(data);
        let document;
        for (i = 0; i < data.length; i++) {
            document = document + "<div class=\"card border-dark mb-3\" style=\"max-width: 99%;\">" +
                "<div class=\"card-header\" >" + data[i].docType + "</div>" +
                "<div class=\"card-body\">" +
                "<h5 class=\"card-title\">" + data[i].title + "</h5>" +
                "<h5 class=\"card-title\">" + data[i].description + "</h5>" +
                "<a class=\"card-title\"> href=\"" + data[i].item + "\"</a>" +

                "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +
                "</div></div>"

        }
        $("#documents").html(document);
    });
}

function saveDocument() {
    $.ajax({
        url: '/document',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        cache: false,
        data: JSON.stringify({
            docType: $("#doctype").val(),
            title: $("#titleDoc").val(),
            description: $("#descriptionDoc").val(),
        }),
        success: function () {
            showDocuments()
        }
    })
}
