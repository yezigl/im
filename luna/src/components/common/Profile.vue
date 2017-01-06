<template>
    <div class="profile-wrapper" v-if="shown" @click.self="shown = !shown">
        <div class="profile-dialog-inner person-vcard-dialog" id="vcard" :style="vstyle">
            <div class="close">
                <i class="iconfont icon-guanbi" @click="shown = false"></i>
            </div>
            <div class="header">
                <div class="person">
                    <div class="wrapper-avatar">
                        <img class="round-avatar xlarge-avatar" :src="roster.avatar">
                        <div class="editmask" v-if="isMe" @click="uploadFile">修改</div>
                        <input type="file" v-if="isMe" name="file" id="file" @change="onAddFile" style="display: none">
                    </div>
                    <div class="info">
                        <div class="wrapper-name">
                            <div class="name">{{roster.name}}</div>
                            <div class="gender"><i class="dxicon dxicon-gender-man"></i></div>
                        </div>
                        <div class="passport">{{roster.mailNickname}}</div>
                    </div>
                </div>
                <div class="signature">
                    <div class="text-vard">{{roster.signature ? roster.signature : '这个人很懒，什么都没写~~'}}</div>
                    <i class="iconfont icon-bianji banji" v-if="isMe" @click="signature=true"></i>
                    <input type="input" v-if="isMe" v-show="signature" v-model="roster.signature" class="banji-input" @blur="modify">
                </div>
            </div>
            <div class="content">
                <ul class="list">
                    <li class="item">
                        <div class="tips">部门</div>
                        <div class="value">{{roster.department}}</div>
                    </li>
                    <li class="item">
                        <div class="tips">工位</div>
                        <div class="value">{{roster.office}}</div>
                    </li>
                    <li class="item">
                        <div class="tips">手机</div>
                        <div class="value">
                            <span>{{roster.mobile ? roster.mobile : '未填写'}}</span>
                            <i class="iconfont icon-bianji banji" v-if="isMe" @click="mobile=true"></i>
                            <input type="tel" v-if="isMe" v-show="mobile" v-model="roster.mobile" class="banji-input" @blur="modify">
                        </div>
                    </li>
                    <li class="item">
                        <div class="tips">分机</div>
                        <div class="value">
                            <span>{{roster.tel ? roster.tel : '未填写'}}</span>
                            <i class="iconfont icon-bianji banji" v-if="isMe" @click="tel=true"></i>
                            <input type="tel" v-if="isMe" v-show="tel" v-model="roster.tel" class="banji-input" @blur="modify">
                        </div>
                    </li>
                </ul>
            </div>
            <div class="footer">
                <div class="btn">发消息</div>
            </div>
            <div style="display: none;">
            </div>
        </div>
    </div>
</template>

<script>
import {SessionType, MessageType} from '../../sdk/Config'

export default {
    name: 'Profile',
    props: ['profile'],
    data: function() {
        return {
            imageRegExp: /(gif|jpeg|jpg|png|bmp)$/i,
            me: config.uid,
            shown: false,
            roster: {},
            vstyle: '',
            avatar: false,
            signature: false,
            tel: false,
            mobile: false
        };
    },
    methods: {
        modify: function() {
            this.avatar = false;
            this.signature = false;
            this.tel = false;
            this.mobile = false;
            this.$http.post(config.apiServer + '/api/v1/rosters/' + this.me, {
                avatar: this.roster.avatar,
                signature: this.roster.signature,
                tel: this.roster.tel,
                mobile: this.roster.mobile
            }).then(suc => {
                this.roster = suc.data.data;
            });
        },
        uploadFile: function() {
            document.getElementById('file').click();
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
                    this.roster.avatar = response.data.url;
                    this.modify();
                }
            }, error => {

            });
        }
    },
    computed: {
        isMe: function() {
            return this.roster.uid == this.me;
        }
    },
    mounted: function() {
        var _this = this;
        config.bus.$on('profile', function(uid, event) {
            _this.shown = true;
            console.log('event bus');
            _this.$http.get(config.apiServer + '/api/v1/rosters/' + uid).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    _this.roster = response.data;
                }
            });
            var ww = document.body.clientWidth, wh = document.body.clientHeight;
            var x = event.target.x, y = event.target.y;
            var w = event.target.clientWidth, h = event.target.clientHeight;
            var vw = 320, vh = 400;
            var left = x + w + 20, top = y - vh / 2;
            if (left + vw > ww) {
                left = x - 320 - 10;
            }
            if (left < 0) {
                left = 10;
            }
            if (top < 0) {
                top = 10;
            }
            _this.vstyle = 'left: ' + left + 'px; top: ' + top + 'px;'
        });
    }
}
</script>

<style scoped>
.profile-wrapper {
    position: fixed;
    width: 100%;
    height: 100%;
    left: 0;
    top:0;
    z-index: 1000;
}

.profile-dialog-inner {
    position: absolute;
    background: #fff;
    min-width: 200px;
    max-width: 960px;
    min-height: 100px;
    z-index: 1001;
    box-shadow: 0 2px 8px 0 rgba(0,0,0,.24);
    border-radius: 4px;
}

.person-vcard-dialog {
    width: 320px;
    font-size: 12px;
}

.zoom-enter {
    animation-timing-function: cubic-bezier(.08,.82,.17,1);
}

.zoom-enter.zoom-enter-active {
    animation-name: antZoomIn;
    animation-play-state: running;
}

.close {
    position: absolute;
    color: rgba(0,0,0,.38);
    font-size: 22px;
    line-height: 24px;
    top: 9px;
    right: 0;
    cursor: pointer;
    z-index: 1;
    width: 28px;
    height: 28px;
}

.header {
    background-image: url(http://x.sankuai.com/public/images/vcard_bg.png?v=ee78753654);
    background-color: #2e3e51;
    background-size: cover;
    color: #fff;
    padding: 30px 30px 27px;
    border-top-left-radius: 4px;
    border-top-right-radius: 4px;
}

.header .person {
    position: relative;
    display: flex;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    -ms-flex-pack: justify;
    justify-content: space-between;
}

.header .signature {
    display: flex;
    margin-top: 17px;
    position: relative;
    text-align: left;
}

.header .person .wrapper-avatar {
    position: relative;
    cursor: pointer;
    border-radius: 50%;
    height: 60px;
    overflow: hidden;
}

.header .person .editmask {
    text-align: center;
    background: rgba(0,0,0,.5);
    width: 100%;
    top: 40px;
    position: absolute;
    height: 30px;
    font-size: 12px;
}

.header .person .info {
    flex: 1;
    margin-left: 15px;
    margin-top: 6px;
    font-size: 20px;
}

.header .person .info .name {
    margin-right: 10px;
}

.header .person .passport {
    line-height: 20px;
    font-size: 14px;
}

.content .list {
    padding: 0 30px;
    margin: 27px 0;
}

.content .list .item {
    text-align: left;
    margin: 14px 0;
    line-height: 18px;
    display: flex;
}

.content .list .item .tips {
    color: rgba(0,0,0,.38);
    width: 44px;
}

.content .list .item .value {
    flex: 1;
    position: relative;
}

.footer {
    border-top: 1px solid rgba(0,0,0,.1);
    height: 40px;
    box-sizing: border-box;
    line-height: 40px;
    text-align: center;
}

.footer .btn {
    color: #118bfb;
}

.banji {
    position: relative;
    left: 4px;
    top: -1px;
    cursor: pointer;
}

.banji-input {
    width: 130px;
    height: 18px;
    line-height: 18px;
    position: absolute;
    left: 0;
    color: rgba(0,0,0,.5);
}
</style>
