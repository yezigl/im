package perhaps.love.im.dto;

public enum Status {

    SUCCESS(200, "success"), //
    ERROR(500, "系统错误"), //
    NOT_FOUND(401, "用户不存在或用户名密码错误"),
    FILE_UPLOAD_ERROR(404, "上传图片失败");

    private int code;
    private String message;

    private Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
