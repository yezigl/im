<template>
    <div class="session-list-wrapper">
        <ul class="session-list">
            <li class="session" v-for="(session, index) in sessionList" @click="showSession(session, index)" v-bind:class="{'selected': sessionId == session.id}">
                <div class="avatar">
                    <img class="large-avatar round-avatar" :src="session.avatar">
                </div>
                <div class="session-badge" v-bind:class="{'hide': session.unread == 0}">
                    <span class="badge-num">{{session.unread}}</span>
                </div>
                <div class="name">
                    <span class="content">
                        <span>{{session.name}}</span>
                        <span>&nbsp;&nbsp;</span>
                    </span>
                </div>
                <div class="desc">
                    <span v-if="session.type == SessionType.GROUPCHAT">{{session.lastName}}：</span>
                    <span>{{session.lastContent}}</span>
                </div>
                <span class="time">{{session.time}}</span>
            </li>
        </ul>
    </div>
</template>

<script>
import Config from '../../sdk/Config'

export default {
    name: 'SessionList',
    data: function() {
        return {
            sessionList: [],
            sessionId: 0,
            SessionType: Config.SessionType
        };
    },
    methods: {
        showSession: function(session, index) {
            if (!session) {
                location.href = '/#/';
                return;
            }
            this.sessionId = session.id;
            if (index !== undefined) {
                session.unread = 0;
            }
            location.href = '/#/' + session.type + '/' + session.id;
            this.$emit('showSession', session);
        }
    },
    mounted: function() {
        this.sessionList = [
            {
                id: 1000,
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile4/99287bb1-8aea-47c6-b628-eaa5d3d496d5',
                unread: 99,
                name: '张三、李四、王五',
                lastName: '张三',
                lastContent: '没事 这个不影响发布',
                time: '昨天',
                type: 'groupchat'
            }, {
                id: 1001,
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile9/1129f264-bdab-43be-a481-23fd69020aaa',
                unread: 0,
                name: '张三',
                lastName: '张三',
                lastContent: '没事 这个不影响发布',
                time: '16/9/21',
                type: 'chat'
            }
        ];
        var match = /#\/(\w+)\/(\d+)(.*)?/.exec(location.hash);
        if (match) {
            var filterd = this.sessionList.filter(e => e.id == match[2]);
            this.showSession(filterd.length == 0 ? null : filterd[0]);
        } else {
            this.showSession(null);
        }
    }
}
</script>

<style scoped>
.session-list-wrapper {
    width: 100%;
    top: 70px;
    bottom: 0;
    position: absolute;
}

.session-list {
    min-height: 100%;
    margin: 0;
    padding: 0;
}

.session-list li {
    list-style: none;
}

.session {
    display: block;
    height: 70px;
    padding: 15px 20px;
    position: relative;
    cursor: pointer;
}

.session.selected, .session.selected:HOVER {
    background-color: #C5DCF1;
}

.session:HOVER {
    background-color: #DBE7F1;
}

.session .avatar {
    float: left;
    margin-right: 12px;
}

.session .name {
    line-height: 18px;
    height: 18px;
    max-width: 90%;
    overflow: hidden;
    margin-top: 1px;
}

.session .name .content {
    max-width: 76%;
    display: inline-block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.session .desc {
    font-size: 12px;
    color: rgba(0, 0, 0, .54);
    min-width: 54%;
    max-width: 70%;
    margin-top: 2px;
    height: 18px;
    text-overflow: ellipsis;
    overflow: hidden;
    line-height: 18px;
}

.session .time {
    position: absolute;
    right: 20px;
    color: rgba(0, 0, 0, .38);
    top: 15px;
    font-size: 12px;
    line-height: 18px;
}

.session .session-badge {
    position: absolute;;
    min-width: 10px;
    width: 18px;
    height: 18px;
    line-height: 18px;
    left: 15px;
    top: 10px;
    text-align: center;
    font-size: 10px;
    color: #fff;
    white-space: nowrap;
    vertical-align: middle;
    background-color: #FF5D4A;
    border-radius: 50%;
}
</style>
