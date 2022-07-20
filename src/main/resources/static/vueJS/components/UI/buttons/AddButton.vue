<template>
    <v-btn
            size="x-small"
            color="grey"
            flat="false"
            class="add_btn">
        <v-icon><span class="material-icons">add</span></v-icon>
    </v-btn>
</template>

<script>
    export default {
        name: "AddButton"
    }
</script>

<style scoped>
.add_btn{
    width: 100%;
    border: 1px solid grey;
    border-radius: 0;
    margin-top: 15px;
}
</style>