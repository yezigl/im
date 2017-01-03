<template>
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
</template>

<script>
import {MessageType} from '../../sdk/Config'

export default {
    name: 'SendBox',
    data: function() {
        return {
            inputText: '',
            imageRegExp: /(gif|jpeg|jpg|png|bmp)$/,
            type: MessageType.TEXT,
            url: '',
            fileFormData: new FormData(),
            formData: new FormData()
        };
    },
    methods: {
        showEmoji: function() {
            this.type = MessageType.EMOJI;
            alert("to be continue");
        },
        uploadFile: function() {
            document.getElementById('uploadFile').click();
        },
        onAddFile: function(event) {
            var file = event.target.files[0];
            var fileName = file.name;
            if (this.imageRegExp.test(fileName)) {
                this.type = MessageType.IMAGE;
                alert("send image")
            } else {
                this.type = MessageType.FILE;
                alert("send normal file")
            }
            var fileFormData = new FormData();
            fileFormData.append('file', file);
            fileFormData.append('type', type);
            this.$http.post(config.apiServer + '/api/v1/upload', fileFormData).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.url = response.data.url;
                }
            }, error => {

            });
        },
        sendMessage: function() {
            if (!this.inputText || !this.url) {
                alert('发送内容不能为空');
                return;
            }
            var formData = new FormData();
            formData.append('type', this.type);
            formData.append('content', this.inputText);
            formData.append('url', this.url);
            this.$emit('sendMessage', formData);
            // 置空
            this.inputText = '';
            this.type = MessageType.TEXT;
            this.url = '';
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
</style>
