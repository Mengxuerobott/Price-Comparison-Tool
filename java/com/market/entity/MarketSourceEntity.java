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
 * 商品来源表(全网商城表)
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@TableName("bis_goods_source")
public class MarketSourceEntity extends Model<MarketSourceEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 商城id
     */
    @TableId(value = "gs_id", type = IdType.AUTO)
    private Long gsId;
    /**
     * 商城名称
     */
    @TableField("gs_name")
    private String gsName;
    /**
     * 主页链接
     */
    @TableField("gs_main_url")
    private String gsMainUrl;
    /**
     * 描述
     */
    @TableField("gs_desc")
    private String gsDesc;
    /**
     * 状态标志
     */
    @TableField("gs_state")
    private String gsState;
    /**
     * 创建时间
     */
    @TableField("gs_created")
    private Date gsCreated;
    /**
     * 修改时间
     */
    @TableField("gs_updated")
    private Date gsUpdated;


    public Long getGsId() {
        return gsId;
    }

    public void setGsId(Long gsId) {
        this.gsId = gsId;
    }

    public String getGsName() {
        return gsName;
    }

    public void setGsName(String gsName) {
        this.gsName = gsName;
    }

    public String getGsMainUrl() {
        return gsMainUrl;
    }

    public void setGsMainUrl(String gsMainUrl) {
        this.gsMainUrl = gsMainUrl;
    }

    public String getGsDesc() {
        return gsDesc;
    }

    public void setGsDesc(String gsDesc) {
        this.gsDesc = gsDesc;
    }

    public String getGsState() {
        return gsState;
    }

    public void setGsState(String gsState) {
        this.gsState = gsState;
    }

    public Date getGsCreated() {
        return gsCreated;
    }

    public void setGsCreated(Date gsCreated) {
        this.gsCreated = gsCreated;
    }

    public Date getGsUpdated() {
        return gsUpdated;
    }

    public void setGsUpdated(Date gsUpdated) {
        this.gsUpdated = gsUpdated;
    }

    @Override
    protected Serializable pkVal() {
        return this.gsId;
    }

    @Override
    public String toString() {
        return "MarketSourceEntity{" +
        ", gsId=" + gsId +
        ", gsName=" + gsName +
        ", gsMainUrl=" + gsMainUrl +
        ", gsDesc=" + gsDesc +
        ", gsState=" + gsState +
        ", gsCreated=" + gsCreated +
        ", gsUpdated=" + gsUpdated +
        "}";
    }
}
