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
 * 教研论文
 *
 * @author 
 * @email
 */
@TableName("lunwen")
public class LunwenEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LunwenEntity() {

	}

	public LunwenEntity(T t) {
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
     * 教研项目
     */
    @ColumnInfo(comment="教研项目",type="int(11)")
    @TableField(value = "xiangmu_id")

    private Integer xiangmuId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 教研论文名称
     */
    @ColumnInfo(comment="教研论文名称",type="varchar(200)")
    @TableField(value = "lunwen_name")

    private String lunwenName;


    /**
     * 教研论文编号
     */
    @ColumnInfo(comment="教研论文编号",type="varchar(200)")
    @TableField(value = "lunwen_uuid_number")

    private String lunwenUuidNumber;


    /**
     * 教研论文照片
     */
    @ColumnInfo(comment="教研论文照片",type="varchar(200)")
    @TableField(value = "lunwen_photo")

    private String lunwenPhoto;


    /**
     * 教研论文类型
     */
    @ColumnInfo(comment="教研论文类型",type="int(11)")
    @TableField(value = "lunwen_types")

    private Integer lunwenTypes;


    /**
     * 教研论文下载
     */
    @ColumnInfo(comment="教研论文下载",type="varchar(200)")
    @TableField(value = "lunwen_file")

    private String lunwenFile;


    /**
     * 教研论文介绍
     */
    @ColumnInfo(comment="教研论文介绍",type="text")
    @TableField(value = "lunwen_content")

    private String lunwenContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "lunwen_delete")

    private Integer lunwenDelete;


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
	 * 获取：教研项目
	 */
    public Integer getXiangmuId() {
        return xiangmuId;
    }
    /**
	 * 设置：教研项目
	 */

    public void setXiangmuId(Integer xiangmuId) {
        this.xiangmuId = xiangmuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：教研论文名称
	 */
    public String getLunwenName() {
        return lunwenName;
    }
    /**
	 * 设置：教研论文名称
	 */

    public void setLunwenName(String lunwenName) {
        this.lunwenName = lunwenName;
    }
    /**
	 * 获取：教研论文编号
	 */
    public String getLunwenUuidNumber() {
        return lunwenUuidNumber;
    }
    /**
	 * 设置：教研论文编号
	 */

    public void setLunwenUuidNumber(String lunwenUuidNumber) {
        this.lunwenUuidNumber = lunwenUuidNumber;
    }
    /**
	 * 获取：教研论文照片
	 */
    public String getLunwenPhoto() {
        return lunwenPhoto;
    }
    /**
	 * 设置：教研论文照片
	 */

    public void setLunwenPhoto(String lunwenPhoto) {
        this.lunwenPhoto = lunwenPhoto;
    }
    /**
	 * 获取：教研论文类型
	 */
    public Integer getLunwenTypes() {
        return lunwenTypes;
    }
    /**
	 * 设置：教研论文类型
	 */

    public void setLunwenTypes(Integer lunwenTypes) {
        this.lunwenTypes = lunwenTypes;
    }
    /**
	 * 获取：教研论文下载
	 */
    public String getLunwenFile() {
        return lunwenFile;
    }
    /**
	 * 设置：教研论文下载
	 */

    public void setLunwenFile(String lunwenFile) {
        this.lunwenFile = lunwenFile;
    }
    /**
	 * 获取：教研论文介绍
	 */
    public String getLunwenContent() {
        return lunwenContent;
    }
    /**
	 * 设置：教研论文介绍
	 */

    public void setLunwenContent(String lunwenContent) {
        this.lunwenContent = lunwenContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getLunwenDelete() {
        return lunwenDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setLunwenDelete(Integer lunwenDelete) {
        this.lunwenDelete = lunwenDelete;
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
        return "Lunwen{" +
            ", id=" + id +
            ", xiangmuId=" + xiangmuId +
            ", yonghuId=" + yonghuId +
            ", lunwenName=" + lunwenName +
            ", lunwenUuidNumber=" + lunwenUuidNumber +
            ", lunwenPhoto=" + lunwenPhoto +
            ", lunwenTypes=" + lunwenTypes +
            ", lunwenFile=" + lunwenFile +
            ", lunwenContent=" + lunwenContent +
            ", lunwenDelete=" + lunwenDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
