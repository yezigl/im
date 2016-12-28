export default {
    name: 'LoginSDK',
    data: function() {

    },
    login: function() {
        return true;
        var cookie = this.parseCookie();
        if (!cookie.uid || !cookie.tk) {
            location.href = 'http://x.focus.cn/login';
            return false;
        }
        window.config.uid = cookie.uid;
        window.config.tk = cookie.tk;
        return true;
    },
    logout: function() {
        location.href = 'http://x.focus.cn/logout';
    },
    parseCookie: function() {
        var cookies = document.cookie.split(';');
        var uid, tk;
        for (var cookie of cookies) {
            var kv = cookie.trim().split('=');
            if (kv[0] == 'im_uid') {
                uid = kv[1];
            }
            if (kv[0] == 'tk') {
                tk = kv[1];
            }
        }
        return {'uid': uid, 'tk': tk};
    }
}
