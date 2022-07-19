<template>
    <div>{{organization.id}} {{ organization.title }}</div>

    <span>
        <edit-button type="button" @click="edit" value="edit"/>
        <input type="button" @click="del" value="X">
    </span>
    <organization-form
        :organizations="organizations"
        :organization-attr="org"
    />
</template>

<script>
    import OrganizationForm from "./OrganizationForm.vue"

    export default {
        components: {
            OrganizationForm
        },
        props: ['organization', 'organizations', 'deleteOrg'],
        data(){
            return{
                org: null
            }
        },
        methods: {
            edit(){
                this.org = this.organization
            },
            del(){
                this.deleteOrg(this.organization)
            }
        }
    }

</script>

<style scoped>

</style>