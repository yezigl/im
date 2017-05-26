package perhaps.love.im.sse;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import perhaps.love.im.Constants;
import perhaps.love.im.dto.MessageDto;
import perhaps.love.im.dto.SessionDto;
import perhaps.love.im.dto.convertor.MessageConvertor;
import perhaps.love.im.dto.convertor.SessionConvertor;
import perhaps.love.im.entity.Group;
import perhaps.love.im.entity.Message;
import perhaps.love.im.entity.Session;
import perhaps.love.im.entity.SessionType;
import perhaps.love.im.entity.User;
import perhaps.love.im.service.GroupService;
import perhaps.love.im.service.SessionService;
import perhaps.love.im.service.UserService;

@Component
public class AsyncSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    ApplicationEventPublisher applicationEventPublisher;

    @Resource
    MessageConvertor messageConvertor;
    @Resource
    SessionConvertor sessionConvertor;

    @Resource
    GroupService groupService;
    @Resource
    SessionService sessionService;
    @Resource
    UserService userService;

    public void sendMessage(Message message) {
        // 发消息后需要处理对应的session
        User user = userService.findByEmployeeNumber(message.getUid());
        IMEvent<MessageDto> event = new IMEvent<>(this);
        if (message.getSessionType() == SessionType.CHAT) {
            event.setUid(Arrays.asList(message.getUid(), message.getToId()));
            sendSession(message.getUid(), message.getToId(), message, user);
            sendSession(message.getToId(), message.getUid(), message, user);
        } else if (message.getSessionType() == SessionType.GROUPCHAT) {
            Group group = groupService.get(message.getToId());
            event.setUid(group.getMembers());
            for (Integer member : group.getMembers()) {
                sendSession(member, group.getId(), message, user);
            }
        }
        event.setContent(messageConvertor.convert(message));
        event.setType(EventType.MESSAGE);
        applicationEventPublisher.publishEvent(event);
        logger.info("send Message {}", message);
    }

    public void sendSession(Session session) {
        IMEvent<SessionDto> event = new IMEvent<>(this);
        event.setUid(Arrays.asList(session.getUid()));
        event.setContent(sessionConvertor.convert(session));
        event.setType(EventType.SESSION);
        applicationEventPublisher.publishEvent(event);
        sessionService.put(session.getUid(), session);
        logger.info("send Session {}", session);
    }

    private void sendSession(int uid, int toUid, Message message, User user) {
        Session session = sessionService.get(uid, toUid);
        if (session == null) {
            session = new Session();
            session.setId(toUid);
            session.setUid(uid);
            session.setType(message.getSessionType());
            session.setAvatar(user.getAvatar());
            session.setName(user.getName());
        }
        session.setUnread(session.getUnread() + 1);
        session.setLastContent(message.getContent());
        session.setLastType(message.getMessageType());
        session.setTime(message.getCreateTime().getTime());
        sendSession(session);
    }

    /**
     * @param group
     */
    public void sendGroup(Group group) {
        Session session = new Session();
        session.setId(group.getId());
        session.setType(SessionType.GROUPCHAT);
        session.setName(group.getName());
        session.setUid(group.getCreateUid());
        session.setAvatar(Constants.DEFAULT_AVATAR); // TODO change avatar
        sendSession(session);
    }
}
