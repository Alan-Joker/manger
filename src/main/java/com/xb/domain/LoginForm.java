package com.xb.domain;

import java.io.Serializable;

public class LoginForm implements Serializable {

    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;

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

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public LoginForm() {
    }

    public LoginForm(String username, String password, String verifiCode, Integer userType) {
        this.username = username;
        this.password = password;
        this.verifiCode = verifiCode;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verifiCode='" + verifiCode + '\'' +
                ", userType=" + userType +
                '}';
    }
}
