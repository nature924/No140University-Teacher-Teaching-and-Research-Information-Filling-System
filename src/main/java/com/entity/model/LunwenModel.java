package com.entity.model;

import com.entity.LunwenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 教研论文
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LunwenModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 教研项目
     */
    private Integer xiangmuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 教研论文名称
     */
    private String lunwenName;


    /**
     * 教研论文编号
     */
    private String lunwenUuidNumber;


    /**
     * 教研论文照片
     */
    private String lunwenPhoto;


    /**
     * 教研论文类型
     */
    private Integer lunwenTypes;


    /**
     * 教研论文下载
     */
    private String lunwenFile;


    /**
     * 教研论文介绍
     */
    private String lunwenContent;


    /**
     * 逻辑删除
     */
    private Integer lunwenDelete;


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
