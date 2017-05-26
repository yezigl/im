/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
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

import com.alibaba.fastjson.JSONObject;

import perhaps.love.im.context.UserContextHolder;
import perhaps.love.im.dto.GroupDto;
import perhaps.love.im.dto.JsonResponse;
import perhaps.love.im.dto.RosterDto;
import perhaps.love.im.dto.convertor.GroupConvertor;
import perhaps.love.im.dto.convertor.UserConvertor;
import perhaps.love.im.entity.Group;
import perhaps.love.im.entity.User;
import perhaps.love.im.entity.UserGroup;
import perhaps.love.im.service.GroupService;
import perhaps.love.im.service.SessionService;
import perhaps.love.im.service.UserService;
import perhaps.love.im.sse.AsyncSender;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月5日
 */
@RestController
@RequestMapping("/api/v1")
public class GroupController {

    @Resource
    GroupService groupService;
    @Resource
    UserService userService;
    @Resource
    SessionService sessionService;
    
    @Resource
    GroupConvertor groupConvertor;
    @Resource
    UserConvertor userConvertor;
    
    @Resource
    AsyncSender asyncSender;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public JsonResponse<List<GroupDto>> groupList(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        int uid = UserContextHolder.getUid();
        List<Group> list = groupService.listGroups(uid, offset, limit);
        return JsonResponse.success(groupConvertor.convert(list));
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public JsonResponse<GroupDto> groupCreate(@RequestParam(value = "members[]") List<Integer> members,
            @RequestParam(required = false) String name) {
        Group group = new Group();
        group.setCreateUid(UserContextHolder.getUid());
        group.setAdminUid(UserContextHolder.getUid());
        group.setMembers(members);
        group.setName(name);
        groupService.save(group);
        // TODO 异步通知
        asyncSender.sendGroup(group);
        return JsonResponse.success(groupConvertor.convert(group));
    }

    @RequestMapping(value = "/groups/{gid}", method = RequestMethod.GET)
    public JsonResponse<GroupDto> groupGet(@PathVariable int gid) {
        Group group = groupService.get(gid);
        return JsonResponse.success(groupConvertor.convert(group));
    }

    @RequestMapping(value = "/groups/{gid}", method = RequestMethod.POST)
    public JsonResponse<GroupDto> groupPost(@PathVariable int gid, @RequestParam(required = false) String name,
            @RequestParam(required = false) String notice) {
        Group group = groupService.get(gid);
        if (StringUtils.isNotBlank(name)) {
            group.setName(name);
        }
        if (StringUtils.isNotBlank(notice)) {
            group.setNotice(notice);
        }
        groupService.save(group);
        return JsonResponse.success(groupConvertor.convert(group));
    }

    @RequestMapping(value = "/groups/{gid}/members", method = RequestMethod.GET)
    public JsonResponse<List<RosterDto>> groupMembers(@PathVariable int gid) {
        Group group = groupService.get(gid);
        List<User> users = userService.findByUids(group.getMembers());
        return JsonResponse.success(userConvertor.convert(users));
    }
    
    @RequestMapping(value = "/groups/{gid}/leave", method = RequestMethod.POST)
    public JsonResponse<JSONObject> groupLeave(@PathVariable int gid) {
        Group group = groupService.get(gid);
        group.getMembers().remove(Integer.valueOf(UserContextHolder.getUid()));
        groupService.update(group);
        // TODO 从session列表里删除
        sessionService.delete(UserContextHolder.getUid(), gid);
        return JsonResponse.success();
    }
    
    @RequestMapping(value = "/groups/{gid}/favor", method = RequestMethod.POST)
    public JsonResponse<JSONObject> groupFavor(@PathVariable int gid, @RequestParam boolean favor) {
        UserGroup userGroup = groupService.getUserGroup(UserContextHolder.getUid(), gid);
        if (userGroup == null) {
            userGroup = new UserGroup();
            userGroup.setUid(UserContextHolder.getUid());
            userGroup.setGid(gid);
        }
        userGroup.setFavor(favor);
        groupService.update(userGroup);
        return JsonResponse.success();
    }
    
    @RequestMapping(value = "/groups/{gid}/top", method = RequestMethod.POST)
    public JsonResponse<JSONObject> groupTop(@PathVariable int gid, @RequestParam boolean top) {
        UserGroup userGroup = groupService.getUserGroup(UserContextHolder.getUid(), gid);
        if (userGroup == null) {
            userGroup = new UserGroup();
            userGroup.setUid(UserContextHolder.getUid());
            userGroup.setGid(gid);
        }
        userGroup.setTop(top);
        groupService.update(userGroup);
        return JsonResponse.success();
    }
    
    @RequestMapping(value = "/groups/{gid}/remind", method = RequestMethod.POST)
    public JsonResponse<JSONObject> groupRemind(@PathVariable int gid, @RequestParam boolean remind) {
        UserGroup userGroup = groupService.getUserGroup(UserContextHolder.getUid(), gid);
        if (userGroup == null) {
            userGroup = new UserGroup();
            userGroup.setUid(UserContextHolder.getUid());
            userGroup.setGid(gid);
        }
        userGroup.setRemind(remind);
        groupService.update(userGroup);
        return JsonResponse.success();
    }
}
