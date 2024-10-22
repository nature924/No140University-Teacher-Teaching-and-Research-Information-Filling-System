package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 教研管理
 *
 * @author 
 * @email
 */
@TableName("jioayanguanli")
public class JioayanguanliEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JioayanguanliEntity() {

	}

	public JioayanguanliEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 教研管理姓名
     */
    @ColumnInfo(comment="教研管理姓名",type="varchar(200)")
    @TableField(value = "jioayanguanli_name")

    private String jioayanguanliName;


    /**
     * 教研管理手机号
     */
    @ColumnInfo(comment="教研管理手机号",type="varchar(200)")
    @TableField(value = "jioayanguanli_phone")

    private String jioayanguanliPhone;


    /**
     * 教研管理身份证号
     */
    @ColumnInfo(comment="教研管理身份证号",type="varchar(200)")
    @TableField(value = "jioayanguanli_id_number")

    private String jioayanguanliIdNumber;


    /**
     * 教研管理头像
     */
    @ColumnInfo(comment="教研管理头像",type="varchar(200)")
    @TableField(value = "jioayanguanli_photo")

    private String jioayanguanliPhoto;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 教研管理邮箱
     */
    @ColumnInfo(comment="教研管理邮箱",type="varchar(200)")
    @TableField(value = "jioayanguanli_email")

    private String jioayanguanliEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：教研管理姓名
	 */
    public String getJioayanguanliName() {
        return jioayanguanliName;
    }
    /**
	 * 设置：教研管理姓名
	 */

    public void setJioayanguanliName(String jioayanguanliName) {
        this.jioayanguanliName = jioayanguanliName;
    }
    /**
	 * 获取：教研管理手机号
	 */
    public String getJioayanguanliPhone() {
        return jioayanguanliPhone;
    }
    /**
	 * 设置：教研管理手机号
	 */

    public void setJioayanguanliPhone(String jioayanguanliPhone) {
        this.jioayanguanliPhone = jioayanguanliPhone;
    }
    /**
	 * 获取：教研管理身份证号
	 */
    public String getJioayanguanliIdNumber() {
        return jioayanguanliIdNumber;
    }
    /**
	 * 设置：教研管理身份证号
	 */

    public void setJioayanguanliIdNumber(String jioayanguanliIdNumber) {
        this.jioayanguanliIdNumber = jioayanguanliIdNumber;
    }
    /**
	 * 获取：教研管理头像
	 */
    public String getJioayanguanliPhoto() {
        return jioayanguanliPhoto;
    }
    /**
	 * 设置：教研管理头像
	 */

    public void setJioayanguanliPhoto(String jioayanguanliPhoto) {
        this.jioayanguanliPhoto = jioayanguanliPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 设置：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：教研管理邮箱
	 */
    public String getJioayanguanliEmail() {
        return jioayanguanliEmail;
    }
    /**
	 * 设置：教研管理邮箱
	 */

    public void setJioayanguanliEmail(String jioayanguanliEmail) {
        this.jioayanguanliEmail = jioayanguanliEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jioayanguanli{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", jioayanguanliName=" + jioayanguanliName +
            ", jioayanguanliPhone=" + jioayanguanliPhone +
            ", jioayanguanliIdNumber=" + jioayanguanliIdNumber +
            ", jioayanguanliPhoto=" + jioayanguanliPhoto +
            ", sexTypes=" + sexTypes +
            ", jioayanguanliEmail=" + jioayanguanliEmail +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
