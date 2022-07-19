<template>
    <div> {{object.id}} {{object.title}}</div>
    <span>
        <edit-button @click="edit"/>
        <input type="button" @click="dlt" value="X">
    </span>
    <object-form :objects="objects"
                 :object-attr="objectAttr"/>
</template>

<script>
    import ObjectForm from "./ObjectForm.vue";
    // import EditButton from "../UI/EditButton.vue";

    export default {
        components: {
            // EditButton,
            ObjectForm
        },
        data() {
            return {
                objectAttr: null
            }
        },
        props: [
            'object', 'deleteObject', 'objects'
        ],
        methods: {
            edit() {
                this.objectAttr = this.object
            },
            dlt() {
                this.deleteObject(this.object)
            }
        }
    }
</script>

<style scoped>

</style>