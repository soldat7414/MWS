
import { createApp } from 'vue'
import VueResource from 'vue-resource'
import App from "./pages/App.vue";

// App.use(VueResource)
const vue = createApp(App, {
    // VueResource: VueResource,
    render: a => a(App)
}).mount('#app')


// app.component(
//     App,{
//         el: '#app',
//         render: a => a(App)
//     }
// )
// new Vue({
//     el: '#app',
//     render: a => a(App)
// })
/*
function getIndex(list, id){
    for(let i = 0; i < list.length; i++){
        if(list[i].id === id){
            return i;
        }
    }
    return -1;
}

let personApi = Vue.resource('/person{/id}');

Vue.component('person-form', {
    props: ['persons', 'personAttr'],
    data: function () {
        return {
            firstName: '',
            lastName: '',
            surname: '',
            position: '',
            id: ''
        }
    },
    watch: {
        personAttr: function (newVal, oldVal) {
            this.firstName = newVal.firstName;
            this.lastName = newVal.lastName;
            this.surname = newVal.surname;
            this.position = newVal.position;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" v-model="firstName" id="firstName" placeholder="Ім\'я">' +
        '<input type="text" v-model="lastName" id="lastName" placeholder="Побатькові">' +
        '<input type="text" v-model="surname" id="surname" placeholder="Фамілія">' +
        '<input type="text" v-model="position" id="position" placeholder="Посада">' +
        '<input type="button" @click="save" value="Save"> ' +
        '</div>',
    methods: {
        save: function () {
            let person = {
                firstName: this.firstName,
                lastName: this.lastName,
                surname: this.surname,
                position: this.position
            };

            if (this.id) {
                personApi.update({id: this.id}, person).then(result =>
                    result.json().then(data => {
                        let index = getIndex(this.persons, this.id);
                        this.persons.splice(index, 1, data);
                        this.firstName = '';
                        this.lastName = '';
                        this.surname = '';
                        this.position = '';
                        this.id = '';
                    })
                )
            } else {
                personApi.save({}, person).then(result =>
                    result.json().then(data => {
                        this.persons.push(data);
                        this.firstName = '';
                        this.lastName = '';
                        this.surname = '';
                        this.position = '';
                    })
                )
            }
        }
    }
})

Vue.component('person-row', {
    props: ['person', 'editMethod', 'persons'],
    template: '<div>' +
        '<i>({{ person.id }})</i> {{ person.firstName }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Редагувати" @click="edit"/>' +
        '<input type="button" value="X" @click="del"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.person);

        },
        del: function () {
            personApi.remove({id: this.person.id}).then(result => {
                if(result.ok){
                    this.persons.splice(this.persons.indexOf(this.person), 1);
                }
            })
        }
    }
});

Vue.component('persons-list', {
    props: ['persons'],
    data: function () {
        return {
            person: null
        }
    },
    template: '<div style="position: relative; width: 300px">' +
        '<person-form :persons="persons" :personAttr="person" />' +
        '<person-row v-for="person in persons" :key="person.id" :person="person"' +
        ':editMethod="editMethod" :persons="persons"/>' +
        '</div>',
    created: function () {
        personApi.get().then(result =>
            result.json().then(data =>
                data.forEach(person => this.persons.push(person))
            )
        )
    },
    methods: {
        editMethod: function (person) {
            this.person = person;
        }
    }
})

let app = new Vue({
    el: '#app',
    template: '<persons-list :persons="persons"/>',
    data: {
        persons: []
    }
})
*/
