<template>
    <div>
        <organization-form
                :organizations="organizations"
        />
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

            }
        }
    }
</script>

<style scoped>

</style>