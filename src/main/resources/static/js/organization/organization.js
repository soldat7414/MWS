$(document).ready(function () {
    showOrganization();
});

function showOrganization() {
    $.get('/organization/' + id, {text: "Text"}, function (org) {
        console.log(org);
        //title
        $("#titleOrg").html(org.title);
        $("#author").html("Автор: " + org.author.name + " " + org.author.surname)
        $("#titleOrgModal").html(org.title);
        //address
        let address = org.address;
        console.log(address);
        if (address != null) {
            console.log(address.id + " in organization")
            localStorage.setItem('addrId', address.id);
            $("#country").html(address.country);
            $("#region").html(address.region);
            $("#district").html(address.district);
            $("#locality").html(address.locality);
            $("#street").html(address.street);
            $("#building").html(address.building);
            $("#noAddress").prop("hidden", true)
        } else {
            $("#address").prop("hidden", true)
        }
        //contacts
        let contacts = org.contacts;
        console.log(contacts);
        let contactsTag = ""
        if(contacts!=null){
            $.each(contacts, function (){
                contactsTag = contactsTag + "<li>" + this.species + " : " + this.contact + "</li>";
            })
            $("#contact").html(contactsTag);
        }


    });

}

function editOrg() {
    $.ajax({
        url: '/organization/' + id,
        dataType: 'json',
        type: 'PUT',
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

function orgAddAddress(addressId) {
    $.ajax({
        url: '/organization',
        dataType: 'json',
        type: 'PUT',
        // contentType: 'application/json',
        cache: false,
        data: {
            entityId: id,
            linkId: addressId,
            function: "ADD_ADDRESS",
        },
        success: function () {
            showOrganization()
        }
    })
}
