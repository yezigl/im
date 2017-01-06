<template>
    <div class="grouplist-wrapper">
        <ul class="grouplist">
            <li class="group" v-for="group in groupList" @click="showGroupInfo(group.id)">
                <img class="large-avatar avatar" :src="group.avatar">
                <div class="name">
                    <span>{{group.name}}</span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'GroupList',
    props: ['groupList'],
    methods: {
        showGroupInfo: function(gid) {
            this.$emit('showGroupInfo', gid);
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
