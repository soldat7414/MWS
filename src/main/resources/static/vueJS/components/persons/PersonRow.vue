<template>
    <div>
        <i>({{ person.id }})</i> {{ person.firstName }}
        <span style="position: absolute; right: 0">
        <input type="button" value="Редагувати" @click="edit"/>
        <input type="button" value="X" @click="del"/>
        </span>
    </div>
</template>

<script>
    export default {
        props: ['person', 'editPerson', 'deletePerson', 'persons'],
        methods: {
            edit() {
                this.editPerson(this.person);

            },
            del() {
                this.deletePerson(this.person)
                // personApi.remove({id: this.person.id}).then(result => {
                //     if(result.ok){
                //         this.persons.splice(this.persons.indexOf(this.person), 1);
                //     }
                // })
            }
        }
    }
</script>

<style scoped>

</style>