package perhaps.love.im.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import perhaps.love.im.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmployeeNumber(int employeeNumber);

    /**
     * @param members
     * @return
     */
    List<User> findByEmployeeNumberIn(List<Integer> members);

    /**
     * @param username
     * @return
     */
    User findByMail(String username);

    /**
     * @param displayName
     * @param name 
     * @param mailNickname 
     * @return
     */
    List<User> findByDisplayNameLikeOrNameLikeOrMailNicknameLike(String displayName, String name, String mailNickname);
}
