<template>
    <div>
        <p>it's working</p>
        <persons-list :persons="persons"/>
    </div>
</template>

<script>
    import PersonsList from "../components/persons/PersonsList.vue"
    // import {Axios as axios} from "axios";
    import axios from 'axios'

    export default {
        components: {
            PersonsList
        },
        data() {
            return {
                persons: []
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
        },


        mounted() {
            // this.$resource('/person{/id}').get().then(result =>
            //     result.json().then(data =>
            //         data.forEach(person => this.persons.push(person))
            //     )
            // )
            this.fetchPersons()
        }


    }
</script>

<style scoped>
.main-app {

}
</style>