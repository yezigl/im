/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package perhaps.love.im.dto.convertor;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import perhaps.love.im.dto.MessageDto;
import perhaps.love.im.entity.Message;
import perhaps.love.im.entity.User;
import perhaps.love.im.service.UserService;


/**
 * description here
 *
 * @author lidehua
 * @since 2016年11月17日
 */
@Component
public class MessageConvertor extends AbstractConvertor<Message, MessageDto> {
    
    @Resource
    UserService userService;

    @Override
    public MessageDto convert(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setContent(message.getContent());
        messageDto.setType(message.getMessageType());
        messageDto.setCreateTime(message.getCreateTime().getTime());
        messageDto.setId(message.getId());
        User user = userService.findByEmployeeNumber(message.getUid());
        messageDto.setUid(user.getEmployeeNumber());
        messageDto.setAvatar(user.getAvatar());
        messageDto.setName(user.getName());
        return messageDto;
    }

    @Override
    public List<MessageDto> convert(List<Message> messages) {
        return messages.stream().map(this::convert).collect(Collectors.toList());
    }

}
