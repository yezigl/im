/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.dto;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月10日
 */
public class UserGroupDto {

    private boolean top;
    private boolean remind;
    private boolean favor;

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isRemind() {
        return remind;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }

    public boolean isFavor() {
        return favor;
    }

    public void setFavor(boolean favor) {
        this.favor = favor;
    }

}
