package com.market.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@TableName("bis_goods_category")
public class CategoryEntity extends Model<CategoryEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "gc_id", type = IdType.AUTO)
    private Long gcId;
    /**
     * 分类名称
     */
    @TableField("gc_name")
    private String gcName;
    /**
     * 描述
     */
    @TableField("gc_desc")
    private String gcDesc;
    /**
     * 父类id
     */
    @TableField("gc_pid")
    private String gcPid;
    /**
     * 备注
     */
    @TableField("gc_remark")
    private String gcRemark;
    /**
     * 状态标志
     */
    @TableField("gc_state")
    private String gcState;
    /**
     * 创建时间
     */
    @TableField("gc_created")
    private Date gcCreated;
    /**
     * 修改时间
     */
    @TableField("gc_updated")
    private Date gcUpdated;


    public Long getGcId() {
        return gcId;
    }

    public void setGcId(Long gcId) {
        this.gcId = gcId;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public String getGcDesc() {
        return gcDesc;
    }

    public void setGcDesc(String gcDesc) {
        this.gcDesc = gcDesc;
    }

    public String getGcPid() {
        return gcPid;
    }

    public void setGcPid(String gcPid) {
        this.gcPid = gcPid;
    }

    public String getGcRemark() {
        return gcRemark;
    }

    public void setGcRemark(String gcRemark) {
        this.gcRemark = gcRemark;
    }

    public String getGcState() {
        return gcState;
    }

    public void setGcState(String gcState) {
        this.gcState = gcState;
    }

    public Date getGcCreated() {
        return gcCreated;
    }

    public void setGcCreated(Date gcCreated) {
        this.gcCreated = gcCreated;
    }

    public Date getGcUpdated() {
        return gcUpdated;
    }

    public void setGcUpdated(Date gcUpdated) {
        this.gcUpdated = gcUpdated;
    }

    @Override
    protected Serializable pkVal() {
        return this.gcId;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
        ", gcId=" + gcId +
        ", gcName=" + gcName +
        ", gcDesc=" + gcDesc +
        ", gcPid=" + gcPid +
        ", gcRemark=" + gcRemark +
        ", gcState=" + gcState +
        ", gcCreated=" + gcCreated +
        ", gcUpdated=" + gcUpdated +
        "}";
    }
}
