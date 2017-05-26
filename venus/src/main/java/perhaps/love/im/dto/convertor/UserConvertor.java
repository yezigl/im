/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package perhaps.love.im.dto.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.dianping.cat.annotation.CatTransaction;

import perhaps.love.im.Constants;
import perhaps.love.im.dto.RosterDto;
import perhaps.love.im.entity.User;

/**
 * description here
 *
 * @author lidehua
 * @since 2016年11月17日
 */
@Component
public class UserConvertor extends AbstractConvertor<User, RosterDto> {

    /**
     * 
     */
    @Override
    @CatTransaction
    public RosterDto convert(User user) {
        RosterDto rosterDto = new RosterDto();
        rosterDto.setUid(user.getEmployeeNumber());
        rosterDto.setDepartment(user.getDepartment());
        rosterDto.setEmail(user.getMail());
        rosterDto.setName(user.getName());
        rosterDto.setTel(user.getTel());
        rosterDto.setOffice(user.getOfficeName());
        rosterDto.setAvatar(StringUtils.defaultIfBlank(user.getAvatar(), Constants.DEFAULT_AVATAR));
        rosterDto.setSignature(user.getSignature());
        rosterDto.setGender(user.getGender());
        rosterDto.setMobile(user.getMobile());
        rosterDto.setMailNickname(user.getMailNickname());
        return rosterDto;
    }

    @Override
    public List<RosterDto> convert(List<User> users) {
        return users.stream().map(this::convert).collect(Collectors.toList());
    }
}
