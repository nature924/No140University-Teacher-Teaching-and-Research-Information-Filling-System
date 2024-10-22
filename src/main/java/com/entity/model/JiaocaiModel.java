package com.entity.model;

import com.entity.JiaocaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 教材
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiaocaiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 教材名称
     */
    private String jiaocaiName;


    /**
     * 教材编号
     */
    private String jiaocaiUuidNumber;


    /**
     * 教材照片
     */
    private String jiaocaiPhoto;


    /**
     * 教材类型
     */
    private Integer jiaocaiTypes;


    /**
     * 教材下载
     */
    private String jiaocaiFile;


    /**
     * 教材介绍
     */
    private String jiaocaiContent;


    /**
     * 逻辑删除
     */
    private Integer jiaocaiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
