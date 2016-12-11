export default {
    name: 'MessageManager',
    MessageType: {
        TYPE_TEXT: 'text',
        TYPE_IMAGE: 'image'
    },
    getChatMessageList: function(toUid) {

    },
    getGroupChatMessageList: function(gid) {

    },
    sendMessage: function(message) {

    },
    sendTextMessage: function(message) {
        message.type = MessageType.TYPE_TEXT;
    },
    sendImageMessage: function(message) {
        message.type = MessageType.TYPE_IMAGE;
    },
    uploadFile: function(file, formData) {

    }
}
