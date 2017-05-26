package perhaps.love.im.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import perhaps.love.im.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    /**
     * @param gids
     * @return
     */
    List<Group> findByIdIn(List<Integer> gids);

}
