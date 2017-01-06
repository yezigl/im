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
            sessionList: [],
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
            location.href = '/#/' + session.type.toLowerCase() + '/' + session.id;
            this.showLastest();
            this.readSession(session);
        },
        showLastest: function() {
            setTimeout(function() {
                var div = document.getElementById('messageListBox');
                div.scrollTop = div.scrollHeight;
            }, 100);
        },
        fetchSessions: function(callback) {
            console.log('fetchSessions', this.offset, this.limit);
            this.$http.get(config.apiServer + '/api/v1/sessions', {
                params: {offset: this.offset, limit: this.limit}
            }).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    callback(response.data.reverse());
                }
            }, error => {
                console.log('fetchSessions error', error);
            });
        },
        readSession: function(session) {
            if (session.unread > 0) {
                this.$http.post(config.apiServer + '/api/v1/sessions/' + session.id + '/read');
                session.unread = 0;
            }
        }
    },
    mounted: function() {
        var _this = this;
        this.fetchSessions(function(sessionList) {
            _this.sessionList = sessionList;
            var match = /#\/(\w+)\/(\d+)(.*)?/.exec(location.hash);
            if (match) {
                var filterd = sessionList.filter(e => e.id == match[2]);
                _this.showSession(filterd.length == 0 ? null : filterd[0]);
            } else {
                _this.showSession(null);
            }
        });
        config.bus.$on('session', function(data) {
            var session = JSON.parse(data);
            var index = _this.sessionList.findIndex(e => e.id == session.id);
            if (index >= 0) {
                _this.sessionList.splice(index, 1);
                _this.sessionList.unshift(session);
            } else {
                _this.sessionList.unshift(session);
            }
            if (_this.curSession.id == session.id) {
                _this.curSession = session;
                _this.readSession(session)
            }
        });
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
