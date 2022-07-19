<template>
    <div>
        <object-form :objects="objects"/>
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
                objects: []
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
            }
        },
        mounted() {
            this.fetchObjects()
        }
    }
</script>

<style scoped>

</style>