

let addrId = localStorage.getItem('addrId')
console.log(addrId + " in  address")

function editAddress() {
    let form = document.forms["editAddressForm"];
    let fd = new FormData(form);
    let data = {};
    for (let [key, prop] of fd) {
        data[key] = prop;
    }
    data = JSON.stringify(data, null, 2);

    console.log(data);

    $.ajax({
        url: '/address/'+ addrId,
        dataType: 'json',
        type: 'PUT',
        contentType: 'application/json',
        cache: false,
        data: data,
        success: function () {
            showOrganization()
        }

    })
}

function addAddress() {
    let form = document.forms["addAddressForm"];
    let fd = new FormData(form);
    let data = {};
    for (let [key, prop] of fd) {
        data[key] = prop;
    }
    data = JSON.stringify(data, null, 2);

    console.log(data);
    $.ajax({
        url: '/address',
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
        success:
            function (addressId) {
            orgAddAddress(addressId)
        }
    })
}

