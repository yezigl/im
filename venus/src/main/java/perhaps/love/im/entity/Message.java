package perhaps.love.im.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(indexes = { @Index(name = "idx_uid_createTime", columnList = "uid,create_time"),
        @Index(name = "idx_uid_toId_createTime", columnList = "uid,to_id,create_time") })
public class Message {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private int uid; // 谁发的
    @Column(name = "to_id")
    private int toId; // 发给谁，可能是某个人，某个群，具体要看type
    @Column(name = "message_type")
    private MessageType messageType;
    @Column(name = "session_type")
    private SessionType sessionType;
    @Column
    private String content;
    @Column
    private int status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

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

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("id", id);
        builder.append("uid", uid);
        builder.append("toId", toId);
        builder.append("messageType", messageType);
        builder.append("sessionType", sessionType);
        builder.append("content", content);
        builder.append("status", status);
        builder.append("createTime", createTime);
        builder.append("updateTime", updateTime);
        return builder.toString();
    }

}
