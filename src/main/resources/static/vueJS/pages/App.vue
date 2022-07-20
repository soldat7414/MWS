<template>

    <v-layout>
        <v-app-bar height="22" color="grey-darken-3" elevation="23" >

                <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

                <v-toolbar-title>My work space</v-toolbar-title>

                <v-spacer></v-spacer>

                <v-btn variant="text" icon="mdi-magnify"></v-btn>

                <v-btn variant="text" icon="mdi-filter"></v-btn>

                <v-btn variant="text" icon="mdi-dots-vertical"></v-btn>
        </v-app-bar>
        <v-container>
            <v-row
                    no-gutters
                    class="flex-nowrap "
                    style="max-width: 100%"
            >
                <v-col
                        cols="4"
                        style="min-width: 100px; max-width: 100%;"
                        class="column_app"
                >
                    <v-sheet>
                        <h1>Організації</h1>
                        <organizations-list :organizations="organizations"/>
                    </v-sheet>
                </v-col>
                <v-col
                        cols="4"
                        style="min-width: 100px; max-width: 100%;"
                        class="column_app"
                >
                    <v-sheet>
                       <h1>Об'єкти</h1>
                        <object-list/>
                    </v-sheet>
                </v-col>
                <v-col
                        cols="4"
                        style="min-width: 100px; max-width: 100%;"
                        class="column_app"

                >
                    <v-sheet>
                        <h1>Люди</h1>
                        <persons-list :persons="persons"/>
                    </v-sheet>
                </v-col>
            </v-row>
        </v-container>
    </v-layout>


</template>


<script>
    import PersonsList from "../components/persons/PersonsList.vue"
    import OrganizationsList from "../components/organization/OrganizationsList.vue";
    import ObjectList from "../components/object/ObjectList.vue";
    import axios from 'axios'

    export default {
        components: {
            PersonsList,
            OrganizationsList,
            ObjectList
        },
        data() {
            return {
                persons: [],
                organizations: []
            }

        },
        methods: {
            async fetchPersons() {
                try {
                    const persons = await axios.get('http://localhost:9000/person')
                    console.log(persons.data)
                    this.persons = persons.data
                } catch (exception) {
                    alert(exception)
                }
            },
            async fetchOrganizations() {
                try {
                    const orgs = await axios.get('http://localhost:9000/organization')
                    console.log(orgs.data)
                    this.organizations = orgs.data
                } catch (exception) {
                    alert(exception)
                }
            },

        },
        mounted(){
            this.fetchPersons()
            this.fetchOrganizations()
        }

    }
</script>

<style>
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    font-family: Tahoma;
}
    .column_app{
        align-content: center;
    }
    h1{
        align-self: center;
    }
</style>