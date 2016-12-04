<template>
    <div>
        <div class="wrapper-chatbox">
            <SearchBox></SearchBox>
            <ChatList></ChatList>
        </div>
        <div class="wrapper-messagebox">
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
.wrapper-chatbox {
    position: absolute;
    width: 100%;
    height: 100%;
}

.wrapper-messagebox {
    position: absolute;
    width: 100%;
    height: 100%;
}
</style>
