import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import {connect} from './util/ws'
import router from 'router/router'

if (frontendData.profile) {
    connect()
}

Vue.prototype.$resource = VueResource;
Vue.use(VueResource)

new Vue({
    el: '#app',
    router,
    render: a => a(App)
})
