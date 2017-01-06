<template>
    <div class="sendbox">
        <div class="tool-wrapper">
            <span class="emoji">
                <i class="iconfont icon-xiaolian" @click="showEmoji"></i>
            </span>
            <span class="fileupload">
                <i class="iconfont icon-fasongwenjian" @click="uploadFile"></i>
                <input type="file" id="uploadFile" @change="onAddFile" v-if="upload" style="display: none">
            </span>
        </div>
        <div class="input-wrapper">
            <textarea id="inputText" name="inputText" v-model="inputText" placeholder="说点什么……" @keyup.shift.enter.prevent="doSendMessage" @keyup.enter.prevent="doSendMessage"></textarea>
        </div>
        <div class="send-wrapper">
            <div>
                <span class="send-tip">Shift + Enter 换行, Enter 发送</span>
                <button type="button" @click="sendMessage" class="send-button button button-primary button-sm">发&nbsp;送</button>
            </div>
        </div>
        <div class="upload-mask-wrapper" v-if="url">
            <div class="upload-mask">
                <div class="preview">
                    <img :src="url">
                </div>
                <div class="btnbar">
                    <button class="button" @click="url = ''">取消</button>
                    <button class="button button-primary" @click="sendMessage">发送</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {MessageType} from '../../sdk/Config'

export default {
    name: 'SendBox',
    data: function() {
        return {
            inputText: '',
            imageRegExp: /(gif|jpeg|jpg|png|bmp)$/i,
            type: MessageType.TEXT,
            url: '',
            upload: false
        };
    },
    methods: {
        showEmoji: function() {
            this.type = MessageType.EMOJI;
            alert("to be continue");
        },
        uploadFile: function() {
            this.upload = true;
            setTimeout(() => {document.getElementById('uploadFile').click()}, 100);
        },
        onAddFile: function(event) {
            var file = event.target.files[0];
            var fileName = file.name;
            if (this.imageRegExp.test(fileName)) {
                this.type = MessageType.IMAGE;
                console.log("send image")
            } else {
                this.type = MessageType.FILE;
                console.log("send normal file")
            }
            var fileFormData = new FormData();
            fileFormData.append('file', file);
            fileFormData.append('type', this.type);
            this.$http.post(config.apiServer + '/api/v1/image/upload', fileFormData).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.url = response.data.url;
                }
            }, error => {
            });
            this.upload = false;
        },
        sendMessage: function() {
            console.log(!this.inputText.trim(), !this.url, !this.inputText && !this.url);
            if (!this.inputText.trim() && !this.url) {
                alert('发送内容不能为空');
                return;
            }
            var params = {};
            params['type'] = this.type;
            params['content'] = this.inputText || this.url;
            this.$emit('sendMessage', params);
            // 置空
            this.inputText = '';
            this.type = MessageType.TEXT;
            this.url = '';
        },
        doSendMessage: function(event) {
            if (event.shiftKey) {
                event.returnValue = false;
            } else {
                event.returnValue = false;
                this.sendMessage();
            }
        }
    }
}
</script>

<style scoped>
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

.send-tip {
    display: inline-block;;
    text-align: right;
    margin: 0 10px 0 0;
    line-height: 30px;
    height: 30px;
    font-size: 12px;
    color: rgba(0,0,0,.38);
}

.upload-mask-wrapper {
    position: fixed;
    width: 100%;
    height: 100%;
    left: 0;
    top:0;
    z-index: 1000;
    text-align: center;
    background-color: rgba(0, 0, 0, 0.5);
}

.upload-mask {
    width: 400px;
    margin: 100px auto;
}

.preview {
}

.preview img {
    max-width: 400px;
    max-height: 400px;
}

.btnbar {
    margin-top: 15px;
}
</style>
