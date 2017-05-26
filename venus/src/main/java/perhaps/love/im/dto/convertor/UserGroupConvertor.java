/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.dto.convertor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import perhaps.love.im.dto.UserGroupDto;
import perhaps.love.im.entity.UserGroup;
import perhaps.love.im.service.GroupService;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月5日
 */
@Component
public class UserGroupConvertor extends AbstractConvertor<UserGroup, UserGroupDto> {

    @Resource
    GroupService groupService;

    @Override
    public UserGroupDto convert(UserGroup userGroup) {
        UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setFavor(userGroup.isFavor());
        userGroupDto.setRemind(userGroup.isRemind());
        userGroupDto.setTop(userGroup.isTop());
        return userGroupDto;
    }

}
