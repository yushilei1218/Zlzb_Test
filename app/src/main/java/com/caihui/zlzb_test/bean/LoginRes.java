package com.caihui.zlzb_test.bean;

/**
 * @auther by yushilei.
 * @time 2017/5/15-11:22
 * @desc
 */

/**
 * 登录接口响应 Bean
 */
public class LoginRes {
    private int userId;
    private boolean needCheckcode;
    private boolean companyRegistered;
    private int userType;
    private int userStatus;
    private String name;
    private String accessToken;
    private String refreshToken;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isNeedCheckcode() {
        return needCheckcode;
    }

    public void setNeedCheckcode(boolean needCheckcode) {
        this.needCheckcode = needCheckcode;
    }

    public boolean isCompanyRegistered() {
        return companyRegistered;
    }

    public void setCompanyRegistered(boolean companyRegistered) {
        this.companyRegistered = companyRegistered;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}