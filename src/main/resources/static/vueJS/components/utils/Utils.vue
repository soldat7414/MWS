<template>
    
</template>

<script>
    export default {
        getIndex(list, id) {
            for (let i = 0; i < list.length; i++) {
                if (list[i].id === id) {
                    return i;
                }
            }
            return -1;
        },
    }
</script>

<style scoped>

</style>