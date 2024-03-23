package com.starblog.entry;

import java.util.Objects;

public class AdministratorEntry {
    /*
        fields
     */
    /**
     * 管理员id
     */
    private Integer uid;
    /**
     * 管理员用户名
     */
    private String name;
    /**
     * 登录密码（加密）
     */
    private String password;

    /*
        constructors
     */

    public AdministratorEntry() {
    }

    public AdministratorEntry(Integer uid, String name, String password) {
        this.uid = uid;
        this.name = name;
        this.password = password;
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

    /*
        toString method
     */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdministratorEntry{");
        sb.append("uid=").append(uid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /*
        equals and hashCode method
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministratorEntry that = (AdministratorEntry) o;
        return uid.equals(that.uid) && name.equals(that.name) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, password);
    }
}
