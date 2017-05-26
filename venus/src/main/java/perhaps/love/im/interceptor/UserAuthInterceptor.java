package perhaps.love.im.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import perhaps.love.im.Constants;
import perhaps.love.im.context.UserContextHolder;
import perhaps.love.im.utils.CookieUtils;

@Component
public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    @Value("${login.url}")
    String loginUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Cookie cookie = CookieUtils.getCookie(request, Constants.COOKIE_LOGIN);
        if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
            response.sendRedirect(loginUrl);
            return false;
        }
        String[] ss = StringUtils.split(cookie.getValue(), '|');
        if (ss.length != 2) {
            response.sendRedirect(loginUrl);
            return false;
        }
        String uid = ss[0];
        String sign = ss[1];
        if (!CookieUtils.genSign(uid).equals(sign)) {
            response.sendRedirect(loginUrl);
            return false;
        }
        UserContextHolder.setUid(Integer.parseInt(uid));

        return true;
    }
}
