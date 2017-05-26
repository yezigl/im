/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import perhaps.love.im.context.UserContextHolder;
import perhaps.love.im.dto.JsonResponse;
import perhaps.love.im.dto.RosterDto;
import perhaps.love.im.dto.Status;
import perhaps.love.im.dto.convertor.UserConvertor;
import perhaps.love.im.entity.User;
import perhaps.love.im.service.UserService;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月28日
 */
@RestController
@RequestMapping("/api/v1")
public class RosterController {

    @Resource
    UserService userService;

    @Resource
    UserConvertor userConvertor;

    @RequestMapping(value = "/rosters", method = RequestMethod.GET)
    public JsonResponse<List<RosterDto>> rosters(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        List<User> users = userService.findAll(offset, limit);
        return JsonResponse.success(userConvertor.convert(users));
    }

    @RequestMapping(value = "/rosters/{uid}", method = RequestMethod.GET)
    public JsonResponse<RosterDto> rosterGet(@PathVariable int uid) {
        User user = userService.findByEmployeeNumber(uid);
        return JsonResponse.success(userConvertor.convert(user));
    }

    @RequestMapping(value = "/rosters/{uid}", method = RequestMethod.POST)
    public JsonResponse<RosterDto> rosterPost(@PathVariable int uid, @RequestParam(required = false) String avatar,
            @RequestParam(required = false) String tel, @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String signature) {
        int myUid = UserContextHolder.getUid();
        if (myUid != uid) {
            return JsonResponse.error(Status.ERROR);
        }
        User user = userService.findByEmployeeNumber(uid);
        if (StringUtils.isNotEmpty(avatar)) {
            user.setAvatar(avatar);
        }
        if (StringUtils.isNotEmpty(tel)) {
            user.setTel(tel);
        }
        if (StringUtils.isNotEmpty(mobile)) {
            user.setMobile(mobile);
        }
        if (StringUtils.isNotEmpty(signature)) {
            user.setSignature(signature);
        }
        userService.save(user);
        return JsonResponse.success(userConvertor.convert(user));
    }
}
