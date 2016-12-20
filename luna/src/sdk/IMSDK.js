import SessionManager from './SessionManager'

export default {
    name: 'IMSDK',
    data: {
        SessionType: SessionManager.SessionType
    },
    start: function() {
        var source = new EventSource('/sse/event/' + 1);

        source.onmessage = function(e) {
          console.log(e.data);
        };
        source.onerror = function(e) {
            console.log(e)
        }

    },
    heartBeat: function() {

    }
}
