package perhaps.love.im.entity;

public enum SessionType {

    CHAT(1), GROUPCHAT(2);

    private int code;

    private SessionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SessionType valueOfCode(int code) {
        for (SessionType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return CHAT;
    }
}
