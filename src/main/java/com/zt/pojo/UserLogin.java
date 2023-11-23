package com.zt.pojo;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/17 20:37
 */
public class UserLogin {
    private Integer id;
    private String username;
    private String password;
    private boolean identity;

    public UserLogin(Integer id, String username, String password, boolean identity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.identity = identity;
    }

    public UserLogin() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }
}
