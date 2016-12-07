<template>
    <div class="chatlist-wrapper">
        <ul class="chatlist">
            <li class="chat" v-for="chat in chatList" @click="showChat(chat.id)" v-bind:class="{'selected': showChatId == chat.id}">
                <div>
                    <img class="large-avatar avatar" :src="chat.avatar">
                </div>
                <div class="chat-badge" v-bind:class="{'hide': chat.unread == 0}">
                    <span class="badge-num">{{chat.unread}}</span>
                </div>
                <div class="name">
                    <span class="content">
                        <span>{{chat.name}}</span>
                        <span>&nbsp;&nbsp;</span>
                    </span>
                </div>
                <div class="desc">
                    <span v-if="chat.group">{{chat.lastName}}：</span>
                    <span>{{chat.lastContent}}</span>
                </div>
                <span class="time">{{chat.time}}</span>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'ChatList',
    data: function() {
        return {
            chatList: [],
            showChatId: 0
        };
    },
    methods: {
        showChat: function(id) {
            this.showChatId = id;
        }
    },
    mounted: function() {
        this.chatList = [
            {
                id: 1000,
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile4/99287bb1-8aea-47c6-b628-eaa5d3d496d5',
                unread: 0,
                name: '张三、李四、王五',
                lastName: '张三',
                lastContent: '没事 这个不影响发布',
                time: '昨天',
                group: true
            }, {
                id: 1001,
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile9/1129f264-bdab-43be-a481-23fd69020aaa',
                unread: 0,
                name: '张三',
                lastName: '张三',
                lastContent: '没事 这个不影响发布',
                time: '16/9/21',
                group: false
            }
        ];
    }
}
</script>

<style scoped>
.chatlist-wrapper {
    width: 100%;
    top: 70px;
    bottom: 0;
    position: absolute;
}

.chatlist {
    min-height: 100%;
    margin: 0;
    padding: 0;
}

.chatlist li {
    list-style: none;
}

.chat {
    display: block;
    height: 70px;
    padding: 15px 20px;
    position: relative;
    cursor: pointer;
}

.chat.selected, .chat.selected:HOVER {
    background-color: #C5DCF1;
}

.chat:HOVER {
    background-color: #DBE7F1;
}

.chat .avatar {
    float: left;
    margin-right: 12px;
}

.chat .name {
    line-height: 18px;
    height: 18px;
    max-width: 90%;
    overflow: hidden;
    margin-top: 1px;
}

.chat .name .content {
    max-width: 76%;
    display: inline-block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.chat .desc {
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

.chat .time {
    position: absolute;
    right: 20px;
    color: rgba(0, 0, 0, .38);
    top: 15px;
    font-size: 12px;
    line-height: 18px;
}
</style>
