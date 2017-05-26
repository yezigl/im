/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月26日
 */
public class Session {

    private int id; // toId
    private int uid; // 当前用户id
    private SessionType type;
    private String avatar;
    private int unread;
    private String name;
    private String lastName;
    private String lastContent;
    private MessageType lastType;
    private long time;

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

    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public MessageType getLastType() {
        return lastType;
    }

    public void setLastType(MessageType lastType) {
        this.lastType = lastType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("id", id);
        builder.append("uid", uid);
        builder.append("type", type);
        builder.append("avatar", avatar);
        builder.append("unread", unread);
        builder.append("name", name);
        builder.append("lastName", lastName);
        builder.append("lastContent", lastContent);
        builder.append("lastType", lastType);
        builder.append("time", time);
        return builder.toString();
    }

}
