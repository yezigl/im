package perhaps.love.im;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import perhaps.love.im.interceptor.UserAuthInterceptor;

@Configuration
@ImportResource("classpath:applicationContext.xml")
@EnableAsync
public class WebConfig extends WebMvcConfigurerAdapter {

    @Resource
    UserAuthInterceptor userAuthInterceptor;

    // @Bean
    // public FilterRegistrationBean catFilter() {
    // FilterRegistrationBean registration = new FilterRegistrationBean();
    // registration.setFilter(new CatFilter());
    // registration.setDispatcherTypes(DispatcherType.REQUEST,
    // DispatcherType.FORWARD);
    // registration.addUrlPatterns("/*");
    // return registration;
    // }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(userAuthInterceptor).excludePathPatterns("/login", "/logout", "/css/**", "/js/**",
                "/favicon.ico", "/nianhui");
    }
}