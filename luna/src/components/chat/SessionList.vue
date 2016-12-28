<template>
    <div class="session-list-wrapper">
        <ul class="session-list">
            <li class="session" v-for="(session, index) in sessionList" @click="showSession(session, index)" v-bind:class="{'selected': curSession.id == session.id}">
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
import {SessionType} from '../../sdk/Config'

export default {
    name: 'SessionList',
    props: ['sessionList', 'curSession'],
    data: function() {
        return {
            SessionType: SessionType
        };
    },
    methods: {
        showSession: function(session, index) {
            this.$emit('showSession', session, index);
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
