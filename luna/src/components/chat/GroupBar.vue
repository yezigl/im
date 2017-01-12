<template>
    <div class="groupinfo-wrapper">
        <div class="tab-header">
            <ul class="tab-list">
                <li class="item" @click="changeTab(1)" v-bind:class="{'item-on': currentTab == 1}">资料</li>
                <li class="item" @click="changeTab(2)" v-bind:class="{'item-on': currentTab == 2}">设置</li>
            </ul>
        </div>
        <div class="tab-panel" v-if="currentTab == 1">
            <div class="group-notice">
                <div class="group-notice-header">
                    <span>群公告</span>
                </div>
                <div class="group-notice-content">
                    <span>{{groupInfo.notice ? groupInfo.notice : '暂无公告'}}</span>
                </div>
            </div>
            <div class="group-members">
                <div class="group-members-header">成员({{members.length}}人)</div>
                <div class="group-members-add member" @click="addGroupMembers">
                    <i class="iconfont icon-tianjia avatar"></i>
                    <span class="name">添加成员</span>
                </div>
                <div class="group-members-list">
                    <div class="member" v-for="member in members">
                        <div class="avatar"><img class="round-avatar base-avatar " :src="member.avatar"></div>
                        <span class="name">{{member.name}}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-panel" @click="changeTab(2)" v-else>
            <div class="tab-panel-settings">
                <div class="group-notice">
                    <div class="group-name-header">
                        <span>群名称</span>
                    </div>
                    <div class="group-name-content">
                        <span>{{groupInfo.name}}</span>
                    </div>
                    <div class="group-admin-header">
                        <span>群管理员</span>
                    </div>
                    <div class="group-admin-content">
                        <span>{{groupInfo.admin.name}}</span>
                    </div>
                </div>
                <div class="group-options">
                    <div class="comp-switch">
                        <span>群置顶</span>
                        <input type="checkbox" readonly v-model="option.top" :checked="option.top == true">
                        <div class="switch-round" @click="switchOption('top')"></div>
                    </div>
                    <div class="comp-switch">
                        <span>消息免打扰</span>
                        <input type="checkbox" readonly v-model="option.remind" :checked="option.remind == true">
                        <div class="switch-round" @click="switchOption('remind')"></div>
                    </div>
                    <div class="comp-switch">
                        <span>保存到群组列表</span>
                        <input type="checkbox" readonly v-model="option.favor" :checked="option.favor == true">
                        <div class="switch-round" @click="switchOption('favor')"></div>
                    </div>
                </div>
            </div>
            <div class="wrapper-group-button">
                <button type="button" class="button comp-btn" @click="leaveGroup">退出群组</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'GroupBar',
    props: ['curSession'],
    data: function() {
        return {
            groupInfo: {},
            members: [],
            currentTab: 1,
            option: {top: false, remind: false, favor: false}
        };
    },
    methods: {
        changeTab: function(num) {
            this.currentTab = num;
        },
        addGroupMembers: function() {
            alert('to be continued')
        },
        switchOption: function(op) {
            var params = {};
            params[op] = !this.option[op];
            this.$http.post(config.apiServer + '/api/v1/groups/' + this.curSession.id + '/' + op, params).then(suc => {
                this.option[op] = !this.option[op];
                console.log(suc.data);
            }, err => {
                alert('500');
            });
        },
        leaveGroup: function() {
            alert('to be continued')
        }
    },
    mounted: function() {
        this.$http.get(config.apiServer + '/api/v1/groups/' + this.curSession.id).then(suc => {
            var response = suc.data;
            if (response.code == 200 && response.data) {
                this.groupInfo = response.data;
                this.option = this.groupInfo.option;
            }
        });
        this.$http.get(config.apiServer + '/api/v1/groups/' + this.curSession.id + '/members').then(suc => {
            var response = suc.data;
            if (response.code == 200 && response.data) {
                this.members = response.data;
            }
        });
    }
}
</script>

<style scoped>
.groupinfo-wrapper {

}

.tab-header {
    height: 40px;
    padding: 0;
    border-bottom: 1px solid rgba(0,0,0,.1);
}

.tab-list {
    color: rgba(0,0,0,.54);
}

.item {
    display: inline-block;
    height: 40px;
    line-height: 40px;
    margin: 0 43px;
    font-size: 16px;
    cursor: pointer;
}

.item-on {
    border-bottom: 2px solid #118bfb;
}

.group-notice {
    padding-bottom: 24px;
    margin-left: 20px;
    border-bottom: 1px solid rgba(0,0,0,.05);
    position: relative;
    color: rgba(0,0,0,.38);
}

.group-notice-header, .group-name-header, .group-admin-header {
    margin: 24px 20px 8px 0;
    font-size: 14px;
    line-height: 20px;
    user-select: none;
}

.group-notice-content {
    min-height: 72px;
    height: 120px;
    font-size: 14px;
    line-height: 22px;
}

.group-name-content, .group-admin-content {
    color: rgba(0,0,0,.87);
}

.group-members-list {
    position: absolute;
    bottom: 0;
    top: 315px;
    left: 0;
    right: 0;
    overflow: auto;
}

.group-members {
}

.group-members-header {
    color: rgba(0,0,0,.5);
    padding: 18px 0 0 20px;
}

.member {
    display: block;
    user-select: none;
    width: 100%;
    height: 38px;
    padding: 5px 20px;
    box-sizing: border-box;
    position: relative;
    font-size: 12px;
    cursor: pointer;
}

.member .name {
    max-width: 60%;
    color: rgba(0,0,0,.87);
    line-height: 28px;
    height: 28px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: pre;
}

.member .iconfont {
    font-size: 28px;
    color: rgba(0,0,0,.5);
    line-height: 28px;
    height: 28px;
}

.member .avatar {
    float: left;
    margin-right: 10px;
}

.tab-panel-settings {
    position: absolute;
    top: 40px;
    bottom: 38px;
    width: 100%;
    overflow: auto;
}

.group-options {
    margin: 0 15px 0 20px;
    padding: 20px 0 10px 0;
}

.comp-switch {
    position: relative;
    display: block;
    padding-bottom: 14px;
}

.comp-switch span {
    vertical-align: middle;
    cursor: pointer;
}

.switch-round {
    position: relative;
    float: right;
    display: inline-block;
    cursor: pointer;
    height: 16px;
    width: 36px;
    border-radius: 100px;
    background-color: rgba(0,0,0,.24);
    -webkit-transition: .2s;
    transition: .2s;
}

.switch-round::before {
    position: absolute;
    content: "";
    height: 14px;
    width: 14px;
    left: 1px;
    top: 1px;
    border-radius: 100px;
    background-color: #f8f8f8;
    -webkit-transition: .2s;
    transition: .2s;
}

.comp-switch input {
    display: none;
}

.comp-switch input:checked+.switch-round {
    background-color: #118bfb;
}

.comp-switch input:checked+.switch-round:before {
    transform: translateX(20px);
}

.wrapper-group-button {
    width: 100%;
    position: absolute;
    bottom: 0;
    text-align: center;
}

.wrapper-group-button button {
    width: 100%;
    color: #ff5d4a;
    background-color: #f3f5f7;
    border: solid rgba(0,0,0,.05);
    border-width: 1px 0 0;
}
</style>
