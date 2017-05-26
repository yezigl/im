package perhaps.love.im.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import perhaps.love.im.TestConfig;
import perhaps.love.im.service.LdapService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class LdapServiceTest {
    
    @Resource
    LdapService ldapService;

    @Test
    public void testFind() {
        System.out.println(ldapService.find("dehuali116041@sohu-inc.com", "yezigL@12xyz"));
    }

}
