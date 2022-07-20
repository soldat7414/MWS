<template>
    <input class="input" type="text"
    :value="modelValue"
    @input="updateInput">
</template>

<script>
    export default {
        name: "TextInput",
        props: {
            modelValue: [String, Number]
        },
        methods: {
            updateInput(event){
                this.$emit('update:modelValue', event.target.value)
            }
        }
    }
</script>

<style scoped>
.input {
    width: 85%;
    border: 1px solid gray;
    padding: 10px 15px;
    margin: 10px 10px 0 10px;
}
</style>