package perhaps.love.im.utils;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import perhaps.love.im.entity.User;

/**
 * Cookie操作工具类 Created by haochen208928 on 2015/7/16.
 */
public class CookieUtils {

    /**
     * cookie默认有效期7天
     */
    public static final int DEFAULT_MAXAGE = 7 * 24 * 60 * 60;

    public static final String DEFAULT_DOMAIN = "focus.cn";

    public static final String DEFAULT_PATH = "/";

    public static void setCookie(HttpServletResponse res, String key, String value, int maxAge, boolean httpOnly,
            String domain, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(key).append("=").append(value).append("; ");
        Date date = new Date();
        if (maxAge > 0) {
            sb.append("Max-Age=").append(maxAge).append("; ");
            sb.append("Expires=").append(DateUtils.addSeconds(date, maxAge).toString()).append(";");
        } else {
            sb.append("Max-Age=").append("Session; ");
            sb.append("Expires=").append("Session;");
        }

        if (httpOnly)
            sb.append("HttpOnly; ");
        if (!StringUtils.isEmpty(domain))
            sb.append("Domain=").append(domain).append("; ");
        if (!StringUtils.isEmpty(path))
            sb.append("Path=").append(path).append("; ");
        res.addHeader("Set-Cookie", sb.toString());
    }

    /**
     * 获取现有cookie
     *
     * @param key
     * @param req
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String key) {
        if (StringUtils.isEmpty(key) || request == null) {
            return null;
        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 添加cookie的通用方法
     *
     * @param request
     * @param response
     * @param name
     * @param value
     * @param domain
     * @param maxAge
     * @param path
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String domain, int maxAge,
            String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setDomain(domain);
        if (maxAge >= 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        String serverName = request.getServerName();
        String domain = StringUtils.isBlank(serverName) ? DEFAULT_DOMAIN : getRootDomain(serverName);
        addCookie(response, name, value, domain, DEFAULT_MAXAGE, DEFAULT_PATH);
    }

    public static String getRootDomain(String domain) {
        if (!IpUtils.isIPv4(domain) && domain.indexOf('.') != -1) {
            String last = StringUtils.substringAfterLast(domain, ".");
            if (StringUtils.isBlank(last))
                return domain;
            StringBuilder sb = new StringBuilder();
            String first = StringUtils.substringBeforeLast(domain, ".");
            first = StringUtils.substringAfterLast(first, ".");
            if (StringUtils.isBlank(first)) {
                sb.append(last);
            } else {
                sb.append(first).append(".").append(last);
            }

            return sb.toString();
        }
        return domain;
    }

    public static String genCookieValue(User user) {
        return user.getEmployeeNumber() + "|" + genSign(user.getEmployeeNumber());
    }

    public static String genSign(Object seed) {
        return DigestUtils.sha1Hex(seed + "~!@#$%");
    }
}
