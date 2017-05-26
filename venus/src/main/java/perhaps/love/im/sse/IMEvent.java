package perhaps.love.im.sse;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class IMEvent<T> extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<Integer> uid;
    private T content;
    private EventType type;

    public IMEvent(Object source) {
        super(source);
    }

    public List<Integer> getUid() {
        return uid;
    }

    public void setUid(List<Integer> uid) {
        this.uid = uid;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

}
