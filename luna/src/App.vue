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
@import "../static/css/font-awesome.min.css";

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

.large-avatar {
    width: 40px;
    height: 40px;
    border: 1px solid rgba(0,0,0,.05);
}

.xlarge-avatar {
    width: 80px;
    height: 80px;
    border: 1px solid rgba(0,0,0,.05);
}

.medium-avatar {
    width: 36px;
    height: 36px;
    border: 1px solid rgba(0,0,0,.05);
}

.avatar {
    border-radius: 50%;
}
</style>
