<template>
    <div>
        <GroupList></GroupList>
        <GroupInfo></GroupInfo>
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

</style>
