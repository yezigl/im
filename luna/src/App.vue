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

export default {
    name: 'app',
    components: {
        Toolbar
    }
}
</script>

<style>
@import "../static/css/bootstrap.css";
@import "../static/css/iconfont.css";

html, body {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    font-family: Helvetica Neue,Helvetica,Arial,PingFang SC,Heiti SC,Hiragino Sans GB,Microsoft YaHei,\\5FAE\8F6F\96C5\9ED1,sans-serif;
    -webkit-font-smoothing: antialiased;
}

pre {
    font-family: Helvetica Neue,Helvetica,Arial,PingFang SC,Heiti SC,Hiragino Sans GB,Microsoft YaHei,\\5FAE\8F6F\96C5\9ED1,sans-serif;
}

ul, li {
    padding: 0;
    margin: 0;
}

li {
    list-style: none;
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

.large-avatar {
    width: 40px;
    height: 40px;
}

.xlarge-avatar {
    width: 80px;
    height: 80px;
}

.medium-avatar {
    width: 36px;
    height: 36px;
}

.base-avatar {
    width: 28px;
    height: 28px;
}

.round-avatar {
    border-radius: 50%;
    border: 1px solid rgba(0,0,0,.05);
}

::-webkit-scrollbar-track {
    background-color:transparent;
    border-radius: 0
}

::-webkit-scrollbar {
    width: 8px;
    height: 8px
}

::-webkit-scrollbar-thumb {
    background-color: rgba(92,110,133,.3);
    border-radius: 4px;
    outline: 0;
    outline-offset: -2px;
    border: none
}

::-webkit-scrollbar-thumb:hover {
    background-color: rgba(60,122,198,.6);
    border-radius: 4px
}
</style>
