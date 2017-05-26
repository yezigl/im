package perhaps.love.im.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import perhaps.love.im.entity.User;
import perhaps.love.im.repository.UserRepository;
import perhaps.love.im.utils.PageBuilder;

@Service
public class UserService {
    
    @Resource
    UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }
    
    public void update(User user) {
        userRepository.save(user);
    }

    /**
     * @param uid
     * @return
     */
    public User findByEmployeeNumber(int uid) {
        return userRepository.findByEmployeeNumber(uid);
    }
    
    public List<User> findAll(int offset, int limit) {
        PageRequest request = PageBuilder.newBuilder().offset(offset).limit(limit).build();
        return userRepository.findAll(request).getContent();
    }

    /**
     * @param members
     */
    public List<User> findByUids(List<Integer> members) {
        return userRepository.findByEmployeeNumberIn(members);
    }

    /**
     * @param username
     * @return
     */
    public User findByMail(String username) {
        return userRepository.findByMail(username);
    }

    /**
     * @param keyword
     * @return
     */
    public List<User> findByKeyword(String keyword) {
        keyword = "%" + keyword + "%";
        return userRepository.findByDisplayNameLikeOrNameLikeOrMailNicknameLike(keyword, keyword, keyword);
    }
}
