/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import perhaps.love.im.entity.User;
import perhaps.love.im.service.UserService;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    UserService userService;
    
    /**
     * Test method for {@link perhaps.love.im.service.UserService#findByEmployeeNumber(int)}.
     */
    @Test
    public void testFindByEmployeeNumber() {
        User user = userService.findByEmployeeNumber(116041);
        user.setDisplayName(user.getDisplayName());
    }

    /**
     * Test method for {@link perhaps.love.im.service.UserService#findAll(int, int)}.
     */
    @Test
    public void testFindAll() {
        System.out.println(userService.findAll(0, 10));
    }

}
