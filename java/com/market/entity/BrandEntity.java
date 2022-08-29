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
 * 品牌表
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@TableName("bis_goods_brand")
public class BrandEntity extends Model<BrandEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId(value = "gb_id", type = IdType.AUTO)
    private Long gbId;
    /**
     * 品牌名称
     */
    @TableField("gb_name")
    private String gbName;
    /**
     * 品牌所属分类
     */
    @TableField("gb_gc_id")
    private Long gbGcId;
    /**
     * 状态标志
     */
    @TableField("gb_state")
    private String gbState;
    /**
     * 创建时间
     */
    @TableField("gb_created")
    private Date gbCreated;
    /**
     * 修改时间
     */
    @TableField("gb_updated")
    private Date gbUpdated;


    public Long getGbId() {
        return gbId;
    }

    public void setGbId(Long gbId) {
        this.gbId = gbId;
    }

    public String getGbName() {
        return gbName;
    }

    public void setGbName(String gbName) {
        this.gbName = gbName;
    }

    public Long getGbGcId() {
        return gbGcId;
    }

    public void setGbGcId(Long gbGcId) {
        this.gbGcId = gbGcId;
    }

    public String getGbState() {
        return gbState;
    }

    public void setGbState(String gbState) {
        this.gbState = gbState;
    }

    public Date getGbCreated() {
        return gbCreated;
    }

    public void setGbCreated(Date gbCreated) {
        this.gbCreated = gbCreated;
    }

    public Date getGbUpdated() {
        return gbUpdated;
    }

    public void setGbUpdated(Date gbUpdated) {
        this.gbUpdated = gbUpdated;
    }

    @Override
    protected Serializable pkVal() {
        return this.gbId;
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
        ", gbId=" + gbId +
        ", gbName=" + gbName +
        ", gbGcId=" + gbGcId +
        ", gbState=" + gbState +
        ", gbCreated=" + gbCreated +
        ", gbUpdated=" + gbUpdated +
        "}";
    }
}
