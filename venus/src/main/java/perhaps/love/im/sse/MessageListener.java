package perhaps.love.im.sse;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import perhaps.love.im.dto.convertor.MessageConvertor;
import perhaps.love.im.service.GroupService;

@Component
public class MessageListener {

    @Resource
    ApplicationEventPublisher applicationEventPublisher;

    @Resource
    MessageConvertor messageConvertor;

    @Resource
    GroupService groupService;

    public void onMessage() {
    }
}
