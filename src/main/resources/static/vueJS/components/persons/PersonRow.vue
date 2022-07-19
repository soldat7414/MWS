<template>
    <v-card class="mx-auto"
            max-width="344">
        <v-card-title>
            <p>{{ person.surname }} {{ person.firstName }}
                {{ person.lastName }}</p>
            <v-btn
                    variant="outlined"
                    size="x-small"
                    icon
                    color="gray"
            >
                <v-icon><span class="material-icons">edit</span></v-icon>
            </v-btn>
        </v-card-title>

        <v-card-subtitle>
            {{ person.firstName }}<input type="button" value="X" @click="del"/>
        </v-card-subtitle>
        <v-card-actions>
            <v-btn
                    color="orange-lighten-2"
                    variant="text"
            >
                Explore
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn
                    :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
                    @click="edit"
            ></v-btn>
        </v-card-actions>

        <v-expand-transition>
            <div v-show="show">
                <v-divider></v-divider>

                <v-card-text>
                    <person-form :persons="persons" :personAttr="person1" :hideForm="hideForm"/>

                </v-card-text>
            </div>
        </v-expand-transition>
    </v-card>
<!--    <div>-->
<!--        <i>({{ person.id }})</i> {{ person.firstName }}-->
<!--        <span style="position: absolute; right: 0">-->
<!--        <input type="button" value="Редагувати" @click="edit"/>-->
<!--        <input type="button" value="X" @click="del"/>-->
<!--        </span>-->
<!--    </div>-->
</template>

<script>
    import PersonForm from "./PersonForm.vue";
    export default {
        props: ['person', 'editPerson', 'deletePerson', 'persons'],
        components: {
            PersonForm
        },
        data(){
            return{
                show: false,
                person1: null,
                persons: this.persons
            }
        },
        // data: () => ({
        //     show: false,
        // }),
        methods: {
            edit() {
                this.show = !this.show
                //this.editPerson(this.person)
                this.person1 = this.person

            },
            del() {
                this.deletePerson(this.person)
            },
            hideForm(){
                this.show = false
            }
        }
    }
</script>

<style scoped>

</style>