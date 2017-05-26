package perhaps.love.im.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import perhaps.love.im.Constants;

@Entity
@Table(indexes = { @Index(name = "idx_employee_number", columnList = "employee_number", unique = true) })
public class User {
    
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "employee_number")
    private int employeeNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "mail_nickname")
    private String mailNickname;
    @Column(name = "title")
    private String title;
    @Column(name = "mail")
    private String mail;
    @Column(name = "department")
    private String department;
    @Column(name = "office_name")
    private String officeName;
    private String tel;
    private int status;
    private String avatar;
    private String signature;
    private int gender;
    @Column(length = 20)
    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return employeeNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = Integer.parseInt(employeeNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        if (displayName == null) {
            return;
        }
        String tempName = Constants.DISPLAY_NAME_PTN.matcher(displayName).replaceAll("");
        this.displayName = tempName.toLowerCase();
    }

    public String getMailNickname() {
        return mailNickname;
    }

    public void setMailNickname(String mailNickname) {
        this.mailNickname = mailNickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel.contains("tel:") ? tel.replace("tel:+", "") : tel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getAvatar() {
        return StringUtils.defaultIfBlank(avatar, Constants.DEFAULT_AVATAR);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("employeeNumber", employeeNumber);
        builder.append("name", name);
        builder.append("displayName", displayName);
        builder.append("mail", mail);
        builder.append("title", title);
        builder.append("department", department);
        builder.append("officeName", officeName);
        builder.append("tel", tel);
        return builder.toString();
    }

}
