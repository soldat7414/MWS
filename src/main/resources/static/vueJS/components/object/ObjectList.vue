<template>
    <div class="list_items">
        <add-button
        @click="showModal"
        />
        <modal-window v-model:show="modalVisible">
            <object-form :objects="objects"
                         v-model:hide="modalVisible"/>
        </modal-window>

        <object-row v-for="object in objects"
                    :object="object"
                    :delete-object="deleteObject"
                    :objects="objects"
                    :key="object.id"

        />
    </div>
</template>

<script>
    import  axios from "axios";
    import ObjectRow from "./ObjectRow.vue";
    import ObjectForm from "./ObjectForm.vue";
    import Utils from "../utils/Utils.vue";

    export default {

        components: {
            ObjectRow,
            Utils,
            ObjectForm
        },
        data() {
            return{
                objects: [],
                modalVisible: false
            }
        },
        methods: {
            async fetchObjects(){
                try{
                    const objs = await axios.get('http://localhost:9000/object')
                    this.objects = objs.data
                }catch (e) {
                    alert(e)
                }
            },
            async deleteObject(object){
                const requestDel = await axios.delete('http://localhost:9000/object/'+object.id)
                if(requestDel){
                    this.objects.splice(Utils.getIndex(this.objects, object.id), 1)

                }
            },
            showModal(){
                this.modalVisible = true
            }
        },
        mounted() {
            this.fetchObjects()
        },

    }
</script>

<style scoped>
.list_items{
    padding: 0 10px;
}
</style>