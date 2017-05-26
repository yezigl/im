package perhaps.love.im.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import perhaps.love.im.entity.Message;
import perhaps.love.im.repository.MessageRepository;
import perhaps.love.im.utils.PageBuilder;

@Service
public class MessageService {

    @Resource
    MessageRepository messageRepository;

    public void save(Message message) {
        message.setCreateTime(new Date());
        message.setUpdateTime(new Date());
        messageRepository.save(message);
    }

    public List<Message> listChats(int uid, int toUid, int offset, int limit) {
        PageRequest request = PageBuilder.newBuilder().offset(offset).limit(limit).order("createTime").desc().build();
        List<Integer> uids = Arrays.asList(uid, toUid);
        return messageRepository.findByUidInAndToIdIn(uids, uids, request);
    }

    public List<Message> listGroupChats(int groupId, int offset, int limit) {
        PageRequest request = PageBuilder.newBuilder().offset(offset).limit(limit).order("createTime").desc().build();
        return messageRepository.findByToId(groupId, request);
    }
    
    public Message getChatLatest(int uid, int toUid) {
        PageRequest request = PageBuilder.newBuilder().offset(0).limit(1).order("createTime").desc().build();
        List<Integer> uids = Arrays.asList(uid, toUid);
        List<Message> list = messageRepository.findByUidInAndToIdIn(uids, uids, request);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
    
    public Message getGroupChatLatest(int groupId) {
        PageRequest request = PageBuilder.newBuilder().offset(0).limit(1).order("createTime").desc().build();
        List<Message> list = messageRepository.findByToId(groupId, request);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
