$(document).ready(function () {
    showDocumentsByOrg();
    showDocuments();
    showDocumentById();
});

function showDocuments() {
    $.get('/document', {text: "Text"}, function (data) {
        console.log(data);
        showEnyDocs(data, "documents")
        // let document = "";
        // for (i = 0; i < data.length; i++) {
        //     document = document + "<div class=\"card border-dark mb-3\" style=\"max-width: 99%;\">" +
        //         "<div class=\"card-header\" >" + data[i].whatDocument + "</div>" +
        //         "<div class=\"card-body\">" +
        //         "<h5 class=\"card-title\">" + data[i].title + "</h5>" +
        //         "<h5 class=\"card-title\">" + data[i].description + "</h5>" +
        //         "<a class=\"card-title\"> href=\"" + data[i].item + "\"</a>" +
        //
        //         "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +
        //         "</div></div>"
        //
        // }
        // $("#documents").html(document);
    });
}

function showDocumentsByOrg() {
    $.get('/document/by/'+id, {text: "Text", function: "ORGANIZATION"}, function (data) {
        console.log(data);
        showEnyDocs(data, "license")

        // let document = "";
        // for (i = 0; i < data.length; i++) {
        //     document = "<div class=\"card border-warning text-warning mb-3 \" style=\"max-width: 99%; margin:0;\">" +
        //         "<div class=\"modal-header\" >" + data[i].whatDocument +
        //         "<div>" +
        //         "<a type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
        //         "href=\"../document/" + data[i].id + "\">&#9999</a>"+
        //         "<button type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
        //         "onclick = \"deleteOrg(" + data[i].id + ")\">&#128465</button>"+
        //         "</div>" +"</div>" +
        //         "<div class=\"card-body\">" +
        //         "<h5 class=\"card-title\">" + data[i].title + "</h5>" +
        //         "<h5 class=\"card-title\">" + data[i].description + "</h5>" +
        //         "<a class=\"card-title\"> href=\"" + data[i].item + "\"</a>" +
        //
        //         "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +
        //
        //         "</div>"
        //     $("#license").append(document)
        // }
    });
}
function showEnyDocs(data, tegId) {
    let document = "";
    for (i = 0; i < data.length; i++) {
        document = "<div class=\"card border-warning text-warning mb-3 \" style=\"max-width: 99%; margin:0;\">" +
            "<div class=\"modal-header\" >" + data[i].whatDocument +
            "<div>" +
            "<a type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
            "href=\"../document/" + data[i].id + "\">&#9999</a>"+
            "<button type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
            "onclick = \"deleteOrg(" + data[i].id + ")\">&#128465</button>"+
            "</div>" +"</div>" +
            "<div class=\"card-body\">" +
            "<h5 class=\"card-title\">" + data[i].title + "</h5>" +
            "<h5 class=\"card-title\">" + data[i].description + "</h5>" +
            "<a class=\"card-title\"> href=\"" + data[i].item + "\"</a>" +

            "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +

            "</div>"
        $("#"+tegId).append(document)
        // $("#license").and("<div th:insert=\"fragments/modal/add/addFile::addFile\">document</div>")
    }
}


function removeRowsFromDocument() {
    $("#license").empty();
}

function saveDocument() {
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

function deleteOrg(idOrg) {
    // alert(idOrg)
    $.ajax({
        url: '/document/' + idOrg,
        dataType: 'json',
        type: 'DELETE',
        contentType: 'application/json',
        cache: false,
        success: function () {
            removeRowsFromDocument()
            showDocumentsByOrg();
            showDocuments();
        }
    })
}

function showDocumentById() {



}




