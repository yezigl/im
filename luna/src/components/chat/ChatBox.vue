<template>
    <div>
        <div class="chatbox-wrapper">
            <div class="chatbox">
                <SearchBox></SearchBox>
                <ChatList @showChat="showChat"></ChatList>
            </div>
        </div>
        <div class="messagebox-wrapper">
            <Empty v-if="currentChat.id == 0"></Empty>
            <MessageBox v-else v-bind:currentChat="currentChat"></MessageBox>
        </div>
    </div>
</template>

<script>
import SearchBox from './SearchBox'
import ChatList from './ChatList'
import MessageBox from './MessageBox'
import Empty from '../common/Empty'

export default {
    data: function() {
        return {
            currentChat: {
                id: 0
            }
        };
    },
    components: {
        SearchBox, ChatList, MessageBox, Empty
    },
    methods: {
        showChat: function(chat) {
            this.currentChat = chat;
        }
    }
}
</script>

<style scoped>
.chatbox-wrapper {
    position: absolute;
    width: 100%;
    height: 100%;
}

.chatbox {
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
</style>
