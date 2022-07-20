<template>
    <div class="list_items">

        <add-button
                @click="showModal"
        />
        <modal-window v-model:show="modalVisible">
        <person-form
                :persons="persons"
                :personAttr="person"
                v-model:hide="modalVisible"/>
        </modal-window>

        <person-row v-for="person in persons"
                    :key="person.id"
                    :person="person"
                    :editPerson="editPerson"
                    :deletePerson="deletePerson"
                    :persons="persons"/>
    </div>
</template>

<script>
    import PersonRow from "./PersonRow.vue";
    import PersonForm from "./PersonForm.vue";
    import axios from 'axios'

    export default {
        props: ['persons'],
        components: {
            PersonRow,
            PersonForm
        },
        data() {
            return {
                person: null,
                modalVisible: false
            }
        },
        methods: {
            editPerson(person) {
                this.person = person;
            },
            deletePerson(person) {
                let result = this.deletePersonRequest(person.id)
                if (result) {
                    this.persons.splice(this.persons.indexOf(person), 1);
                }
            },
            async deletePersonRequest(id) {
                try {
                    await axios({
                        method: 'delete',
                        url: '/person/' + id,
                    }).then(function (response) {
                        return response.data
                    })
                } catch (exception) {
                    console.log(exception)
                }

            },
            showModal(){
                this.modalVisible = true
            }

        },

    }
</script>

<style scoped>
    .list_items{
        padding: 0 10px;
    }
</style>