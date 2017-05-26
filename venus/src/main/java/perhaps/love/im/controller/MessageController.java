package perhaps.love.im.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import perhaps.love.im.context.UserContextHolder;
import perhaps.love.im.dto.JsonResponse;
import perhaps.love.im.dto.MessageDto;
import perhaps.love.im.dto.convertor.MessageConvertor;
import perhaps.love.im.entity.Message;
import perhaps.love.im.entity.MessageType;
import perhaps.love.im.entity.SessionType;
import perhaps.love.im.service.GroupService;
import perhaps.love.im.service.MessageService;
import perhaps.love.im.sse.AsyncSender;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Resource
    MessageService messageService;
    @Resource
    GroupService groupService;

    @Resource
    MessageConvertor messageConvertor;
    @Resource
    AsyncSender asyncSender;

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public JsonResponse<Object> postText(@RequestParam int toId, @RequestParam SessionType sessionType,
            @RequestParam MessageType type, @RequestParam String content, @RequestParam long timestamp) {

        Message message = new Message();
        message.setUid(UserContextHolder.getUid());
        message.setSessionType(sessionType);
        message.setToId(toId);
        message.setMessageType(type);
        message.setContent(content);
        messageService.save(message);
        // TODO 使用异步队列
        asyncSender.sendMessage(message);

        return JsonResponse.success(messageConvertor.convert(message));
    }

    @RequestMapping(value = "/messages/chat", method = RequestMethod.GET)
    public JsonResponse<List<MessageDto>> getChatMessages(@RequestParam int toUid,
            @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
        int uid = UserContextHolder.getUid();
        List<Message> list = messageService.listChats(uid, toUid, offset, limit);
        return JsonResponse.success(messageConvertor.convert(list));
    }

    @RequestMapping(value = "/messages/groupchat", method = RequestMethod.GET)
    public JsonResponse<List<MessageDto>> getGroupChatMessages(@RequestParam int gid,
            @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
        // 判断用户是否在此群组里
        int uid = UserContextHolder.getUid();
        if (!groupService.isInGroup(uid, gid)) {
            return JsonResponse.success();
        }
        List<Message> list = messageService.listGroupChats(gid, offset, limit);
        return JsonResponse.success(messageConvertor.convert(list));
    }
}
