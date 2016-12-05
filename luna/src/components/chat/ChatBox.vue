<template>
    <div>
        <div class="chatbox-wrapper">
            <div class="chatbox">
                <SearchBox></SearchBox>
                <ChatList></ChatList>
            </div>
        </div>
        <div class="messagebox-wrapper">
            <MessageBox></MessageBox>
        </div>
    </div>
</template>

<script>
import SearchBox from './SearchBox'
import ChatList from './ChatList'
import MessageBox from './MessageBox'

export default {
    components: {
        SearchBox, ChatList, MessageBox
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
