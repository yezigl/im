<template>
    <div class="rosterlist-wrapper">
        <ul class="rosterlist">
            <li class="roster" v-for="(roster, index) in rosterList" @click="showRoster(roster, index)">
                <img class="large-avatar avatar" :src="roster.avatar">
                <div class="name">
                    <span>{{roster.name}}</span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'RosterList',
    props: ['rosterList'],
    methods: {
        showRoster: function(roster, index) {
            this.$emit("showRoster", roster, index);
        }
    }
}
</script>

<style scoped>
.rosterlist-wrapper {
    width: 100%;
    top: 0;
    bottom: 0;
    position: absolute;
}

.rosterlist {
    position: absolute;
    width: 280px;
    height: 100%;
    background-color: #E8EBEF;
    border-right: 1px solid #D1D4D8;
    z-index: 1;
    min-height: 100%;
    margin: 0;
    padding: 0;
    overflow: auto;
}

.rosterlist li {
    list-style: none;
}

.roster {
    height: 70px;
    padding: 15px 20px;
    position: relative;
    cursor: pointer;
}

.roster .name {
    display: inline-block;
}

.roster:HOVER {
    background-color: #DBE7F1;
}
</style>
