<template>
    <div class="chatlist-wrapper">
        <ul class="chatlist">
            <li class="chat">
                <div>
                    <img class="large-avatar avatar" src="http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile4/99287bb1-8aea-47c6-b628-eaa5d3d496d5">
                </div>
                <div class="chat-badge hide">
                    <span class="badge-num">0</span>
                </div>
                <div class="name">
                    <span class="content">
                        <span>辛孟莹、李德华、于吉星</span>
                        <span>&nbsp;&nbsp;</span>
                    </span>
                </div>
                <div class="desc">
                    <span>于吉星：</span>
                    <span>没事 这个不影响发布</span>
                </div>
                <span class="time">昨天</span>
            </li>
            <li class="chat selected">
                <div>
                    <img class="large-avatar avatar" src="http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile9/1129f264-bdab-43be-a481-23fd69020aaa">
                </div>
                <div class="chat-badge hide">
                    <span class="badge-num">0</span>
                </div>
                <div class="name">
                    <span class="content">
                        <span>于吉星</span>
                        <span>&nbsp;&nbsp;</span>
                    </span>
                </div>
                <div class="desc">
                    <span>没事 这个不影响发布</span>
                </div>
                <span class="time">16/9/21</span>
            </li>
        </ul>
    </div>
</template>

<script>
export default {

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
