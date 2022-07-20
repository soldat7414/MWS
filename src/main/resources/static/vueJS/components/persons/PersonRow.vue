<template>



    <div class="row_item">
        <div class="row_item__content">
            <i>({{ person.id }})</i> {{ person.firstName }}
        </div>
        <div class="row_item__buttons">
            <edit-button type="button" value="Редагувати" @click="edit"/>
            <delete-button type="button" value="X" @click="del"/>
        </div>
    </div>

    <modal-window v-model:show="modalVisible">
        <person-form :persons="persons"
                     :personAttr="person1"
                     :v-model:hide="modalVisible"/>
    </modal-window>
</template>

<script>
    import PersonForm from "./PersonForm.vue";
    export default {
        props: ['person', 'editPerson', 'deletePerson', 'persons'],
        components: {
            PersonForm
        },
        data(){
            return{
                person1: null,
                persons: this.persons,
                modalVisible: false
            }
        },
        methods: {
            edit() {
                this.person1 = this.person
                this.showModal()

            },
            del() {
                this.deletePerson(this.person)
            },
            showModal() {
                this.modalVisible = true
            }
        }
    }
</script>

<style scoped>
    .row_item {
        width: 100%;
        border-bottom: 1px solid gray;
        display: flex;
        flex-direction: row;
        padding: 10px;
    }

    .row_item__content{
        width: 90%;
        font-size: 20px;
        font-stretch: expanded;
    }
    .row_item__buttons{
        display: flex;
        flex-direction: row;

    }
</style>