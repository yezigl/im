<template>
    <div class="messagebox">
        <div class="title-wrapper head-wrapper">
            <div class="title">
                <span>{{currentChat.name}}</span>
            </div>
            <div class="profile" v-bind:class="{ 'show-sidebar': showSidebar }">
                <span class="iconfont icon-yonghu" v-on:click="toggleSidebar"></span>
                <span class="arrow-top"></span>
            </div>
        </div>
        <div class="message-list-wrapper" v-bind:class="{ 'show-sidebar': showSidebar }">
            <div class="message-list-box">
                <MessageList></MessageList>
            </div>
            <div class="sendbox">
                <div class="tool-wrapper">
                    <span class="emoji">
                        <i class="iconfont icon-xiaolian" @click="showEmoji"></i>
                    </span>
                    <span class="fileupload">
                        <i class="iconfont icon-fasongwenjian" @click="uploadFile"></i>
                        <input type="file" id="uploadFile" @change="onAddFile" style="display: none">
                    </span>
                </div>
                <div class="input-wrapper">
                    <textarea id="inputText" name="inputText" v-model="inputText" placeholder="说点什么……"></textarea>
                </div>
                <div class="send-wrapper">
                    <div>
                        <span class="send-tip">Shift + Enter 换行, Enter 发送</span>
                        <button type="button" @click="sendMessage" class="send-button button button-primary button-sm">发&nbsp;送</button>
                    </div>
                </div>
            </div>
            <div v-if="showSidebar" class="sidebar">
                <ProfileBar v-if="currentChat.type == 'chat'"></ProfileBar>
                <GroupBar v-if="currentChat.type == 'groupchat'"></GroupBar>
            </div>
        </div>
    </div>
</template>

<script>
import ProfileBar from './ProfileBar'
import GroupBar from './GroupBar'
import MessageList from './MessageList'

export default {
    name: 'MessageBox',
    props: ['currentChat'],
    components : {
        ProfileBar, GroupBar, MessageList
    },
    data: function() {
        return {
            showSidebar: true,
            inputText: '',
            imageRegExp: /(gif|jpeg|jpg|png|bmp)$/,
            personInfo: {}
        };
    },
    methods: {
        toggleSidebar: function() {
            this.showSidebar = !this.showSidebar;
        },
        showEmoji: function() {
            alert("to be continue");
        },
        uploadFile: function() {
            document.getElementById('uploadFile').click();
        },
        onAddFile: function(event) {
            var fileName = event.target.files[0].name;
            if (this.imageRegExp.test(fileName)) {
                alert("send image")
            } else {
                alert("send normal file")
            }
        },
        sendMessage: function() {
            alert("to be continue");
        }
    }
}
</script>

<style scoped>
.messagebox {
    position: absolute;
    left: 280px;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: #F3F5F7;
}

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

.message-list-wrapper {
    position: absolute;
    width: 100%;
    color: rgba(0,0,0,.87);
    top: 70px;
    bottom: 0;
}

.message-list-box {
    overflow: auto;
    position: absolute;
    top: 0;
    bottom: 140px;
    left: 0;
    right: 0;
    padding: 0;
}

.show-sidebar .message-list-box, .show-sidebar .sendbox {
    right: 240px;
}

.sendbox {
    height: 140px;
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    border-top: 1px solid #D1D4D8;
}

.sendbox .tool-wrapper {
    height: 30px;
    line-height: 30px;
}

.sendbox .tool-wrapper i {
    color: #ccc;
    cursor: pointer;
}

.sendbox .tool-wrapper .emoji {
    margin-left: 20px;
    font-size: 22px;
}

.sendbox .tool-wrapper .fileupload {
    margin-left: 6px;
    font-size: 20px;
}

.sendbox .input-wrapper {
    width: 100%;
    height: 66px;
}

.sendbox .input-wrapper textarea {
    width: 100%;
    height: 100%;
    overflow: auto;
    background-repeat: no-repeat;
    border: 0;
    box-sizing: border-box;
    padding: 10px 20px;
    line-height: 20px;
    background-color: transparent;
    resize: none;
    outline: 0;
}

.sendbox .send-wrapper {
    text-align: right;
}

.sendbox .send-wrapper .send-button {
    margin: 0 20px 0 0;
    width: 54px;
    height: 30px;
    text-align: center;
    font-size: 12px;
    display: inline-block;
}

.button {
    display: inline-block;
    margin-bottom: 0;
    font-weight: 400;
    text-align: center;
    vertical-align: middle;
    touch-action: manipulation;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    white-space: nowrap;
    line-height: 1.5;
    padding: 8px 31px;
    font-size: 14px;
    border-radius: 4px;
    user-select: none;
    transition: all .3s cubic-bezier(.645,.045,.355,1);
    transform: translateZ(0);
    color: rgba(0,0,0,.87);
    background-color: #f8f8f8;
    border-color: rgba(0,0,0,.1);
}

.button-primary {
    color: #fff;
    background-color: #118bfb;
    border-color: #118bfb;
}

.button-sm {
    padding: 1px 7px;
    border-radius: 4px;
}

.send-tip {
    display: inline-block;;
    text-align: right;
    margin: 0 10px 0 0;
    line-height: 30px;
    height: 30px;
    font-size: 12px;
    color: rgba(0,0,0,.38);
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
