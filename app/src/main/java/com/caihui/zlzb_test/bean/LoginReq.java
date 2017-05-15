package com.caihui.zlzb_test.bean;

/**
 * @auther by yushilei.
 * @time 2017/5/15-12:10
 * @desc
 */

/**
 * 登录接口请求Bean
 */
public class LoginReq {
    private String loginName;
    private String pwd;
    private String checkId;
    private String checkCode;

    public LoginReq() {
    }

    public LoginReq(String loginName, String pwd) {
        this.loginName = loginName;
        this.pwd = pwd;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
