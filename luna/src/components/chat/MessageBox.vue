<template>
    <div>
        <div class="title-wrapper head-wrapper">
            <div class="title">
                <span>{{curSession.name}}</span>
            </div>
            <div class="profile" v-bind:class="{ 'show-sidebar': showSidebar }">
                <span class="iconfont icon-yonghu" v-on:click="toggleSidebar"></span>
                <span class="arrow-top"></span>
            </div>
        </div>
        <div class="message-box-wrapper" v-bind:class="{ 'show-sidebar': showSidebar }">
            <div class="message-list-wrapper" id="messageListBox">
                <MessageList :messageList="messageList" :trigger="trigger"></MessageList>
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
            messageList: []
        };
    },
    methods: {
        toggleSidebar: function() {
            this.showSidebar = !this.showSidebar;
        },
        sendMessage: function(message) {
            alert("to be continue");
            this.$http.post(config.apiService + '/api/v1/messages', message).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.messageList.push(response.data);
                    delete this.messageWrapper[this.curSession.id];
                }
            }, error => {

            });
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
            this.$emit('showLastest');
        },
        pageMessages: function() {
            var wrapper = this.messageWrapper[this.curSession.id];
            var _this = this;
            if (!wrapper) {
                wrapper = {offset: 0, limit: 10, messageList: []};
            }
            this.fetchMessages(wrapper.offset, wrapper.limit, function(mlist) {
                wrapper.messageList = mlist.reverse().concat(wrapper.messageList);
                wrapper.offset += wrapper.limit;
                _this.messageList = wrapper.messageList;
            });
            this.messageWrapper[this.curSession.id] = wrapper;
            return wrapper;
        }
    },
    computed: {
        trigger: function() {
            console.log("trigger");
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
                setTimeout(function() {
                    wrapper.messageList = [
                        {
                            id: 12,
                            uid: 2,
                            avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
                            nickname: '张三',
                            content: '没细看日志，之前是有这个的',
                            type: 'text'
                        }, {
                            id: 13,
                            uid: 1,
                            avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
                            nickname: '张三',
                            content: '没细看日志，之前是有这个的',
                            type: 'text'
                        },
                        {
                            id: 12,
                            uid: 2,
                            avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
                            nickname: '张三',
                            content: `SELECT b.dealid
            from origindb.wwwdeal__dealbizacct as b
            JOIN origindb.wwwdeal__deal as c
            on b.dealid = c.id
            WHERE b.bizacctid in (
              SELECT DISTINCT(a.bizacctid)
              FROM origindb.meituanverify_meituanverify__allverifylog as a
              WHERE a.id >= 7431016249
              AND a.bizacctid % 100 in (80, 81, 82, 83, 84)
              AND a.verifytype in (80, 9, 101)
            )
            AND c.begintime > UNIX_TIMESTAMP('2016-10-19 00:00:00');`,
                            type: 'text'
                        }
                    ];
                    _this.setMessageList(wrapper.messageList);
                }, 100)
            } else {
                _this.setMessageList(wrapper.messageList);
            }
            return wrapper;
        }
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
    z-index: 1;
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
