<template>
    <div id="toolbar">
        <div class="userinfo">
            <img class="large-avatar avatar" src="http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile14/a306ae07-f678-4ac3-b3e6-c1d6deacd25c_200_200" />
        </div>
        <div class="tool">
            <div class="app-item active">
                <div class="activebar"></div>
                <a href="/#/chat">消息</a>
            </div>
            <div class="app-item">
                <div class="activebar"></div>
                <a href="/#/group">群组</a>
            </div>
            <div class="app-item">
                <div class="activebar"></div>
                <a href="/#/roster">通讯录</a>
            </div>
        </div>
        <div class="settings">
            <div class="app-item">设置</div>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#toolbar {
    position: absolute;
    width: 60px;
    height: 100%;
    background-color: #2B3D52;
}

.userinfo {
    height: 70px;
    line-height: 70px;
    text-align: center;
    vertical-align: middle;
    border-bottom: 1px solid #3F5063;
}

.tool {
    border-bottom: 1px solid #3F5063;
}

.app-item {
    width: 100%;
    height: 46px;
    line-height: 46px;
    text-align: center;
    cursor: pointer;
    color: #fff;
}

.app-item a {
    color: #ccc;
    text-decoration: none;
}

.app-item:HOVER, .app-item.active {
    background-color: #22486C;
}

.app-item a:hover, .app-item.active a {
    color: #fff;
}

.active .activebar {
    width: 4px;
    height: 46px;
    background-color: #00B5FF;
    position: fixed;
}

.settings {
    position: absolute;
    bottom: 0px;
    width: 60px;
    padding-top: 10px;
    border-top: 1px solid #3F5063;
}

</style>
