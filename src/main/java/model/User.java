package model;

/**
 * Created by lh
 * on 2017/9/6.
 */

import myenum.Gender;
import sun.security.action.GetBooleanAction;

import java.sql.Timestamp;

/**
 * 用户
 */
public class User {
    private int idUser;
    private String loginName;
    private String userName;
    private String password;
    private Gender gender;
    private byte roleType;
    private String email;
    /**
     * 0：未验证
     * 1：验证失败
     * 2：验证成功
     */
    private int status;
    private String validateCode;
    /**
     * 发起验证的时期
     */
    private Timestamp validateDate;

    public Timestamp getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Timestamp validateDate) {
        this.validateDate = validateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getRoleType() {
        return roleType;
    }

    public void setRoleType(byte roleType) {
        this.roleType = roleType;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
