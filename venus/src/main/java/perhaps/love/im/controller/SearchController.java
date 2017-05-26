/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import perhaps.love.im.dto.JsonResponse;
import perhaps.love.im.dto.RosterDto;
import perhaps.love.im.dto.convertor.UserConvertor;
import perhaps.love.im.entity.User;
import perhaps.love.im.service.UserService;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月6日
 */
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    
    @Resource
    UserService userService;
    
    @Resource
    UserConvertor userConvertor;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JsonResponse<List<RosterDto>> searchUsers(@RequestParam String keyword) {
        
        List<User> list = userService.findByKeyword(keyword);
        
        return JsonResponse.success(userConvertor.convert(list));
    }
}
