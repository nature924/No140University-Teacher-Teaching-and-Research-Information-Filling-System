package com.entity.vo;

import com.entity.JioayanguanliEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 教研管理
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jioayanguanli")
public class JioayanguanliVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 教研管理姓名
     */

    @TableField(value = "jioayanguanli_name")
    private String jioayanguanliName;


    /**
     * 教研管理手机号
     */

    @TableField(value = "jioayanguanli_phone")
    private String jioayanguanliPhone;


    /**
     * 教研管理身份证号
     */

    @TableField(value = "jioayanguanli_id_number")
    private String jioayanguanliIdNumber;


    /**
     * 教研管理头像
     */

    @TableField(value = "jioayanguanli_photo")
    private String jioayanguanliPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 教研管理邮箱
     */

    @TableField(value = "jioayanguanli_email")
    private String jioayanguanliEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：教研管理姓名
	 */
    public String getJioayanguanliName() {
        return jioayanguanliName;
    }


    /**
	 * 获取：教研管理姓名
	 */

    public void setJioayanguanliName(String jioayanguanliName) {
        this.jioayanguanliName = jioayanguanliName;
    }
    /**
	 * 设置：教研管理手机号
	 */
    public String getJioayanguanliPhone() {
        return jioayanguanliPhone;
    }


    /**
	 * 获取：教研管理手机号
	 */

    public void setJioayanguanliPhone(String jioayanguanliPhone) {
        this.jioayanguanliPhone = jioayanguanliPhone;
    }
    /**
	 * 设置：教研管理身份证号
	 */
    public String getJioayanguanliIdNumber() {
        return jioayanguanliIdNumber;
    }


    /**
	 * 获取：教研管理身份证号
	 */

    public void setJioayanguanliIdNumber(String jioayanguanliIdNumber) {
        this.jioayanguanliIdNumber = jioayanguanliIdNumber;
    }
    /**
	 * 设置：教研管理头像
	 */
    public String getJioayanguanliPhoto() {
        return jioayanguanliPhoto;
    }


    /**
	 * 获取：教研管理头像
	 */

    public void setJioayanguanliPhoto(String jioayanguanliPhoto) {
        this.jioayanguanliPhoto = jioayanguanliPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：教研管理邮箱
	 */
    public String getJioayanguanliEmail() {
        return jioayanguanliEmail;
    }


    /**
	 * 获取：教研管理邮箱
	 */

    public void setJioayanguanliEmail(String jioayanguanliEmail) {
        this.jioayanguanliEmail = jioayanguanliEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
