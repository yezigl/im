<template>
    <div class="groupinfo-wrapper">
        <div class="tab-header">
            <ul class="tab-list">
                <li class="item" @click="changeTab(1)" v-bind:class="{'item-on': currentTab == 1}">资料</li>
                <li class="item" @click="changeTab(2)" v-bind:class="{'item-on': currentTab == 2}">设置</li>
            </ul>
        </div>
        <div class="tab-panel" v-if="currentTab == 1">
            <div class="group-notice">
                <div class="group-notice-header">
                    <span>群公告</span>
                </div>
                <div class="group-notice-content">
                    <span>暂无公告</span>
                </div>
            </div>
            <div class="group-members">
                <div class="group-members-header">成员({{total}}人)</div>
                <div class="group-members-add member">
                    <i class="iconfont icon-tianjia avatar"></i>
                    <span class="name">添加成员</span>
                </div>
                <div class="group-members-list">
                    <div class="member" v-for="member in members">
                        <div class="avatar"><img class="round-avatar base-avatar " :src="member.avatar"></div>
                        <span class="name">{{member.name}}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-panel" @click="changeTab(2)" v-else>
            <div class="group-notice">
                <div class="group-name-header">
                    <span>群名称</span>
                </div>
                <div class="group-name-content">
                    <span>张三、李四、王五</span>
                </div>
                <div class="group-admin-header">
                    <span>群管理员</span>
                </div>
                <div class="group-admin-content">
                    <span>张三</span>
                </div>
            </div>
            <div class="group-options">
                <div class="comp-switch">
                    <span>群置顶</span>
                    <input type="checkbox" readonly v-model="option1" :checked="option1 == true">
                    <div class="switch-round" @click="switchOption(1)"></div>
                </div>
                <div class="comp-switch">
                    <span>消息免打扰</span>
                    <input type="checkbox" readonly v-model="option2" :checked="option2 == true">
                    <div class="switch-round" @click="switchOption(2)"></div>
                </div>
                <div class="comp-switch">
                    <span>保存到群组列表</span>
                    <input type="checkbox" readonly v-model="option3" :checked="option3 == true">
                    <div class="switch-round" @click="switchOption(3)"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'GroupBar',
    data: function() {
        return {
            groupInfo: {},
            members: [],
            total: 3,
            currentTab: 1,
            option1: false,
            option2: false,
            option3: false
        };
    },
    methods: {
        changeTab: function(num) {
            this.currentTab = num;
        },
        switchOption: function(id) {
            switch (id) {
                case 1:
                    this.option1 = !this.option1;
                    break;
                case 2:
                    this.option2 = !this.option2;
                    break;
                case 3:
                    this.option3 = !this.option3;
                    break;
                default:

            }
        }
    },
    mounted: function() {
        this.groupInfo = {
            avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
            name: '某某某',
            email: '账号:moumoumou',
            department: '集团/餐饮平台/到店餐饮技术部/交易与信息技术中心/订单组',
            desc: '这是个性签名，这是个性签名'
        };
        this.members = [
            {
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
                name: '张三'
            },
            {
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
                name: '李四光'
            },
            {
                avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200',
                name: '王五六'
            }
        ];
    }
}
</script>

<style scoped>
.groupinfo-wrapper {

}

.tab-header {
    height: 40px;
    padding: 0;
    border-bottom: 1px solid rgba(0,0,0,.1);
}

.tab-list {
    color: rgba(0,0,0,.54);
}

.item {
    display: inline-block;
    height: 40px;
    line-height: 40px;
    margin: 0 43px;
    font-size: 16px;
    cursor: pointer;
}

.item-on {
    border-bottom: 2px solid #118bfb;
}

.group-notice {
    padding-bottom: 24px;
    margin-left: 20px;
    border-bottom: 1px solid rgba(0,0,0,.05);
    position: relative;
    color: rgba(0,0,0,.38);
}

.group-notice-header, .group-name-header, .group-admin-header {
    margin: 24px 20px 8px 0;
    font-size: 14px;
    line-height: 20px;
    user-select: none;
}

.group-notice-content {
    min-height: 72px;
    height: 120px;
    font-size: 14px;
    line-height: 22px;
}

.group-name-content, .group-admin-content {
    color: rgba(0,0,0,.87);
}

.group-members {

}

.group-members-header {
    color: rgba(0,0,0,.5);
    padding: 24px 0 0 20px;
}

.member {
    display: block;
    user-select: none;
    width: 100%;
    height: 38px;
    padding: 5px 20px;
    box-sizing: border-box;
    position: relative;
    font-size: 12px;
    cursor: pointer;
}

.member .name {
    max-width: 60%;
    color: rgba(0,0,0,.87);
    line-height: 28px;
    height: 28px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: pre;
}

.member .iconfont {
    font-size: 28px;
    color: rgba(0,0,0,.5);
    line-height: 28px;
    height: 28px;
}

.member .avatar {
    float: left;
    margin-right: 10px;
}

.group-options {
    margin: 0 15px 0 20px;
    padding: 20px 0 10px 0;
}

.comp-switch {
    position: relative;
    display: block;
    padding-bottom: 14px;
}

.comp-switch span {
    vertical-align: middle;
    cursor: pointer;
}

.switch-round {
    position: relative;
    float: right;
    display: inline-block;
    cursor: pointer;
    height: 16px;
    width: 36px;
    border-radius: 100px;
    background-color: rgba(0,0,0,.24);
    -webkit-transition: .2s;
    transition: .2s;
}

.switch-round::before {
    position: absolute;
    content: "";
    height: 14px;
    width: 14px;
    left: 1px;
    top: 1px;
    border-radius: 100px;
    background-color: #f8f8f8;
    -webkit-transition: .2s;
    transition: .2s;
}

.comp-switch input {
    display: none;
}

.comp-switch input:checked+.switch-round {
    background-color: #118bfb;
}

.comp-switch input:checked+.switch-round:before {
    transform: translateX(20px);
}

</style>
