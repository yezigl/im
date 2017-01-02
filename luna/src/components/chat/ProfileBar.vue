<template>
    <div class="personinfo-wrapper">
        <img class="round-avatar xlarge-avatar" @click="showProfile" :src="personInfo.avatar" />
        <div class="personinfo">
            <div class="name">{{personInfo.name}}</div>
            <div class="email">{{personInfo.email}}</div>
            <div class="department">{{personInfo.department}}</div>
        </div>
        <div class="extrainfo">
            <div class="desc-label">个性签名</div>
            <div class="desc">{{personInfo.desc}}</div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ProfileBar',
    props: ['curSession'],
    data: function() {
        return {
            pp: {}
        };
    },
    methods: {
        showProfile: function() {

        }
    },
    computed: {
        personInfo: function() {
            this.$http.get(config.apiServer + '/api/v1/rosters/' + this.curSession.id).then(suc => {
                var response = suc.data;
                if (response.code == 200 && response.data) {
                    this.pp = response.data;
                }
            });
            return this.pp;
        }
    }
}
</script>

<style scoped>
.personinfo-wrapper {
    padding: 30px;
    text-align: center;
}

.personinfo {
    color: rgba(0,0,0,.38);
    margin: 10px 0 30px 0;
}

.personinfo .name {
    font-size: 18px;
    margin-bottom: 8px;
}

.personinfo .department {
    margin: 10px 0 15px 0;
}

.extrainfo .desc-label {
    color: rgba(0,0,0,.38);
    text-align: left;
}

.extrainfo .desc {
    text-align: left;
    margin: 10px 0;
}
</style>
