package perhaps.love.im;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@SpringBootTest
@EnableAutoConfiguration
@Configuration
@ImportResource("classpath:applicationContext.xml")
public class TestConfig {

}
