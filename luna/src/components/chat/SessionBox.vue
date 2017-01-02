<template>
    <div>
        <div class="sessionbox-wrapper">
            <div class="sessionbox">
                <SearchBox></SearchBox>
                <SessionList @showSession="showSession" :sessionList="sessionList" :curSession="curSession"></SessionList>
            </div>
        </div>
        <div class="messagebox-wrapper">
            <MessageBox class="messagebox" v-if="curSession.id" @showLastest="showLastest" :curSession="curSession"></MessageBox>
            <Empty v-else></Empty>
        </div>
    </div>
</template>

<script>
import SearchBox from './SearchBox'
import SessionList from './SessionList'
import MessageBox from './MessageBox'
import Empty from '../common/Empty'

export default {
    data: function() {
        return {
            curSession: {},
            sessionListWrapper: {sessionList: []},
            offset: 0,
            limit: 10
        };
    },
    components: {
        SearchBox, SessionList, MessageBox, Empty
    },
    methods: {
        showSession: function(session, index) {
            if (!session) {
                location.href = '/#/';
                return;
            }
            if (index !== undefined) {
                session.unread = 0;
            }
            this.curSession = session;
            location.href = '/#/' + session.type + '/' + session.id;
            this.showLastest();
        },
        showLastest: function() {
            setTimeout(function() {
                var div = document.getElementById('messageListBox');
                div.scrollTop = div.scrollHeight;
            }, 100);
        },
        fetchSessions: function(callback) {
            console.log(this.offset, this.limit);
            this.$http.get(config.apiServer + '/api/v1/sessions', {
                params: {offset: this.offset, limit: this.limit}
            }, suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    callback(response.data);
                }
            }, error => {
                console.log('error');
                this.offset += this.limit;
            });
        }
    },
    computed: {
        sessionList: function() {
            this.fetchSessions(function(sessionList) {
                this.sessionListWrapper.sessionList = sessionList;
                this.offset += this.limit;
                var match = /#\/(\w+)\/(\d+)(.*)?/.exec(location.hash);
                if (match) {
                    var filterd = this.sessionListWrapper.sessionList.filter(e => e.id == match[2]);
                    this.showSession(filterd.length == 0 ? null : filterd[0]);
                } else {
                    this.showSession(null);
                }
            });
            return this.sessionListWrapper.sessionList;
        }
    },
    mounted: function() {
        var source = new EventSource(config.apiServer + '/sse/event/' + config.tk);

        source.onmessage = function(e) {
            console.log(e.data);
        };
        source.onerror = function(e) {
            console.log(e)
        }

        // this.sessionList = [
        //     {
        //         id: 1000,
        //         avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile4/99287bb1-8aea-47c6-b628-eaa5d3d496d5',
        //         unread: 99,
        //         name: '张三、李四、王五',
        //         lastName: '张三',
        //         lastContent: '没事 这个不影响发布',
        //         time: '昨天',
        //         type: 'groupchat'
        //     }, {
        //         id: 1001,
        //         avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile9/1129f264-bdab-43be-a481-23fd69020aaa',
        //         unread: 0,
        //         name: '张三',
        //         lastName: '张三',
        //         lastContent: '没事 这个不影响发布',
        //         time: '16/9/21',
        //         type: 'chat'
        //     }
        // ];
    }
}
</script>

<style scoped>
.sessionbox-wrapper {
    position: absolute;
    width: 100%;
    height: 100%;
}

.sessionbox {
    position: absolute;
    width: 280px;
    height: 100%;
    background-color: #E8EBEF;
    border-right: 1px solid #D1D4D8;
    z-index: 1;
}

.messagebox-wrapper {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: #E8EBEF;
}

.messagebox {
    position: absolute;
    left: 280px;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: #F3F5F7;
}
</style>
