/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月5日
 */
@Entity
@Table(indexes = { @Index(name = "idx_uid_gid", columnList = "uid,gid") })
public class UserGroup {

    @Id
    @GeneratedValue
    private int id;
    private int uid;
    private int gid;
    private boolean top;
    private boolean remind;
    private boolean favor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

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
