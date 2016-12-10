<template>
    <div>
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
