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
 * 标准产品单元
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@TableName("bis_goods_spu")
public class SpuEntity extends Model<SpuEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * SPU id
     */
    @TableId(value = "spu_id", type = IdType.AUTO)
    private Long spuId;
    /**
     * 商品标题
     */
    @TableField("spu_title")
    private String spuTitle;
    /**
     * 子标题
     */
    @TableField("spu_subtitle")
    private String spuSubtitle;
    /**
     * 商品分类id
     */
    @TableField("spu_catetory_id")
    private String spuCatetoryId;
    /**
     * 品牌id
     */
    @TableField("spu_brand_id")
    private String spuBrandId;
    /**
     * 商品描述
     */
    @TableField("spu_desc")
    private String spuDesc;
    /**
     * 状态标志
     */
    @TableField("spu_state")
    private String spuState;
    /**
     * 创建时间
     */
    @TableField("spu_created")
    private Date spuCreated;
    /**
     * 修改时间
     */
    @TableField("spu_updated")
    private Date spuUpdated;


    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getSpuTitle() {
        return spuTitle;
    }

    public void setSpuTitle(String spuTitle) {
        this.spuTitle = spuTitle;
    }

    public String getSpuSubtitle() {
        return spuSubtitle;
    }

    public void setSpuSubtitle(String spuSubtitle) {
        this.spuSubtitle = spuSubtitle;
    }

    public String getSpuCatetoryId() {
        return spuCatetoryId;
    }

    public void setSpuCatetoryId(String spuCatetoryId) {
        this.spuCatetoryId = spuCatetoryId;
    }

    public String getSpuBrandId() {
        return spuBrandId;
    }

    public void setSpuBrandId(String spuBrandId) {
        this.spuBrandId = spuBrandId;
    }

    public String getSpuDesc() {
        return spuDesc;
    }

    public void setSpuDesc(String spuDesc) {
        this.spuDesc = spuDesc;
    }

    public String getSpuState() {
        return spuState;
    }

    public void setSpuState(String spuState) {
        this.spuState = spuState;
    }

    public Date getSpuCreated() {
        return spuCreated;
    }

    public void setSpuCreated(Date spuCreated) {
        this.spuCreated = spuCreated;
    }

    public Date getSpuUpdated() {
        return spuUpdated;
    }

    public void setSpuUpdated(Date spuUpdated) {
        this.spuUpdated = spuUpdated;
    }

    @Override
    protected Serializable pkVal() {
        return this.spuId;
    }

    @Override
    public String toString() {
        return "SpuEntity{" +
        ", spuId=" + spuId +
        ", spuTitle=" + spuTitle +
        ", spuSubtitle=" + spuSubtitle +
        ", spuCatetoryId=" + spuCatetoryId +
        ", spuBrandId=" + spuBrandId +
        ", spuDesc=" + spuDesc +
        ", spuState=" + spuState +
        ", spuCreated=" + spuCreated +
        ", spuUpdated=" + spuUpdated +
        "}";
    }
}
