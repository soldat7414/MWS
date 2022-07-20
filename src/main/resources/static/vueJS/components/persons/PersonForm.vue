<template>
    <div class="form_item">
        <div>
            <text-input type="text" v-model="firstName" id="firstName" placeholder="Ім\'я"/>
            <text-input type="text" v-model="lastName" id="lastName" placeholder="Побатькові"/>
            <text-input type="text" v-model="surname" id="surname" placeholder="Фамілія"/>
            <text-input type="text" v-model="position" id="position" placeholder="Посада"/>
        </div>

        <save-button class="form_item__btn" type="button" @click="save"/>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        props: ['persons', 'personAttr'],
        data() {
            return {
                firstName: '',
                lastName: '',
                surname: '',
                position: '',
                id: ''
            }
        },
        watch: {
            personAttr(newVal, oldVal) {
                this.firstName = newVal.firstName;
                this.lastName = newVal.lastName;
                this.surname = newVal.surname;
                this.position = newVal.position;
                this.id = newVal.id;
            }
        },
        methods: {
            getIndex(list, id) {
                for (let i = 0; i < list.length; i++) {
                    if (list[i].id === id) {
                        return i;
                    }
                }
                return -1;
            },
            save() {
                let person = {
                    firstName: this.firstName,
                    lastName: this.lastName,
                    surname: this.surname,
                    position: this.position
                }
                if (this.id) {
                   this.editPerson(person, this.id)
                } else {
                    this.savePerson(person)
                }
                this.$emit('update:hide', false)
            },
            async savePerson(person) {
                try {
                    await axios({
                        method: 'post',
                        url: '/person',
                        data: {
                            firstName: person.firstName,
                            lastName: person.lastName,
                            surname: person.surname,
                            position: person.position
                        }
                    }).then(response =>  {
                        let d = response.data
                        this.persons.push(d);
                        console.log(this.persons)
                        this.firstName = '';
                        this.lastName = '';
                        this.surname = '';
                        this.position = '';
                        console.log(person)
                        return person
                    })
                } catch (e) {
                    console.log(e)
                }

            },
            async editPerson(person, id) {
                    await axios({
                        method: 'put',
                        url: '/person/' + id,
                        data: {
                            firstName: person.firstName,
                            lastName: person.lastName,
                            surname: person.surname,
                            position: person.position
                        }
                    }).then(response => {
                        let d =response.data
                        console.log(d)
                        let index = this.getIndex(this.persons, this.id);
                        this.persons.splice(index, 1, d);
                        this.firstName = '';
                        this.lastName = '';
                        this.surname = '';
                        this.position = '';
                        this.id = '';
                    }).catch (e => console.log(e))
            }
        }
    }
</script>

<style scoped>
    .form_item {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }

    .form_item__btn {
        align-self: end;
    }
</style>