package edu.nju.master.graduate.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class AdminUserAddReqDto {

    @NotNull(message = "用户姓名不能为空")
    private String username;

    private String password;

    private String phoneNumber;

    @Range(min = 0,max = 1,message = "用户类型错误")
    private Integer role;

    @Range(min = 0,max = 1,message = "用户状态错误")
    private Integer status;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
