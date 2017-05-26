package perhaps.love.im.entity;

public enum MessageType {

    TEXT(1), IMAGE(2), FILE(3), EMOJI(4);

    private int code;

    private MessageType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MessageType valueOfCode(int code) {
        for (MessageType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return TEXT;
    }
}
