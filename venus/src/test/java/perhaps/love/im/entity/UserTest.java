/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.entity;

import org.junit.Test;

import perhaps.love.im.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月6日
 */
public class UserTest {

    @Test
    public void test() {
        User user = new User();
        user.setDisplayName("LI DEHUA(焦点)");
        System.out.println(user.getDisplayName());
    }

}
