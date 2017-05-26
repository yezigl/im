/**
 * Copyright 2016 focus.cn. All Rights Reserved.
 */
package perhaps.love.im.context;

/**
 * description here
 *
 * @author lidehua
 * @since 2016年11月17日
 */
public class UserContextHolder {

    private static ThreadLocal<Integer> uidHolder = new ThreadLocal<>();

    public static void setUid(Integer uid) {
        uidHolder.set(uid);
    }

    public static int getUid() {
        Integer uid = uidHolder.get();
        return uid == null ? 0 : uid.intValue();
    }
}
