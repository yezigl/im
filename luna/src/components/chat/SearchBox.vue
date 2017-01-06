<template>
    <div class="search-wrapper">
        <div class="search">
            <span class="icon iconfont icon-sousuo" aria-hidden="true"></span>
            <input type="text" name="search">
        </div>
        <div class="addchat">
            <span class="iconfont icon-tianjiaqunliao" @click="addChat" title="发起聊天"></span>
        </div>
        <div v-if="add" class="addchat-wrapper" @keyup.esc="add = false">
            <div class="header">
                <div class="title">发起聊天</div>
                <i class="iconfont icon-guanbi" @click="add = false"></i>
            </div>
            <div class="content">
                <div class="content-wrapper">
                    <div class="wrapper-selectMembers">
                        <div class="title-bar">
                            <span>选择成员</span>
                        </div>
                        <div class="suggest">
                            <div class="ctn-ipt">
                                <i class="iconfont icon-sousuo"></i>
                                <input type="text" autocomplete="off" @keyup="suggest" v-model="keyword" placeholder="请输入账号全称或中文名进行搜索">
                            </div>
                        </div>
                        <div class="comp-tab start-chat-org-contact">
                            <div class="tab-panel">
                                <div class="start-chat-contact">
                                    <div class="start-chat-person" v-for="roster in rosterList">
                                        <label class="comp-checkbox">
                                            <input type="checkbox" @click="select(roster)">
                                        </label>
                                        <div class="avatar">
                                            <img class="round-avatar large-avatar" :src="roster.avatar">
                                        </div>
                                        <span class="name">{{roster.name}}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="wrapper-selected">
                        <div class="title">
                            <span>已选成员 (&nbsp;</span>
                            <span>{{rosterSelected.length}}</span>
                            <span>&nbsp;)</span>
                        </div>
                        <div class="wrapper-selectNode">
                            <div class="selectNodes">
                                <div v-for="roster in rosterSelected">
                                    <div class="section-selectNode">
                                        <i class="iconfont icon-guanbi" @click="select(roster)"></i>
                                        <div class="avatar"><img class="large-avatar round-avatar" :src="roster.avatar"></div>
                                        <span class="name">{{roster.name}}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <button class="button" @click="add = false">取 消</button>
                <button class="button button-primary" :disabled="rosterSelected.length == 0" @click="makeGroup">确 定</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            add: false,
            keyword: '',
            rosterList: [],
            rosterSelected: []
        };
    },
    methods: {
        addChat: function() {
            this.add = true;
        },
        suggest: function(event) {
            var value = event.target.value;
            if (!value) {
                this.rosterList = [];
                return;
            }
            this.$http.get(config.apiServer + '/api/v1/search/user', {
                params: {keyword: value}
            }).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.rosterList = response.data;
                }
            });
        },
        select: function(roster) {
            var index = this.rosterSelected.findIndex(e => e.uid == roster.uid);
            if (index < 0) {
                this.rosterSelected.push(roster);
            } else {
                this.rosterSelected.splice(index, 1);
            }
        },
        makeGroup: function() {
            var name = [];
            var members = [];
            for (var roster of this.rosterSelected) {
                name.push(roster.name);
                members.push(roster.uid);
            }
            console.log(name, members);
            this.$http.post(config.apiServer + '/api/v1/groups', {
                name: name.join('、').substring(0, 20) + '...',
                members: members
            }).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.add = false;
                }
            });
        }
    },
    mounted: function() {
    }
}
</script>

<style scoped>

.search-wrapper {
    height: 70px;
    padding: 20px 0;
    border-bottom: 1px solid #D1D4D8;
}

.search {
    width: 196px;
    margin: 0 20px;
    position: relative;
    display: inline-block;
}

.search .icon-sousuo {
    position: absolute;
    top: 4px;
    left: 4px;
    color: #999;
    font-size: 16px;
}

.search input {
    background-color: hsla(0, 0%, 100%, .5);
    border-radius: 4px;
    padding: 5px 22px;
    border: none;
    width: 100%;
    height: 30px;
    border: 1px solid transparent;
}

.search-wrapper .addchat {
    color: rgba(0,0,0,.38);
    display: inline-block;
    font-size: 24px;
    vertical-align: middle;
    height: 30px;
    line-height: 30px;
    cursor: pointer;
}

.search-wrapper .addchat:hover {
    color: #118bfb;
}

.addchat-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1000;
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    overflow: auto;
    background: #f8f8f8;
    padding-top: 50px;
}

.header {
    background: #fff;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1000;
    width: 100%;
    height: 50px;
    border-bottom: 1px solid #e5e5e5;
    box-sizing: border-box;
}

.header .title{
    text-align: center;
    line-height: 50px;
    font-size: 18px;
    position: relative;
}

.header .icon-guanbi {
    position: absolute;
    top: 10px;
    right: 25px;
    color: rgba(0,0,0,.38);
    cursor: pointer;
    font-size: 20px;
}

.content {
    width: 720px;
    box-sizing: border-box;
    height: 100%;
    position: relative;
    margin: 15px auto 0;
}

.content-wrapper {
    width: 722px;
    position: absolute;
    top: 0;
    bottom: 100px;
    background: #fff;
    border: 1px solid rgba(0,0,0,.1);
    box-sizing: border-box;
}

.wrapper-selectMembers {
    width: 440px;
    height: 100%;
    float: left;
    position: relative;
    display: flex;
    flex-direction: column;
}

.wrapper-selected {
    width: 280px;
    height: 100%;
    display: inline-block;
    border-left: 1px solid rgba(0,0,0,.1);
    box-sizing: border-box;
    overflow: auto;
}

.title-bar {
    padding: 16px 0 14px 20px;
    font-size: 16px;
}

.suggest {
    padding: 0 20px;
}

.suggest .ctn-ipt {
    position: relative;
    background: #eee;
    border-radius: 4px;
}

.suggest .ctn-ipt .icon-sousuo {
    position: absolute;
    top: 6px;
    left: 8px;
    color: #999;
    font-size: 16px;
}

.suggest .ctn-ipt input {
    border: 1px solid rgba(0,0,0,.1);
    border-radius: 4px;
    height: 35px;
    padding: 5px 30px;
    background-color: #fff;
    width: 100%;
}

.comp-tab {
    position: relative;
    flex: 1;
    height: 100%;
    box-sizing: border-box;
    overflow: auto;
    margin-top: 10px;
}

.tab-panel {
    height: 100%;
    box-sizing: border-box;
    padding: 0 40px;
}

.tab-panel .start-chat-contact {
    overflow: auto;
}

.tab-panel .start-chat-person {
    padding: 0;
    height: 52px;
    line-height: 52px;
}

.tab-panel .start-chat-person {
    vertical-align: middle;
}

.tab-panel .start-chat-person:hover {
    background-color: #F4FAFF;
}

.tab-panel .comp-checkbox {
    margin: 0 12px 0 0;
    display: inline-block;
    user-select: none;
}

.tab-panel .comp-checkbox {
    width: 16px;
    height: 16px;
    border-radius: 100%;
}

.tab-panel .avatar {
    margin-right: 10px;
    display: inline-block;
}

.tab-panel .name {
    font-size: 14px;
    cursor: pointer;
}

.wrapper-selected .title {
    position: absolute;
    width: 280px;
    padding: 16px 20px 18px;
    box-sizing: border-box;
    background-color: #fff;
    font-size: 16px;
}

.wrapper-selected .wrapper-selectNode {
    height: 100%;
    padding-top: 56px;
    overflow: auto;
    box-sizing: border-box;
}

.wrapper-selected .section-selectNode {
    width: 100%;
    height: 52px;
    font-size: 14px;
    line-height: 52px;
}

.wrapper-selected .icon-guanbi {
    margin: 0 12px 0 22px;
    background-color: #ff5d4a;
    font-size: 16px;
    cursor: pointer;
    border-radius: 50%;
    color: #fff;
    padding: 0 1px 2px 1px;
}

.wrapper-selected .avatar {
    margin-right: 10px;
    display: inline-block;
}

.footer {
    position: absolute;
    bottom: 30px;
    width: 340px;
    left: 50%;
    margin-left: -170px;
    text-align: center;
}

.button:disabled {
    color: #ccc;
    background-color: #f3f5f7;
    border-color: #047eee;
}
</style>
