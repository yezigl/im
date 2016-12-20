import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'

import App from './App'
import SessionBox from './components/chat/SessionBox'
import GroupBox from './components/group/GroupBox'

import MessageManager from './sdk/MessageManager'
import IMSDK from './sdk/IMSDK'

/* eslint-disable no-new */
const router = new VueRouter({
    routes: [
        {
            path: '/',
            component: SessionBox
        },
        {
            path: '/chat/:uid?',
            component: SessionBox
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
IMSDK.start();
