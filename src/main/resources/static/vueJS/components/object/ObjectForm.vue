<template>
    <input type="text" v-model="title" id="title" placeholder="Назва компанії">
    <input type="button" @click="save" value="Зберегти">
</template>

<script>
    import axios from "axios"
    import Utils from "../utils/Utils.vue";

    export default {
        components: {
          Utils
        },
        props: ['objects','objectAttr'],
        data(){
            return{
                title: '',
                id: ''
            }
        },
        watch: {
            objectAttr(newVal, oldVal){
                this.title = newVal.title
                this.id = newVal.id
            }
        },
        methods: {
            save(){
                let object = {
                    title: this.title
                }
                if(this.id){
                    this.editObject(object)
                } else{
                    this.saveObject(object)

                }
                this.title = ''
            },
            async editObject(object){
                try{
                    await axios({
                        method: 'put',
                        url: '/object/' + this.id,
                        data: {
                            title: object.title
                        }
                    }).then(response =>{
                        this.objects.splice(
                            Utils.getIndex(this.objects, this.id), 1, response.data)
                    })
                } catch (e) {
                    alert(e)
                }
            },
            async saveObject(object){
                try {
                    await axios({
                        method: 'post',
                        url: '/object',
                        data: {
                            title: object.title
                        }
                    }).then(response =>{
                        this.objects.push(response.data)
                    })
                }catch (e) {
                    alert(e)
                }
            }
        }
    }
</script>

<style scoped>

</style>