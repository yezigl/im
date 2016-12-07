<template>
    <div>
        <div class="groupbox-wrapper">
            <div class="groupbox">
                <GroupList></GroupList>
            </div>
        </div>
        <div class="groupinfo-wrapper">
            <GroupInfo></GroupInfo>
        </div>
    </div>
</template>

<script>
import GroupList from './GroupList'
import GroupInfo from './GroupInfo'

export default {
    name: 'GroupBox',
    components: {
        GroupList, GroupInfo
    }
}
</script>

<style scoped>
.groupbox-wrapper {
    position: absolute;
    width: 100%;
    height: 100%;
}

.groupbox {
    position: absolute;
    width: 280px;
    height: 100%;
    background-color: #E8EBEF;
    border-right: 1px solid #D1D4D8;
    z-index: 1;
}

.groupinfo-wrapper {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: #E8EBEF;
}
</style>
