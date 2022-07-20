<template>
    <div class="row_item">
        <div class="row_item__content"> {{object.id}} {{object.title}}</div>
        <div class="row_item__buttons">
            <edit-button @click="edit"/>
            <delete-button type="button" @click="dlt" value="X"/>
        </div>
    </div>


    <modal-window v-model:show="modalVisible">
        <object-form :objects="objects"
                     :object-attr="objectAttr"
                     :v-model:hide="modalVisible"/>
    </modal-window>
</template>

<script>
    import ObjectForm from "./ObjectForm.vue";

    export default {
        components: {
            ObjectForm
        },
        data() {
            return {
                objectAttr: null,
                modalVisible: false
            }
        },
        props: [
            'object', 'deleteObject', 'objects'
        ],
        methods: {
            edit() {
                this.objectAttr = this.object
                this.showModal()

            },
            dlt() {
                this.deleteObject(this.object)
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