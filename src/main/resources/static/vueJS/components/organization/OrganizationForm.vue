<template>
    <div>
        <input type="text" v-model="title" id="title" placeholder="Назва">
        <input type="text" v-model="contrParty" id="contrParty" placeholder="Сторона відносин">
        <input type="button" @click="save" value="Зберегти">
    </div>
</template>

<script>
    import Utils from "../utils/Utils.vue";
    import axios from "axios";
    export default {
        components: {
            Utils
        },
        props: ['organizations', 'organizationAttr', 'hideForm'],
        data() {
            return {
                title: '',
                contrParty: '',
                id: ''
            }
        },
        watch: {
            organizationAttr(newVal, oldVal){
                this.title = newVal.title;
                this.contrParty = newVal.contrParty;
                this.id = newVal.id;

            }
        },
        methods: {
            save() {
                let organization = {
                    title: this.title,
                    contrParty: this.contrParty,
                }
                if (this.id) {
                    this.editOrg(organization, this.id)
                } else {
                    this.saveOrg(organization)
                }
                if(this.hideForm){
                    this.hideForm()
                }
            },
            async saveOrg(organization) {
                try {
                    await axios({
                        method: 'post',
                        url: '/organization',
                        data: {
                            title: organization.title,
                            contrParty: organization.contrParty,
                        }
                    }).then(response =>  {
                        let d = response.data
                        this.organizations.push(d);
                        console.log(this.organizations)
                        this.title = '';
                        this.contrParty = '';
                    })
                } catch (e) {
                    console.log(e)
                }
            },
            async editOrg(organization, id) {
                await axios({
                    method: 'put',
                    url: '/organization/' + id,
                    data: {
                        title: organization.title,
                        contrParty: organization.contrParty,
                    }
                }).then(response => {
                    let d =response.data
                    console.log(d)
                    let index = Utils.getIndex(this.organizations, this.id);
                    this.organizations.splice(index, 1, d);
                    this.title = '';
                    this.contrParty = '';
                    this.id = '';
                }).catch (e => console.log(e))
            }
        }
    }
</script>

<style scoped>

</style>