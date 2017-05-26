/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.dto.convertor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import perhaps.love.im.context.UserContextHolder;
import perhaps.love.im.dto.GroupDto;
import perhaps.love.im.entity.Group;
import perhaps.love.im.entity.User;
import perhaps.love.im.entity.UserGroup;
import perhaps.love.im.service.GroupService;
import perhaps.love.im.service.UserService;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月5日
 */
@Component
public class GroupConvertor extends AbstractConvertor<Group, GroupDto> {

    @Resource
    UserService userService;
    @Resource
    GroupService groupService;
    @Resource
    UserConvertor userConvertor;
    @Resource
    UserGroupConvertor userGroupConvertor;
    
    @Override
    public GroupDto convert(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setAdminUid(group.getAdminUid());
        groupDto.setCreateUid(group.getCreateUid());
        groupDto.setAvatar(group.getAvatar());
        groupDto.setName(group.getName());
        groupDto.setNotice(group.getNotice());
        User user = userService.findByEmployeeNumber(group.getAdminUid());
        groupDto.setAdmin(userConvertor.convert(user));
        UserGroup userGroup = groupService.getUserGroup(UserContextHolder.getUid(), group.getId());
        if (userGroup != null) {
            groupDto.setOption(userGroupConvertor.convert(userGroup));
        }
        return groupDto;
    }

}
