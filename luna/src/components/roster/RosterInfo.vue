<template>
    <div class="rosterinfo-wrapper">
        <div class="rosterinfo">
            <div class="personinfo-wrapper">
                <img class="round-avatar xlarge-avatar" :src="rosterInfo.avatar" />
                <div class="personinfo">
                    <div class="name">{{rosterInfo.name}}</div>
                    <div class="email">{{rosterInfo.email}}</div>
                    <div class="department">{{rosterInfo.department}}</div>
                </div>
                <div class="extrainfo">
                    <div class="desc-label">个性签名</div>
                    <div class="desc">{{rosterInfo.desc}}</div>
                </div>
                <button class="chat-button button button-primary" v-if="rosterInfo.uid != me" @click="makeChat(rosterInfo)">发起聊天</button>
            </div>
        </div>
    </div>
</template>

<script>
import {SessionType} from '../../sdk/Config'

export default {
    name: 'RosterInfo',
    props: ['rosterInfo'],
    data: function() {
        return {
            me: config.uid
        };
    },
    methods: {
        makeChat: function(roster) {
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
.rosterinfo-wrapper {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: #E8EBEF;
}

.rosterinfo {
    position: absolute;
    left: 280px;
    right: 0;
    top: 0;
    bottom: 0;
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

.chat-button {
    margin-top: 20px;
}
</style>
