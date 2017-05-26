/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.sse;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月5日
 */
public enum EventType {

    MESSAGE("message"), SESSION("session");

    private String name;

    /**
     * 
     */
    private EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
