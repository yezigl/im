<template>
    <div id="app">
        <Toolbar></Toolbar>
        <div id="main">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
import Toolbar from './components/Toolbar'
import ChatBox from './components/chat/ChatBox'

export default {
    name: 'app',
    components: {
        Toolbar, ChatBox
    }
}
</script>

<style>
html, body {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}

#app {
    width: 100%;
    height: 100%;
}

#main {
    position: absolute;
    left: 60px;
    top: 0;
    right: 0;
    bottom: 0;
}
</style>
