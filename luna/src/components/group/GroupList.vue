<template>
    <div class="grouplist-wrapper">
        <ul class="grouplist">
            <li class="group" @click="showGroupInfo">
                <img class="large-avatar avatar" src="http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile4/99287bb1-8aea-47c6-b628-eaa5d3d496d5">
                <div class="name">
                    <span>辛孟莹、李德华、于吉星</span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'GroupList',
    methods: {
        showGroupInfo: function() {

        }
    }
}
</script>

<style scoped>
.grouplist-wrapper {
    width: 100%;
    top: 0;
    bottom: 0;
    position: absolute;
}

.grouplist {
    position: absolute;
    width: 280px;
    height: 100%;
    background-color: #E8EBEF;
    border-right: 1px solid #D1D4D8;
    z-index: 1;
    min-height: 100%;
    margin: 0;
    padding: 0;
}

.grouplist li {
    list-style: none;
}

.group {
    height: 70px;
    padding: 15px 20px;
    position: relative;
    cursor: pointer;
}

.group .name {
    display: inline-block;
}

.group:HOVER {
    background-color: #DBE7F1;
}
</style>
