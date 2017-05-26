/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.dto;

import perhaps.love.im.entity.MessageType;

/**
 * description here
 *
 * @author yezi
 * @since 2016年12月26日
 */
public class MessageDto {

    private long id;
    private String content;
    private MessageType type;
    private long createTime;
    private int uid;
    private String name;
    private String avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
