package com.zt.pojo;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/17 20:37
 */
public class UserDetail {
    private Integer id;
    private String username;
    private String name;
    private String sex;
    private String birthday;
    private String email;
    private String password;

    public UserDetail(Integer id, String username, String name, String sex, String birthday, String email, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
