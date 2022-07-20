<template>
    <div class="modal"  v-show="show" @click.stop="hideModal">
        <div @click.stop class="modal__content">
            <slot></slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ModalWindow",
        props: {
            show: {
                type: Boolean,
                default: false
            }
        },
        methods: {
            hideModal(){
                this.$emit('update:show', false)
            }
        }

    }
</script>

<style scoped>
.modal {
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    background: rgba(0,0,0, 0.5);
    position: fixed;
    display: flex;
    z-index: 1;
    padding: 15px;
}
.modal__content {
    margin: auto;
    background: white;
    border: #212121;
    min-height: 50px;
    min-width: 30%;
    padding: 15px 15px 25px 15px;

    z-index: 1;

}

</style>