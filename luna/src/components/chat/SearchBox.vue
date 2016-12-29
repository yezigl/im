<template>
    <div class="search-wrapper">
        <div class="search">
            <span class="icon iconfont icon-sousuo" aria-hidden="true"></span>
            <input type="text" name="search">
        </div>
        <div class="addchat">
            <span class="iconfont icon-tianjiayonghu" @click="addChat"></span>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        addChat: function() {
            alert('to be continued')
        }
    }
}
</script>

<style scoped>

.search-wrapper {
    height: 70px;
    padding: 20px 0;
    border-bottom: 1px solid #D1D4D8;
}

.search {
    width: 196px;
    margin: 0 20px;
    position: relative;
    display: inline-block;
}

.search .icon {
    position: absolute;
    top: 4px;
    left: 4px;
    color: #999;
    font-size: 16px;
}

.search input {
    background-color: hsla(0, 0%, 100%, .5);
    border-radius: 4px;
    padding: 5px 22px;
    border: none;
    width: 100%;
    height: 30px;
    border: 1px solid transparent;
}

.search-wrapper .addchat {
    color: #ccc;
    display: inline-block;
    font-size: 20px;
    vertical-align: middle;
    height: 30px;
    cursor: pointer;
}
</style>
