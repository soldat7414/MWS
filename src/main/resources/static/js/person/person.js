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
            "<div class=\"col-6\">" +
            "<h5 class=\"card-title\">Посада: " + people[i].position + "</h5>"
           if(address !== null){
               person = person +"<h5 class=\"card-title\">Адреса:</h5>" +
                   "<p class=\"card-text\">" + address.country + ", " + address.region + " обл. " +
                   address.district + " район, н/п " + address.locality + ", вул. " +
                   address.street + " буд. " + address.building + "</p>"

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