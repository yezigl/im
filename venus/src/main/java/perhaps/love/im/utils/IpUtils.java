package perhaps.love.im.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.util.InetAddressUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA. User: chenhao12 Date: 14-8-8 Time: 上午11:06 To
 * change this template use File | Settings | File Templates.
 */
public class IpUtils {

    /**
     * 获取IP地址
     *
     * @param request
     * @return String
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return StringUtils.defaultIfBlank(ip, "");
    }

    public static boolean isIPv4(String ip) {
        String trimmedIp = StringUtils.trimToEmpty(ip);
        return InetAddressUtils.isIPv4Address(trimmedIp);
    }

}
