import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'

import App from './App'
import ChatBox from './components/chat/ChatBox'
import GroupBox from './components/group/GroupBox'


/* eslint-disable no-new */
const router = new VueRouter({
    routes: [
        {
            path: '/',
            component: ChatBox
        },
        {
            path: '/chat/:uid?',
            component: ChatBox
        },
        {
            path: '/group/:gid?',
            component: GroupBox
        }
    ]
});

Vue.use(VueRouter).use(VueResource);
new Vue({
    el: '#app',
    template: '<App/>',
    components: { App },
    router: router
})
