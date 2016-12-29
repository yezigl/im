export default {
    name: 'LoginSDK',
    data: function() {

    },
    login: function() {
        var cookie = this.parseCookie();
        if (!cookie.uid || !cookie.tk) {
            location.href = config.apiServer + '/login';
            return false;
        }
        window.config.uid = cookie.uid;
        window.config.tk = cookie.tk;
        return true;
    },
    logout: function() {
        location.href = config.apiServer + '/logout';
    },
    parseCookie: function() {
        var cookies = document.cookie.split(';');
        var uid, tk;
        for (var cookie of cookies) {
            var kv = cookie.trim().split('=');
            if (kv[0] == 'im_uid') {
                var us = kv[1].split('|');
                uid = us[0];
            }
            if (kv[0] == 'im_tk') {
                tk = kv[1];
            }
        }
        return {'uid': uid, 'tk': tk};
    }
}
