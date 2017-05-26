/**
 * Copyright 2017 yezi.gl. All Rights Reserved.
 */
package perhaps.love.im.dto;

/**
 * description here
 *
 * @author yezi
 * @since 2017年1月5日
 */
public class GroupDto {

    private int id;
    private String name;
    private String notice;
    private int createUid;
    private int adminUid;
    private String avatar;
    private RosterDto admin;
    private UserGroupDto option;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getCreateUid() {
        return createUid;
    }

    public void setCreateUid(int createUid) {
        this.createUid = createUid;
    }

    public int getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(int adminUid) {
        this.adminUid = adminUid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RosterDto getAdmin() {
        return admin;
    }

    public void setAdmin(RosterDto admin) {
        this.admin = admin;
    }

    public UserGroupDto getOption() {
        return option;
    }

    public void setOption(UserGroupDto option) {
        this.option = option;
    }

}
