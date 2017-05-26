package perhaps.love.im.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JsonResponse<T> {

    private Integer code;
    private String message;
    @JsonInclude(Include.NON_NULL)
    private T data;

    public static <T> JsonResponse<T> success() {
        JsonResponse<T> response = new JsonResponse<>();
        response.setStatus(Status.SUCCESS);
        return response;
    }

    public static <T> JsonResponse<T> success(T data) {
        JsonResponse<T> response = new JsonResponse<>();
        response.setData(data);
        response.setStatus(Status.SUCCESS);
        return response;
    }

    public static <T> JsonResponse<T> error(Status status) {
        JsonResponse<T> response = new JsonResponse<>();
        response.setStatus(status);
        return response;
    }

    public void setStatus(Status status) {
        this.setCode(status.getCode());
        this.setMessage(status.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
