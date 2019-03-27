package edu.nju.master.graduate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Daniel
 * @since 2019-03-22
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * 角色权限 0-普通用户 1-root用户
     */
    @Column(name = "role")
    private Integer role;
    /**
     * 用户状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public User() {
        this.createTime = new Date();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", role=" + role +
        ", phoneNumber=" + phoneNumber +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
