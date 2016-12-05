<template>
    <div class="search-wrapper">
        <div class="search">
            <span class="fa fa-search icon" aria-hidden="true"></span>
            <input type="text" name="search">
        </div>
        <div class="addchat">
            <span class="fa fa-user-plus"></span>
        </div>
    </div>
</template>

<script>
export default {

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
    top: 8px;
    left: 4px;
    color: #999;
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
