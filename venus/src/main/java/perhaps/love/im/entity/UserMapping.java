package perhaps.love.im.entity;

import java.util.function.BiConsumer;

public enum UserMapping {

    TITLE("title", User::setTitle), // 职位
    MAIIL("mail", User::setMail), // 邮件
    DEPARTMENT("department", User::setDepartment), // 部门
    EMPLOYEENUMBER("employeeNumber", User::setEmployeeNumber), // 工号
    NAME("name", User::setName), // 姓名
    DISPLAYNAME("displayName", User::setDisplayName), // 显示名字
    PHYSICALDELIVERYOFFICENAME("physicalDeliveryOfficeName", User::setOfficeName), // 办公地点
    MAILNICKNAME("mailNickname", User::setMailNickname), // 邮件昵称
    TEL("msRTCSIP-Line", User::setTel); // 电话

    private String attr;
    private BiConsumer<User, String> consumer;

    private UserMapping(String attr, BiConsumer<User, String> consumer) {
        this.attr = attr;
        this.consumer = consumer;
    }

    public String getAttr() {
        return this.attr;
    }

    public BiConsumer<User, String> getConsumer() {
        return this.consumer;
    }
}
