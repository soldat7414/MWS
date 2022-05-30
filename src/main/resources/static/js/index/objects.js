$(document).ready(function () {
    showObjects();
});

function showObjects() {
    $.get('/object', {text: "Text"}, function (data) {
        console.log(data);
        let object="";
        for (i = 0; i < data.length; i++) {
            object = object + "<div class=\"card border-warning mb-3\" style=\"max-width: 99%;\">" +
                "<div class=\"card-header\" >" + data[i].title + "</div>" +
                "<div class=\"card-body\">" +
                "<h7 class=\"card-title\">Початок будівництва: " + new Date(data[i].beginBuilding).toLocaleDateString() + "</h7><br>" +
                "<h7 class=\"card-title\">Строк будівництва: " + new Date(data[i].endBuilding).toLocaleDateString() + "</h7>" +
                "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +
                "</div></div>"

        }
        $("#object").html(object);
    });
}

function saveObject() {
    $.ajax({
        url: '/object',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        cache: false,
        data: JSON.stringify({
            title: $("#titleObj").val(),
            beginBuilding: $("#beginBuilding").val(),
            endBuilding: $("#endBuilding").val(),
        }),
        success: function () {
            showObjects();
        }
    })
}

