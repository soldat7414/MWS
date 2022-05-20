$(document).ready(function () {
    showContactModal();
});

function addContact() {
    let form = document.forms["addContactForm"];
    let fd = new FormData(form);
    let data = {};
    for (let [key, prop] of fd) {
        data[key] = prop;
    }
    data = JSON.stringify(data, null, 2);
    console.log(data);

    $.ajax({
        url: '/contacts',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        cache: false,
        data: data,
        /*data: JSON.stringify({
            //id: id,
            country: $("#country").val(),
            region: $("#region").val(),
            district: $("#district").val(),
            locality: $("#locality").val(),
            street: $("#street").val(),
            building: $("#building").val(),
        }),*/
        success: function (contactId) {
            bindContact(contactId)
        }
    })
}
function bindContact(contactId) {

    $.ajax({
        url: '/contacts',
        dataType: 'json',
        type: 'PUT',
        // contentType: 'application/json',
        cache: false,
        data: {
            entityId: contactId,
            linkId: id,
            function: "ADD_ORGANIZATION",
        },
        success: function () {
            showOrganization()
        }
    })

}
function editContact() {
    let form = document.forms["editContactForm"];
    let fd = new FormData(form);
    let data = {};
    for (let [key, prop] of fd) {
        data[key] = prop;
    }
    data = JSON.stringify(data, null, 2);
    console.log(data);
    contactId = $("#contactId").val();
    console.log(contactId)
    $.ajax({
        url: '/contacts/'+contactId,
        dataType: 'json',
        type: 'PUT',
        contentType: 'application/json',
        cache: false,
        data: data,
        /*data: JSON.stringify({
            //id: id,
            country: $("#country").val(),
            region: $("#region").val(),
            district: $("#district").val(),
            locality: $("#locality").val(),
            street: $("#street").val(),
            building: $("#building").val(),
        }),*/
        success: function () {
            showOrganization()
        }
    })
}

function showContactModal() {
    $.ajax({
        url: '/contacts/org/'+id,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        cache: false,
        success: function (contacts) {
            let contactsTag = ""
            if(contacts!=null){
                $.each(contacts, function (){
                    contactsTag = contactsTag + "<li>" + this.id + this.species + " : " + this.contact + "</li>";
                })
                $("#contactModal").html(contactsTag);
            }
        }
    })


}

