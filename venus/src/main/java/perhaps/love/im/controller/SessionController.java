/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import perhaps.love.im.context.UserContextHolder;
import perhaps.love.im.dto.JsonResponse;
import perhaps.love.im.dto.SessionDto;
import perhaps.love.im.dto.convertor.SessionConvertor;
import perhaps.love.im.entity.Group;
import perhaps.love.im.entity.Message;
import perhaps.love.im.entity.Session;
import perhaps.love.im.entity.SessionType;
import perhaps.love.im.entity.User;
import perhaps.love.im.service.GroupService;
import perhaps.love.im.service.MessageService;
import perhaps.love.im.service.SessionService;
import perhaps.love.im.service.UserService;
import perhaps.love.im.sse.AsyncSender;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月29日
 */
@RestController
@RequestMapping("/api/v1")
public class SessionController {

    @Resource
    SessionService sessionService;
    @Resource
    UserService userService;
    @Resource
    MessageService messageService;
    @Resource
    GroupService groupService;

    @Resource
    SessionConvertor sessionConvertor;
    
    @Resource
    AsyncSender asyncSender;

    @RequestMapping(value = "/sessions", method = RequestMethod.POST)
    public JsonResponse<Object> postSession(@RequestParam int toId, @RequestParam SessionType type) {
        int uid = UserContextHolder.getUid();
        Session session = sessionService.get(uid, toId);
        if (session == null) {
            session = new Session();
            session.setId(toId);
            session.setType(type);
            session.setUid(uid);
            if (type == SessionType.CHAT) {
                Message message = messageService.getChatLatest(uid, toId);
                User user = userService.findByEmployeeNumber(toId);
                session.setAvatar(user.getAvatar());
                session.setName(user.getName());
                if (message != null) {
                    session.setLastName(user.getName());
                    session.setLastContent(message.getContent());
                    session.setTime(message.getCreateTime().getTime());
                }
            } else if (type == SessionType.GROUPCHAT) {
                Message message = messageService.getGroupChatLatest(toId);
                if (message != null) {
                    User user = userService.findByEmployeeNumber(message.getUid());
                    session.setLastName(user.getName());
                    session.setLastContent(message.getContent());
                    session.setTime(message.getCreateTime().getTime());
                }
                Group group = groupService.get(toId);
                session.setAvatar(group.getAvatar());
                session.setName(group.getName());
            }
        } else {
            session.setUid(uid);
        }
        
        asyncSender.sendSession(session);

        return JsonResponse.success();
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public JsonResponse<List<SessionDto>> getSessions(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        int uid = UserContextHolder.getUid();
        List<Session> list = sessionService.listSession(uid, offset, limit);
        list.forEach(e -> {
            Message message = null;
            if (e.getType() == SessionType.CHAT) {
                message = messageService.getChatLatest(uid, e.getId());
            } else if (e.getType() == SessionType.GROUPCHAT) {
                message = messageService.getGroupChatLatest(e.getId());
            }
            if (message != null) {
                User user = userService.findByEmployeeNumber(message.getUid());
                e.setLastName(user.getName());
                e.setLastContent(message.getContent());
                e.setLastType(message.getMessageType());
                e.setTime(message.getCreateTime().getTime());
            }
        });
        return JsonResponse.success(sessionConvertor.convert(list));
    }
    
    @RequestMapping(value = "/sessions/{toId}/read", method = RequestMethod.POST)
    public JsonResponse<JSONObject> sessionReaded(@PathVariable int toId) {
        Session session = sessionService.get(UserContextHolder.getUid(), toId);
        if (session != null) {
            session.setUnread(0);
            sessionService.put(UserContextHolder.getUid(), session);
        }
        return JsonResponse.success();
    }
}
