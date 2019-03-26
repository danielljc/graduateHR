package edu.nju.master.graduate.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * 网址检测记录表
 * </p>
 *
 * @author Daniel
 * @since 2019-03-26
 */
public class UrlRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * 网站名称
     */
    @Column(name = "website_name")
    private String websiteName;
    /**
     * 网址
     */
    @Column(name = "url")
    private String url;
    /**
     * 用户id
     */
    @Column(name = "userId")
    private Integer userId;
    /**
     * 检测结果 0-正常 1-异常
     */
    @Column(name = "result")
    private Integer result;
    /**
     * 概率
     */
    @Column(name = "possibility")
    private Double possibility;
    /**
     * 检测时间
     */
    @Column(name = "create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Double getPossibility() {
        return possibility;
    }

    public void setPossibility(Double possibility) {
        this.possibility = possibility;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UrlRecord{" +
        "id=" + id +
        ", websiteName=" + websiteName +
        ", url=" + url +
        ", userId=" + userId +
        ", result=" + result +
        ", possibility" + possibility +
        ", createTime=" + createTime +
        "}";
    }
}
