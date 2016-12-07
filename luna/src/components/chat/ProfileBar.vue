<template>
    <div class="personinfo-wrapper">
        <img class="avatar xlarge-avatar" @click="showProfile" :src="personInfo.avatar" />
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
    data: function() {
        return {
            personInfo: {}
        };
    },
    methods: {
        showProfile: function() {

        }
    },
    mounted: function() {
        this.personInfo = {
            avatar: 'http://s3-img.meituan.net/v1/mss_491cda809310478f898d7e10a9bb68ec/profile9/1129f264-bdab-43be-a481-23fd69020aaa',
            name: '某某某',
            email: '账号:moumoumou',
            department: '集团/餐饮平台/到店餐饮技术部/交易与信息技术中心/订单组',
            desc: '这是个性签名，这是个性签名'
        };
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
