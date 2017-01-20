<template>
    <div>
        <div class="title-wrapper head-wrapper">
            <div class="title">
                <span>{{curSession.name}}</span>
            </div>
            <div class="profile" v-bind:class="{ 'show-sidebar': showSidebar }">
                <span class="iconfont icon-yonghu" v-on:click="this.showSidebar = !this.showSidebar"></span>
                <span class="arrow-top"></span>
            </div>
        </div>
        <div class="message-box-wrapper" v-bind:class="{ 'show-sidebar': showSidebar }">
            <div class="message-list-wrapper" id="messageListBox">
                <MessageList :messageList="messageList"></MessageList>
            </div>
            <div class="sendbox-wrapper">
                <SendBox @sendMessage="sendMessage"></SendBox>
            </div>
            <div v-if="showSidebar" class="sidebar">
                <ProfileBar v-if="curSession.type == SessionType.CHAT" :curSession="curSession"></ProfileBar>
                <GroupBar v-if="curSession.type == SessionType.GROUPCHAT" :curSession="curSession"></GroupBar>
            </div>
        </div>
    </div>
</template>

<script>
import ProfileBar from './ProfileBar'
import GroupBar from './GroupBar'
import MessageList from './MessageList'
import SendBox from './SendBox'
import {SessionType} from '../../sdk/Config'

export default {
    name: 'MessageBox',
    props: ['curSession'],
    components : {
        ProfileBar, GroupBar, MessageList, SendBox
    },
    data: function() {
        return {
            SessionType: SessionType,
            showSidebar: true,
            messageWrapper: {},
            messageList: [],
            loading: true,
            nomore: false
        };
    },
    methods: {
        sendMessage: function(message) {
            message['sessionType'] = this.curSession.type;
            message['toId'] = this.curSession.id;
            message['timestamp'] = config.time;
            this.$http.post(config.apiServer + '/api/v1/messages', message).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.messageList.push(response.data);
                    delete this.messageWrapper[this.curSession.id];
                    this.$emit('showLastest');
                }
            }, err => {

            });
            // 广播消息发送
            message['fake'] = true;
            message['sending'] = true;
            config.bus.$emit('sendmessage', message);
        },
        fetchMessages: function(offset, limit, callback) {
            var params = {'offset': offset, 'limit': limit};
            if (this.curSession.type == SessionType.CHAT) {
                params['toUid'] = this.curSession.id;
            } else if (this.curSession.type == SessionType.GROUPCHAT) {
                params['gid'] = this.curSession.id;
            }
            this.$http.get(config.apiServer + '/api/v1/messages/' + this.curSession.type.toLowerCase(), {
                'params': params
            }).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    callback(response.data);
                }
            });
        },
        setMessageList: function(messageList) {
            this.messageList = messageList;
            this.loading = false;
            this.nomore = messageList.length == 0;
            this.$emit('showLastest');
        },
        pageMessages: function() {
            this.loading = true;
            var wrapper = this.messageWrapper[this.curSession.id];
            var _this = this;
            if (!wrapper) {
                wrapper = {offset: 0, limit: 10, messageList: []};
                this.fetchMessages(wrapper.offset, wrapper.limit, function(mlist) {
                    wrapper.messageList = mlist.reverse().concat(wrapper.messageList);
                    wrapper.offset += wrapper.limit;
                    _this.setMessageList(wrapper.messageList);
                });
                this.messageWrapper[this.curSession.id] = wrapper;
            } else {
                this.setMessageList(wrapper.messageList);
            }
            return wrapper;
        }
    },
    mounted: function() {
        var _this = this;
        config.bus.$on('message', function(response) {
            var data = JSON.parse(response);
            // 是当前对话窗口的
            if (_this.curSession.id == data.uid) {
                _this.messageList.push(data);
                _this.$emit('showLastest');
            }
            delete _this.messageWrapper[data.id];
        });
        config.bus.$on('sendmessage', function(message) {
            // TODO 处理messageList
        });
        this.pageMessages();
        this.$watch('curSession', function(n, o) {
            this.messageList = [];
            this.pageMessages();
        })
    }
}
</script>

<style scoped>
.title-wrapper {
    width: 100%;
    height: 70px;
    line-height: 70px;
    padding: 0 20px;
    font-size: 18px;
    border-bottom: 1px solid #D1D4D8;
}

.title-wrapper .profile {
    position: absolute;
    right: 30px;
    top: 0;
    color: #ccc;
    cursor: pointer;
    font-size: 22px;
}

.title-wrapper .arrow-top {
    display: none;
    bottom: -9px;
    position: absolute;
    transform: rotate(45deg);
    background-color: #f3f5f7;
    width: 20px;
    height: 20px;
    border-left: 1px solid #D1D4D8;
    border-top: 1px solid #D1D4D8;
}

.title-wrapper .show-sidebar .arrow-top {
    display: block;
}

.message-box-wrapper {
    position: absolute;
    width: 100%;
    color: rgba(0,0,0,.87);
    top: 70px;
    bottom: 0;
}

.message-list-wrapper {
    overflow: auto;
    position: absolute;
    top: 0;
    bottom: 140px;
    left: 0;
    right: 0;
    padding: 0;
}

.show-sidebar .message-list-wrapper, .show-sidebar .sendbox-wrapper {
    right: 240px;
}

.sendbox-wrapper {
    height: 140px;
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    border-top: 1px solid #D1D4D8;
}

.sidebar {
    width: 241px;
    transition: .1s .1s;
    display: block;
    border-left: 1px solid rgba(0,0,0,.1);
    right: 0;
    top: 0;
    bottom: 0;
    position: absolute;
}
</style>
