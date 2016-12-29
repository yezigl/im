import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'

import App from './App'
import SessionBox from './components/chat/SessionBox'
import GroupBox from './components/group/GroupBox'
import RosterBox from './components/roster/RosterBox'
import SettingsBox from './components/settings/SettingsBox'

import {IMSDK} from './sdk/IMSDK'
import LoginSDK from './sdk/LoginSDK'

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
            path: '/groupchat/:gid?',
            component: SessionBox
        },
        {
            path: '/group/:gid?',
            component: GroupBox
        },
        {
            path: '/roster',
            component: RosterBox
        },
        {
            path: '/settings',
            component: SettingsBox
        }
    ]
});

window.config = {
    apiServer: 'http://x.focus.cn',
    uid: 0,
    tk: '',
    time: '',
    bus: new Vue()
}
if (LoginSDK.login()) {
    Vue.use(VueRouter).use(VueResource);
    var vue = new Vue({
        el: '#app',
        template: '<App/>',
        components: { App },
        router: router
    });

    var imsdk = new IMSDK(vue);
    setTimeout(() => {imsdk.ntp()}, 1000);
}
