<template>
    <div class="message-list-box">
        <div>
            <div class="loading-wrapper loaded">没有更多数据了</div>
        </div>
        <div class="message-list">
            <div class="message-item-wrapper" v-for="message in messageList" v-bind:class="message.uid == meUid ? 'me' : 'you'">
                <div class="message-item">
                    <div class="avatar">
                        <img class="medium-avatar" :src="message.avatar">
                    </div>
                    <div class="right-content">
                        <div class="person" v-if="message.uid != meUid">
                            <span class="nickname">{{message.name}}</span>
                        </div>
                        <div class="bubble-corner-container">
                            <i class="dxicon bubble-corner left lfc dxicon-lfc"></i>
                        </div>
                        <div class="message-container">
                            <div class="content">
                                <pre>{{message.content}}</pre>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MessageList',
    props: ['messageList'],
    components : {

    },
    data: function() {
        return {
            meUid: config.uid
        };
    },
    methods: {

    }
}
</script>

<style scoped>
.loading-wrapper {
    text-align: center;
    padding: 10px;
    color: rgba(0,0,0,.38);
    cursor: pointer;
}

.loaded {
    cursor: text;
}

.message-list {
    width: 100%;
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
}

.message-item-wrapper {
    position: relative;
    width: 100%;
    box-sizing: border-box;
    margin: 0 0 14px;
}

.message-item-wrapper.you {
    padding: 0 66px 0 20px;
}

.message-item-wrapper.me {
    padding: 0 20px 0 66px;
}

.message-item-wrapper.you .message-item {
    display: flex;
    box-orient: horizontal;
    box-direction: normal;
    flex-direction: row;
    box-pack: start;
    justify-content: flex-start;
}

.message-item-wrapper.me .message-item {
    display: flex;
    box-orient: horizontal;
    box-direction: reverse;
    flex-direction: row-reverse;
    box-pack: start;
    justify-content: flex-start;
}

.message-item-wrapper.you .avatar {
    margin: 0 10px 0 0;
    cursor: pointer;
    display: inline-block;
    height: 100%;
}

.message-item-wrapper.me .avatar {
    margin: 0 0 0 10px;
    cursor: pointer;
    display: inline-block;
    height: 100%;
}

.message-item .right-content {
    position: relative;
    display: inline-block;
}

.message-item .person {
    margin: 0 0 2px;
    height: 16px;
    line-height: 16px;
}

.message-item .person .nickname {
    margin: 0 10px 0 0;
    font-size: 12px;
    color: #596e8f;
}

.message-item-wrapper.you .content {
    background-color: #cae5f9;
}

.message-item-wrapper.me .content {
    background-color: #e5e5e5;
    text-align: right;
}

.message-item .content {
    border-radius: 8px;
    font-size: 14px;
    line-height: 1.5;
    vertical-align: middle;
}

.message-item .content pre {
    word-break: break-word;
    word-wrap: break-word;
    white-space: pre-wrap;
    padding: 8px 11px;
    margin: 0;
}

</style>
