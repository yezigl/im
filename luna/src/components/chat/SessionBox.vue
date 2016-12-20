<template>
    <div>
        <div class="sessionbox-wrapper">
            <div class="sessionbox">
                <SearchBox></SearchBox>
                <SessionList @showSession="showSession"></SessionList>
            </div>
        </div>
        <div class="messagebox-wrapper">
            <Empty v-if="curSession.id == 0"></Empty>
            <MessageBox class="messagebox" v-else v-bind:curSession="curSession"></MessageBox>
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
            curSession: {
                id: 0
            }
        };
    },
    components: {
        SearchBox, SessionList, MessageBox, Empty
    },
    methods: {
        showSession: function(session) {
            this.curSession = session;
            console.log(session.id)
            var div = document.getElementById('messageListBox');
            div.scrollTop = div.scrollHeight;
        }
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
