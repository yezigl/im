/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import perhaps.love.im.entity.Group;
import perhaps.love.im.entity.UserGroup;
import perhaps.love.im.repository.GroupRepository;
import perhaps.love.im.repository.UserGroupRepository;
import perhaps.love.im.utils.PageBuilder;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月29日
 */
@Service
public class GroupService {
    
    @Resource
    GroupRepository groupRepository;
    @Resource
    UserGroupRepository userGroupRepository;

    public boolean isInGroup(int uid, int gid) {
        Group group = groupRepository.getOne(gid);
        return group.getMembers().contains(Integer.valueOf(uid));
    }

    /**
     * @param groupId
     * @return
     */
    public Group get(int groupId) {
        return groupRepository.findOne(groupId);
    }
    
    public void save(Group group) {
        group.setCreateTime(new Date());
        group.setUpdateTime(new Date());
        groupRepository.save(group);
    }

    public List<Group> listGroups(int uid, int offset, int limit) {
        PageRequest request = PageBuilder.newBuilder().offset(offset).limit(limit).build();
        List<UserGroup> list = userGroupRepository.findByUidAndFavor(uid, true, request);
        List<Integer> gids = list.stream().map(e -> e.getId()).collect(Collectors.toList());
        return groupRepository.findByIdIn(gids);
    }
    
    public List<Group> listSystemGroups() {
        return new ArrayList<>();
    }

    /**
     * @param group
     */
    public void update(Group group) {
        groupRepository.save(group);
    }
    
    public UserGroup getUserGroup(int uid, int gid) {
        return userGroupRepository.findByUidAndGid(uid, gid);
    }

    /**
     * @param userGroup
     */
    public void update(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }
}
