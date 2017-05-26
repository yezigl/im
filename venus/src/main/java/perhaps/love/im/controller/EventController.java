package perhaps.love.im.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import perhaps.love.im.sse.IMEventListener;

@Controller
public class EventController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${sse.timeout}")
    long sseTimeout;

    @Resource
    IMEventListener messageEventListener;

    @RequestMapping(value = "/sse/event/{uid}/{token}")
    public SseEmitter event(@PathVariable int uid, @PathVariable String token) {
        SseEmitter sseEmitter = new SseEmitter(sseTimeout);
        sseEmitter.onTimeout(() -> {
            sseEmitter.complete();
            messageEventListener.removeSseEmitters(uid);
        });
        messageEventListener.addSseEmitters(uid, sseEmitter);
        logger.info("create {} sse", uid);
        return sseEmitter;
    }
}
