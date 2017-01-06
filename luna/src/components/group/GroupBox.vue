<template>
    <div>
        <GroupList :groupList="groupList"></GroupList>
        <GroupInfo :groupInfo="groupInfo"></GroupInfo>
    </div>
</template>

<script>
import GroupList from './GroupList'
import GroupInfo from './GroupInfo'

export default {
    name: 'GroupBox',
    data: function() {
        return {
            groupList: [],
            groupInfo: {}
        };
    },
    components: {
        GroupList, GroupInfo
    },
    methods: {
        showGroupInfo: function(gid) {
            this.$http.get(config.apiServer + '/api/v1/groups/' + gid).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.groupInfo = response.data;
                }
            }, error => {

            });
        }
    },
    mounted: function() {
        this.$http.get(config.apiServer + '/api/v1/groups').then(suc => {
            var response = suc.data;
            if (response.code == 200 && response.data) {
                this.groupList = response.data;
            }
        }, error => {

        });
    }
}
</script>

<style scoped>

</style>
