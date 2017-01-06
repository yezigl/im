import Config from './Config'

export class IMSDK {
    constructor(vue) {
        this.http = vue.$http;
    }
    start() {
        var source = new EventSource(config.apiServer + '/sse/event/' + config.uid + '/' + config.tk, { withCredentials: true });

        source.addEventListener('message', function(e) {
            console.log('message', e.data);
            config.bus.$emit('message', e.data);
        });
        source.addEventListener('session', function(e) {
            console.log('session', e.data);
            config.bus.$emit('session', e.data);
        });
        source.addEventListener('error', function(e) {
            console.log('error', e);
        });
    }
    heartBeat() {

    }
    ntp() {
        var s = new Date().getTime();
        this.http.get(config.apiServer + '/api/v1/ntp').then((suc) => {
            var response = suc.data;
            if (response.code == 200 && response.data && response.data.time) {
                var e = new Date().getTime();
                config.time = Math.round(response.data.time - (e - s) / 2);
            }
        }, (response) => {
            console.error('sync ntp error', response);
        });
    }
}
