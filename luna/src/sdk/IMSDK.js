import Config from './Config'

export class IMSDK {
    constructor(vue) {
        this.http = vue.$http;
    }
    start() {
        var source = new EventSource(config.apiServer + '/sse/event/' + config.tk);

        source.onmessage = function(e) {
            console.log(e.data);
        };
        source.onerror = function(e) {
            console.log(e)
        }

    }
    heartBeat() {

    }
    ntp() {
        var s = new Date().getTime();
        this.http.get(config.apiServer + '/api/v1/ntp').then((response) => {
            if (response.data.code == 200 && response.data.data && response.data.data.time) {
                var e = new Date().getTime();
                config.time = response.data.data.time - (e - s) / 2;
            }
        }, (response) => {
            console.error('sync ntp error', response);
        });
    }
}
