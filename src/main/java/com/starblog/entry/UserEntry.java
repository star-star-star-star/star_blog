package com.starblog.entry;

import java.util.Objects;

public class UserEntry {
    /*
        fields
     */
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String name;
    /**
     * 登录密码（加密）
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 最近登录时间
     */
    private String activeTime;

    /*
        constructors
     */

    public UserEntry() {
    }

    public UserEntry(Integer uid, String name, String password, String email, String registerTime, String activeTime) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.registerTime = registerTime;
        this.activeTime = activeTime;
    }
    /*
        getter and setter
     */

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    /*
        toString method
     */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserEntry{");
        sb.append("uid=").append(uid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", registerTime='").append(registerTime).append('\'');
        sb.append(", activeTime='").append(activeTime).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /*
        equals and hshCode method
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntry userEntry = (UserEntry) o;
        return uid.equals(userEntry.uid) && name.equals(userEntry.name) && password.equals(userEntry.password) && email.equals(userEntry.email) && registerTime.equals(userEntry.registerTime) && activeTime.equals(userEntry.activeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, password, email, registerTime, activeTime);
    }
}
