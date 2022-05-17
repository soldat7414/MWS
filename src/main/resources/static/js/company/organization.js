$(document).ready(function () {
    showOrganization();
});

function showOrganization() {

    $.get('/organization/' + id, {text: "Text"}, function (org) {
        console.log(org);
        // let comp;
        // let titleOrg = "<h1>" + org.contacts[0].contact + "</h1>"
        $("#titleOrg").html(org.title);
        // $("#test").html(titleOrg);
        // let countryOrg = "<h1>" + org.address.country + "</h1>"
        // $("#countryOrg").html(countryOrg);
        // let contrParty = "<h4>" + org.contrParty + "</h4>" + "<h4>" + org.id + "</h4>"
        // $("#contrParty").html(contrParty);
        // let address = document.createElement('h3');
        // let div = document.createElement('div')
        // div.innerHTML("<h1>" + org.contacts[0].contact+ "</h1>")
        // let addr =





            comp = comp + "<a class=\"link-info\" href=\"doc/ " + Number(org.id) + "\"><div class=\"card border-info mb-3\" style=\"max-width: 99%;\">" +
                "<div class=\"card-header\" >" + org.contrParty + "</div>" +
                "<div class=\"card-body\">" +
                "<h5 class=\"card-title\">" + org.title + "</h5>" +
                "<p class=\"card-text\">" + "id = " + org.id + "</p>" +
                "</div></div></a>"


        $("#company").html(comp);
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
            showOrganization()
        }
    })
}
