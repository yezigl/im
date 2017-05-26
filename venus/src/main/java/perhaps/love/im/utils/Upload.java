/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.utils;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月10日
 */
public class Upload {

    private String host = "";
    private String key;
    
    public Upload(String key) {
        this.key = key;
    }
    
    public String getOriginUrl() {
        return host + "/" + key;
    }
    
    public String getW300Url() {
        return host + "/c_zoom,w_300/" + key;
    }
}
