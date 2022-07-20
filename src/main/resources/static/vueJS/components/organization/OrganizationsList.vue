<template>
    <div class="list_items">
        <add-button
        @click="showModal"
        />
        <modal-window v-model:show="modalVisible">
        <organization-form
                :organizations="organizations"
                v-model:hide="modalVisible"
        />
        </modal-window>
        <organization-row v-for="organization in organizations"
                          :organization="organization"
                          :organizations="organizations"
                          :key="organization.id"
                          :delete-org="deleteOrg"
        />
    </div>

</template>

<script>
    import OrganizationForm from "./OrganizationForm.vue";
    import OrganizationRow from "./OrganizationRow.vue";
    import axios from "axios";
    export default {
        components: {
            OrganizationRow,
            OrganizationForm
        },
        props: ['organizations'],
        // name: "OrganizationsList"
        data() {
          return{
              modalVisible: false
          }
        },
        methods: {
            deleteOrg(organization) {
                let result = this.deleteOrgRequest(organization.id)
                if (result) {
                    this.organizations.splice(this.organizations.indexOf(organization), 1);
                }
            },
            async deleteOrgRequest(id) {
                try {
                    await axios({
                        method: 'delete',
                        url: '/organization/' + id,
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
        }
    }
</script>

<style scoped>
    .list_items{
        padding: 0 10px;
    }
</style>