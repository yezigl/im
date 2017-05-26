/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月3日
 */
@Configuration
public class IMConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制
        factory.setMaxFileSize("10MB"); // KB,MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize("10MB");
        // Sets the directory location where files will be stored.
        return factory.createMultipartConfig();
    }
    
    @Configuration
    @Profile({ "default" })
    @PropertySource(value = "classpath:config.properties")
    static class ConfigDefault {

    }

    @Configuration
    @Profile({ "dev" })
    @PropertySource(value = "classpath:config-dev.properties")
    static class ConfigDev {

    }
    
    @Configuration
    @Profile({ "local" })
    @PropertySource(value = "classpath:config-local.properties")
    static class ConfigLocal {

    }
}
