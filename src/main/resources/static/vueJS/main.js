//
// import { createApp } from 'vue'
// import App from "./pages/App.vue";
//
// import 'vuetify/dist/vuetify.min.css'
//
//
// const vue = createApp(App, {
//     render: a => a(App)
//
// }).mount('#app')


import { createApp } from 'vue'
import { createVuetify } from 'vuetify'
import App from "./pages/App.vue";
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import ui from './components/UI'
import {aliases, md} from "vuetify/iconsets/md";
import 'material-design-icons-iconfont/dist/material-design-icons.css' // Ensure your project is capable of handling css files

const app = createApp(App)

const vuetify = createVuetify({
    components,
    directives,
    display: {
        mobileBreakpoint: 'sm',
        thresholds: {
            xs: 0,
            sm: 340,
            md: 540,
            lg: 800,
            xl: 1280,
        },
    },
    icons: {
        defaultSet: 'md',
        aliases,
        sets: {
            md,
        },
    }
})

app.use(vuetify)

ui.forEach(uiItem => {
    app.component(uiItem.name, uiItem)
})

app.mount('#app')