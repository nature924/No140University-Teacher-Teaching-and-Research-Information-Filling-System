package com.entity.vo;

import com.entity.JiaocaiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 教材
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiaocai")
public class JiaocaiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 教材名称
     */

    @TableField(value = "jiaocai_name")
    private String jiaocaiName;


    /**
     * 教材编号
     */

    @TableField(value = "jiaocai_uuid_number")
    private String jiaocaiUuidNumber;


    /**
     * 教材照片
     */

    @TableField(value = "jiaocai_photo")
    private String jiaocaiPhoto;


    /**
     * 教材类型
     */

    @TableField(value = "jiaocai_types")
    private Integer jiaocaiTypes;


    /**
     * 教材下载
     */

    @TableField(value = "jiaocai_file")
    private String jiaocaiFile;


    /**
     * 教材介绍
     */

    @TableField(value = "jiaocai_content")
    private String jiaocaiContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "jiaocai_delete")
    private Integer jiaocaiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：教材名称
	 */
    public String getJiaocaiName() {
        return jiaocaiName;
    }


    /**
	 * 获取：教材名称
	 */

    public void setJiaocaiName(String jiaocaiName) {
        this.jiaocaiName = jiaocaiName;
    }
    /**
	 * 设置：教材编号
	 */
    public String getJiaocaiUuidNumber() {
        return jiaocaiUuidNumber;
    }


    /**
	 * 获取：教材编号
	 */

    public void setJiaocaiUuidNumber(String jiaocaiUuidNumber) {
        this.jiaocaiUuidNumber = jiaocaiUuidNumber;
    }
    /**
	 * 设置：教材照片
	 */
    public String getJiaocaiPhoto() {
        return jiaocaiPhoto;
    }


    /**
	 * 获取：教材照片
	 */

    public void setJiaocaiPhoto(String jiaocaiPhoto) {
        this.jiaocaiPhoto = jiaocaiPhoto;
    }
    /**
	 * 设置：教材类型
	 */
    public Integer getJiaocaiTypes() {
        return jiaocaiTypes;
    }


    /**
	 * 获取：教材类型
	 */

    public void setJiaocaiTypes(Integer jiaocaiTypes) {
        this.jiaocaiTypes = jiaocaiTypes;
    }
    /**
	 * 设置：教材下载
	 */
    public String getJiaocaiFile() {
        return jiaocaiFile;
    }


    /**
	 * 获取：教材下载
	 */

    public void setJiaocaiFile(String jiaocaiFile) {
        this.jiaocaiFile = jiaocaiFile;
    }
    /**
	 * 设置：教材介绍
	 */
    public String getJiaocaiContent() {
        return jiaocaiContent;
    }


    /**
	 * 获取：教材介绍
	 */

    public void setJiaocaiContent(String jiaocaiContent) {
        this.jiaocaiContent = jiaocaiContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getJiaocaiDelete() {
        return jiaocaiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJiaocaiDelete(Integer jiaocaiDelete) {
        this.jiaocaiDelete = jiaocaiDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
