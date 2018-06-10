package entity;

import java.util.Date;
import java.util.UUID;

/**
 * 用户实体类，id 值使用 UUID 随机生成
 *
 * @author Fish
 * created by 2018-06-08 16:19
 */
public class User {
    private String userId = null;

    private String name = null;

    private String username = null;

    private String password = null;

    private String qq = null;

    private String mail = null;

    private String intro = null;

    private Date createTime = null;

    public User() {
        this.userId = UUID.randomUUID().toString();
    }

    public User(String username, String password) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                ", mail='" + mail + '\'' +
                ", intro='" + intro + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
