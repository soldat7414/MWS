$(document).ready(function () {
    console.log("it's works")
    showPeople();
})

function showPeople() {
    $.get('/person', {text: "Text"}, function (data) {
        console.log(data);
        showAllPersons(data, "people");

    })
}

function showAllPersons(people, tegId) {
    let person = "";

    for (let i = 0; i < people.length; i++) {
        let address = people[i].address;
        let contact = people[i].phoneNumbers;
        person = "<div class=\"card border-warning text-warning mb-3 \" style=\"max-width: 99%; margin:0;\">" +
            "<div class=\"modal-header\" ><h5>" + people[i].firstName + " " + people[i].surname +
            "</h5><div>" +
            "<button type=\"button\" class=\"btn-outline-warning btn btn-sm rounded-pill\" " +
            "onclick = \"deletePerson(" + people[i].id + ")\">&#128465</button>" +
            "</div>" + "</div>" +
            "<div class=\"card-body row\">" +
            "<div class=\"col-12\">" +
            "<h5 class=\"card-title\">Посада: " + people[i].position + "</h5>"+
            "<h5 id='address"+ people[i].id + "' class=\"card-title\">Адреса:</h5>"
           if(address !== null){
               person = person + "<p class=\"card-text\">" + address.country + ", " + address.region + " обл. " +
                   address.district + " район, н/п " + address.locality + ", вул. " +
                   address.street + " буд. " + address.building + "</p>"

           } else {
               addAddressToPerson(people[i].id)
           }
           person = person + "<div th:replace='fragments/modal/add/addAddress::addAddress'></div>"
            if(contact !== null){
                person = person + "<h5 class=\"card-title\">Контакти</h5>" +
                    "<ul>"
                $.each(contact, function () {
                    person = person + "<li>" + this.id + this.species + " : " + this.contact + "</li>";
                })
                person = person + "</ul>"
            }
        person = person + "</div></div></div>"
        $("#" + tegId).append(person)
        $('#saveAddress').attr('onclick', 'addAddressToPerson()')
        // $('#address'+people[i].id).find('#saveAddress').attr('onclick', 'addAddressToPerson()')
        // '+people[i].id+'
    }
}

function addPerson() {
    let form = document.forms["addPersonForm"];
    let fd = new FormData(form);
    let data = {};
    for (let [key, prop] of fd) {
        data[key] = prop;
    }
    data = JSON.stringify(data, null, 2);

    console.log(data);
    $.ajax({
        url: '/person',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        cache: false,
        data: data,
        success:
            function () {
                showPeople();
            }
    })

}

function addAddressToPerson1(personId) {
    $.get('../../js/person/addAddress.html', function(response) {
        console.log(response)


        $('#address'+personId).append(response);
    })
    // $('#saveAddress').attr(onclick, addAddressToPerson(personId))
}

function addAddressToPerson(personId) {
    $('#address'+personId).append("<button type=\"button\" class=\"btn btn-outline-warning ms-3\"\n" +
        "            data-bs-toggle=\"modal\" data-bs-target=\"#addAddress\">\n" +
        "       Додати\n" +
        "    </button>\n" +
        "\n" +
        "    <!-- Modal -->\n" +
        "    <div class=\"modal fade\" id=\"addAddress\" tabindex=\"-1\" aria-labelledby=\"addressModal\" aria-hidden=\"true\">\n" +
        "        <div class=\"modal-dialog\">\n" +
        "            <div class=\"modal-content\">\n" +
        "                <div class=\"modal-header\">\n" +
        "                    <h5 class=\"modal-title\" id=\"addressModal\"></h5>\n" +
        "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
        "                </div>\n" +
        "                <div class=\"modal-body\">\n" +
        "                    <form id=\"form\" name=\"addAddressForm\">\n" +
        "                        <div class=\"form-row align-items-center\">\n" +
        "                            <div class=\"col-auto\">\n" +
        "                                <label class=\"sr-only\" for=\"country\">Страна</label>\n" +
        "                                <input type=\"text\" class=\"form-control mb-2\" id=\"country\"\n" +
        "                                       name=\"country\" placeholder=\"Страна\">\n" +
        "                            </div>\n" +
        "                            <select class=\"form-select\" aria-label=\"Область\" id=\"region\">\n" +
        "                                <option selected>Оберіть область</option>\n" +
        "                                <option value=\"Харківська обл.\">Харківська обл.</option>\n" +
        "                                <option value=\"Полтавська обл.\">Полтавська обл.</option>\n" +
        "                                <option value=\"Київська обл.\">Київська обл.</option>\n" +
        "                                <option value=\"Сумська обл.\">Сумська обл.</option>\n" +
        "                                <option value=\"Одеська обл.\">Одеська обл.</option>\n" +
        "                            </select>\n" +
        "                            <div class=\"col-auto\">\n" +
        "                                <label class=\"sr-only\" for=\"district\">Район</label>\n" +
        "                                <input type=\"text\" class=\"form-control mb-2\" id=\"district\"\n" +
        "                                       name=\"district\" placeholder=\"Район\">\n" +
        "                            </div>\n" +
        "                            <div class=\"col-auto\">\n" +
        "                                <label class=\"sr-only\" for=\"locality\">Населений пункт</label>\n" +
        "                                <input type=\"text\" class=\"form-control mb-2\" id=\"locality\"\n" +
        "                                       name=\"locality\" placeholder=\"Населений пункт\">\n" +
        "                            </div>\n" +
        "                            <div class=\"col-auto\">\n" +
        "                                <label class=\"sr-only\" for=\"street\">Вулиця</label>\n" +
        "                                <input type=\"text\" class=\"form-control mb-2\" id=\"street\"\n" +
        "                                       name=\"street\" placeholder=\"Вулиця\">\n" +
        "                            </div>\n" +
        "                            <div class=\"col-auto\">\n" +
        "                                <label class=\"sr-only\" for=\"building\">Дом</label>\n" +
        "                                <input type=\"text\" class=\"form-control mb-2\" id=\"building\"\n" +
        "                                       name=\"building\" placeholder=\"Дом\">\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"modal-footer\">\n" +
        "                            <button type=\"reset\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Відмінити</button>\n" +
        "                            <button type=\"button\" name=\"send\" data-bs-dismiss=\"modal\" onclick=\"addAddress("+personId+")\"\n" +
        "                                    class=\"btn btn-primary\">Зберегти\n" +
        "                            </button>\n" +
        "                        </div>\n" +
        "                    </form>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>")

}