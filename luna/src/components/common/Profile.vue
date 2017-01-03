<template>
    <div class="profile-wrapper">
        <div class="profile">
            <div class="profile-wrapper">
                <img class="round-avatar xlarge-avatar" :src="profile.avatar" />
                <div class="personinfo">
                    <div class="name">{{profile.name}}</div>
                    <div class="email">{{profile.email}}</div>
                    <div class="department">{{profile.department}}</div>
                </div>
                <div class="extrainfo">
                    <div class="desc-label">个性签名</div>
                    <div class="desc">{{profile.description}}</div>
                </div>
            </div>
        </div>
    </div>
    <div class="profile-wrapper shown">
        <div class="comp-dialog-inner person-vcard-dialog zoom-enter zoom-enter-active" style="left: 382px; top: 5px;">
            <div class="close">
                <i class="dxicon dxicon-remove"></i>
            </div>
            <div class="header">
            </div>
            <div class="content">
                <div class="wrapper-uservcard">
                    <div class="wrapper-header-outer">
                        <div class="wrapper-header-inner">
                            <div class="wrapper-avator">
                                <div class="comp-avator avator-xxlarge">
                                    <img >
                                </div>
                            </div>
                            <div class="info">
                                <div class="wrapper-name">
                                    <div class="name">于吉星</div>
                                    <div class="gender"><i class="dxicon dxicon-gender-man"></i></div>
                                </div>
                                <div class="passport">yujixing</div>
                            </div>
                        </div>
                        <div class="signature"><div class="text-vard">天下英雄出我辈，一入Java岁月催</div></div>
                    </div>
                    <div class="ctn-content">
                        <ul class="list">
                            <li class="item"><div class="tips">部门</div><div class="value">集团/餐饮平台/到店餐饮技术部/交易与信息技术中心/订单组</div></li>
                            <li class="item"><div class="tips">工位</div><div class="value">北京 恒电C座</div></li>
                            <li class="item"><div class="tips">手机</div><div class="value"><span>187********</span></div></li>
                            <li class="item"><div class="tips">分机</div><div class="value">未填写</div></li>
                        </ul>
                    </div>
                    <div class="footer">
                        <div class="btn">发消息</div>
                        <div class="btn is-contact">删除联系人</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {SessionType} from '../../sdk/Config'

export default {
    name: 'Profile',
    props: ['profile'],
    data: function() {
        return {
            me: config.uid
        };
    },
    methods: {
        modify: function(roster) {
            this.$http.post(config.apiServer + '/api/v1/sessions', {
                toId: roster.uid,
                type: SessionType.CHAT
            }).then(suc => {
                console.log('create chat with ' + roster.uid);
                location.href = '/#/' + SessionType.CHAT.toLowerCase() + '/' + roster.uid;
            });
        }
    }
}
</script>

<style scoped>
.profile-wrapper {
    position: fixed;
    width: 400px;
    height: 100%;
    left: 200px;
    background-color: #E8EBEF;
    border: 1px solid #E8EBEF;
    z-index: 10;
}

.profile {
    width: 100%;
    height: 100%;
    background-color: #F3F5F7;
}

.personinfo-wrapper {
    padding: 50px;
    text-align: center;
}

.personinfo {
    color: rgba(0,0,0,.38);
    margin: 10px 0 30px 0;
}

.personinfo .name {
    font-size: 18px;
    margin-bottom: 8px;
}

.personinfo .department {
    margin: 10px 0 15px 0;
}

.extrainfo .desc-label {
    color: rgba(0,0,0,.38);
}

.extrainfo .desc {
    margin: 10px 0;
}

</style>
