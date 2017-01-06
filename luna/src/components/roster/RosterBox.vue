<template>
    <div>
        <RosterList @showRoster="showRoster" :rosterList="rosterList"></RosterList>
        <RosterInfo v-if='rosterInfo.uid' :rosterInfo="rosterInfo"></RosterInfo>
        <Empty v-else></Empty>
    </div>
</template>

<script>
import RosterList from './RosterList'
import RosterInfo from './RosterInfo'
import Empty from '../common/Empty'

export default {
    name: 'RosterBox',
    data: function() {
        return {
            rosterList: [],
            rosterInfo: {}
        };
    },
    components: {
        RosterList, RosterInfo, Empty
    },
    methods: {
        showRoster: function(roster, index) {
            console.log(roster)
            this.rosterInfo = roster;
        }
    },
    mounted: function() {
        this.$http.get(config.apiServer + '/api/v1/rosters').then(suc => {
            var response = suc.data;
            if (response.code == 200 && response.data) {
                this.rosterList = response.data;
            }
        }, error => {

        });
    }
}
</script>

<style scoped>
.empty {
    position: absolute;
    left: 280px;
    right: 0;
    top: 0;
    bottom: 0;
    background-color: #F3F5F7;
}
</style>
