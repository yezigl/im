package perhaps.love.im.sse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.alibaba.fastjson.JSON;

@Component
public class IMEventListener implements DisposableBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    Map<Integer, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @EventListener
    public void eventHandler(IMEvent<?> event) throws IOException {
        logger.info("receive {} event to {}", event.getType(), event.getUid());
        List<Integer> uids = event.getUid();
        for (Integer uid : uids) {
            SseEmitter emitter = sseEmitters.get(uid);
            if (emitter == null) {
                logger.warn("{} sse is null", uid);
                continue;
            }
            SseEventBuilder builder = SseEmitter.event().name(event.getType().getName())
                    .data(JSON.toJSONString(event.getContent()));
            emitter.send(builder);
        }
    }

    public void addSseEmitters(int uid, SseEmitter sseEmitter) {
        sseEmitters.put(uid, sseEmitter);
    }

    public void removeSseEmitters(int uid) {
        sseEmitters.remove(uid);
    }

    @Override
    public void destroy() throws Exception {
        sseEmitters.values().forEach(SseEmitter::complete);
    }
}
