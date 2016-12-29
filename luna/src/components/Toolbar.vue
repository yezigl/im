<template>
    <div id="toolbar">
        <div class="userinfo" :title="userInfo.name">
            <img class="large-avatar round-avatar" :src="userInfo.avatar" @click="showProfile" />
        </div>
        <div class="tool">
            <div class="app-item" title="消息" v-bind:class="{ 'active': type == 'chat' }">
                <div class="activebar"></div>
                <a href="/#/chat" @click="changeToolbar('chat')"><i class="iconfont icon-liaotian"></i></a>
            </div>
            <div class="app-item" title="群组" v-bind:class="{ 'active': type == 'group' }">
                <div class="activebar"></div>
                <a href="/#/group" @click="changeToolbar('group')"><i class="iconfont icon-qunzu"></i></a>
            </div>
            <div class="app-item" title="通讯录" v-bind:class="{ 'active': type == 'roster' }">
                <div class="activebar"></div>
                <a href="/#/roster" @click="changeToolbar('roster')"><i class="iconfont icon-tongxunlu"></i></a>
            </div>
        </div>
        <div class="settings">
            <div class="app-item" title="设置" v-bind:class="{ 'active': type == 'settings' }">
                <div class="activebar"></div>
                <a href="/#/settings" @click="changeToolbar('settings')"><i class="iconfont icon-shezhi"></i></a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Toolbar',
    data: function() {
        var match = /#\/(\w+)(\/.*)?/.exec(location.hash);
        return {
            type: match ? match[1] : 'chat',
            userInfo: {}
        };
    },
    methods: {
        changeToolbar: function(type) {
            this.type = type;
        },
        showProfile: function() {
            alert('to be continued')
        }
    },
    mounted: function() {
        this.$http.get(config.apiServer + '/api/v1/rosters/' + config.uid).then(suc => {
            var response = suc.data;
            if (response.code == 200 && response.data) {
                this.userInfo = response.data;
            }
        }, error => {

        });
    }
}
</script>

<style scoped>
#toolbar {
    position: absolute;
    width: 60px;
    height: 100%;
    background-color: #2B3D52;
}

.userinfo {
    height: 70px;
    line-height: 70px;
    text-align: center;
    vertical-align: middle;
    border-bottom: 1px solid #3F5063;
    cursor: pointer;
}

.tool {
    border-bottom: 1px solid #3F5063;
}

.app-item {
    width: 100%;
    height: 46px;
    line-height: 46px;
    text-align: center;
    cursor: pointer;
    color: #fff;
    font-size: 20px;
}

.app-item a {
    color: #ccc;
    text-decoration: none;
    display: block;
}

.app-item:HOVER, .app-item.active {
    background-color: #22486C;
}

.app-item a:hover, .app-item.active a {
    color: #fff;
}

.active .activebar {
    width: 4px;
    height: 46px;
    background-color: #00B5FF;
    position: fixed;
}

.settings {
    position: absolute;
    bottom: 0px;
    width: 100%;
    border-top: 1px solid #3F5063;
}

</style>
