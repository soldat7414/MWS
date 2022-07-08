$(document).ready(function () {
    showOrganizations();
    clickDiv();
});

function showOrganizations() {
    $.get('/organization', {text: "Text"}, function (data) {
        console.log(data);
        let comp="";
        for (i = 0; i < data.length; i++) {
            comp = comp + "<div type='button' href='http://localhost:8080/mws/organization/"+ data[i].id + "' class=\"href card border-warning mb-3\" style=\"max-width: 99%;\">" +
                "<div class=\"card-header\" >" + data[i].contrParty + "</div>" +
                "<div class=\"card-body\">" +
                "<h5 class=\"card-title\">" + data[i].title + "</h5>" +
                "<p class=\"card-text\">" + "id = " + data[i].id + "</p>" +
                "<a class=\"btn btn-outline-warning\" href=\"mws/organization/"+ data[i].id + "\">Відкрити</a>"+
                "</div></div>"

        }
        $("#company").html(comp);
    });
}
function clickDiv(){
    $("div.href").click(function(){
        let attr = $(this).attr('href');
        if(attr !== 'undefined' && attr !== false){
        console.log("click")
        console.log(attr)
        window.location=$(this).attr('attr');
        }
    });
}


function saveOrg() {
    $.ajax({
        url: '/organization',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        cache: false,
        data: JSON.stringify({
            title: $("#title").val(),
            contrParty: $("#contrParty").val()
        }),
        success: function () {
            showOrganizations()
        }
    })
}
