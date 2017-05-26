package perhaps.love.im.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import perhaps.love.im.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findByUidInAndToIdIn(List<Integer> uids, List<Integer> toIds, Pageable pageable);
    
    public List<Message> findByToId(int groupId, Pageable pageable);
}
