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
 * 教材
 *
 * @author 
 * @email
 */
@TableName("jiaocai")
public class JiaocaiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiaocaiEntity() {

	}

	public JiaocaiEntity(T t) {
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
     * 教材名称
     */
    @ColumnInfo(comment="教材名称",type="varchar(200)")
    @TableField(value = "jiaocai_name")

    private String jiaocaiName;


    /**
     * 教材编号
     */
    @ColumnInfo(comment="教材编号",type="varchar(200)")
    @TableField(value = "jiaocai_uuid_number")

    private String jiaocaiUuidNumber;


    /**
     * 教材照片
     */
    @ColumnInfo(comment="教材照片",type="varchar(200)")
    @TableField(value = "jiaocai_photo")

    private String jiaocaiPhoto;


    /**
     * 教材类型
     */
    @ColumnInfo(comment="教材类型",type="int(11)")
    @TableField(value = "jiaocai_types")

    private Integer jiaocaiTypes;


    /**
     * 教材下载
     */
    @ColumnInfo(comment="教材下载",type="varchar(200)")
    @TableField(value = "jiaocai_file")

    private String jiaocaiFile;


    /**
     * 教材介绍
     */
    @ColumnInfo(comment="教材介绍",type="text")
    @TableField(value = "jiaocai_content")

    private String jiaocaiContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "jiaocai_delete")

    private Integer jiaocaiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：教材名称
	 */
    public String getJiaocaiName() {
        return jiaocaiName;
    }
    /**
	 * 设置：教材名称
	 */

    public void setJiaocaiName(String jiaocaiName) {
        this.jiaocaiName = jiaocaiName;
    }
    /**
	 * 获取：教材编号
	 */
    public String getJiaocaiUuidNumber() {
        return jiaocaiUuidNumber;
    }
    /**
	 * 设置：教材编号
	 */

    public void setJiaocaiUuidNumber(String jiaocaiUuidNumber) {
        this.jiaocaiUuidNumber = jiaocaiUuidNumber;
    }
    /**
	 * 获取：教材照片
	 */
    public String getJiaocaiPhoto() {
        return jiaocaiPhoto;
    }
    /**
	 * 设置：教材照片
	 */

    public void setJiaocaiPhoto(String jiaocaiPhoto) {
        this.jiaocaiPhoto = jiaocaiPhoto;
    }
    /**
	 * 获取：教材类型
	 */
    public Integer getJiaocaiTypes() {
        return jiaocaiTypes;
    }
    /**
	 * 设置：教材类型
	 */

    public void setJiaocaiTypes(Integer jiaocaiTypes) {
        this.jiaocaiTypes = jiaocaiTypes;
    }
    /**
	 * 获取：教材下载
	 */
    public String getJiaocaiFile() {
        return jiaocaiFile;
    }
    /**
	 * 设置：教材下载
	 */

    public void setJiaocaiFile(String jiaocaiFile) {
        this.jiaocaiFile = jiaocaiFile;
    }
    /**
	 * 获取：教材介绍
	 */
    public String getJiaocaiContent() {
        return jiaocaiContent;
    }
    /**
	 * 设置：教材介绍
	 */

    public void setJiaocaiContent(String jiaocaiContent) {
        this.jiaocaiContent = jiaocaiContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJiaocaiDelete() {
        return jiaocaiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setJiaocaiDelete(Integer jiaocaiDelete) {
        this.jiaocaiDelete = jiaocaiDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Jiaocai{" +
            ", id=" + id +
            ", jiaocaiName=" + jiaocaiName +
            ", jiaocaiUuidNumber=" + jiaocaiUuidNumber +
            ", jiaocaiPhoto=" + jiaocaiPhoto +
            ", jiaocaiTypes=" + jiaocaiTypes +
            ", jiaocaiFile=" + jiaocaiFile +
            ", jiaocaiContent=" + jiaocaiContent +
            ", jiaocaiDelete=" + jiaocaiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
