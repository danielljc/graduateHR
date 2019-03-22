package edu.nju.master.graduate.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;

import javax.persistence.*;
import java.io.Serializable;

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
     * 角色权限 0-普通用户 1-root用户
     */
    @Column(name = "role")
    private Integer role;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;


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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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
        ", createTime=" + createTime +
        "}";
    }
}
