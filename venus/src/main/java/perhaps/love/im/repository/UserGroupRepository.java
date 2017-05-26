package perhaps.love.im.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import perhaps.love.im.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {

    /**
     * @param request
     * @return
     */
    List<Integer> findByUid(int uid, Pageable pageable);

    /**
     * @param uid
     * @param request
     * @return
     */
    List<UserGroup> findByUidAndFavor(int uid, boolean favor, Pageable pageable);

    /**
     * @param uid
     * @param gid
     * @return
     */
    UserGroup findByUidAndGid(int uid, int gid);

}
