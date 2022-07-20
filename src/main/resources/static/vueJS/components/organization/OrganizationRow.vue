<template>
    <div class="row_item">
        <div class="row_item__content">{{organization.id}} {{ organization.title }}</div>
        <div class="row_item__buttons">
            <edit-button type="button" @click="edit" value="edit"/>
            <delete-button type="button" @click="del" value="X"/>
        </div>
    </div>


    <modal-window v-model:show="modalVisible">
    <organization-form
        :organizations="organizations"
        :organization-attr="org"
        :v-model:hide="modalVisible"
    />
    </modal-window>
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
                org: null,
                modalVisible: false
            }
        },
        methods: {
            edit(){
                this.org = this.organization
                this.showModal()
            },
            del(){
                this.deleteOrg(this.organization)
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