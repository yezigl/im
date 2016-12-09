<template>
    <div class="message-list">
        <div class="message-item-wrapper" v-for="message in messageList" v-bind:class="message.uid == meUid ? 'me' : 'you'">
            <div class="message-item">
                <div class="message-avatar">
                    <img class="medium-avatar avatar" :src="message.avatar">
                </div>
                <div class="right-content">
                    <div class="person">
                        <span class="nickname">{{message.nickname}}</span>
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
</template>

<script>
export default {
    name: 'MessageList',
    components : {

    },
    data: function() {
        return {
            meUid: 1,
            messageList: []
        };
    },
    methods: {

    },
    mounted: function() {
        this.messageList = [
            {
                id: 12,
                uid: 2,
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
                nickname: '张三',
                content: '没细看日志，之前是有这个的',
                type: 'text'
            }
        ];
    }
}
</script>

<style scoped>
.message-list {

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

.message-item {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -webkit-flex-direction: row;
    -ms-flex-direction: row;
    flex-direction: row;
    -webkit-box-pack: start;
    -webkit-justify-content: flex-start;
    -ms-flex-pack: start;
    justify-content: flex-start;
}

.message-avatar {
    margin: 0 10px 0 0;
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
}

.message-item .person .nickname {
    margin: 0 10px 0 0;
    font-size: 12px;
    line-height: 16px;
    color: #596e8f;
}

.message-item .content {
    background-color: #cae5f9;
    border-radius: 8px;
    font-size: 14px;
    line-height: 1.5;
    word-break: break-all;
    vertical-align: middle;
}

.message-item .content pre {
    word-break: break-word;
    word-wrap: break-word;
    white-space: pre-wrap;
    padding: 8px 11px;
    margin: 0;
}

.dxicon {
    display: inline-block;
    font-style: normal;
    vertical-align: middle;
    text-align: center;
    text-transform: none;
    text-rendering: auto;
    line-height: 1;
    font-size: 14px;
    -webkit-font-smoothing: antialiased;
}

.dxicon-lfc:before {
    content: "\E615";
}
.dxicon:before {
    display: block;
    font-family: dxicon!important;
}

.message-item .right-content .bubble-corner-container .bubble-corner {
    position: absolute;
    font-size: 14px;
    z-index: 0;
}

.message-item .right-content .bubble-corner-container .bubble-corner.left {
    left: -6px;
    top: 21px;
}

.message-item .right-content .bubble-corner-container .bubble-corner.left.lfc {
    color: #cae5f9;
}
</style>
