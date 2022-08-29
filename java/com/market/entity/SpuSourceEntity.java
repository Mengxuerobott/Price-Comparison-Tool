package com.market.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品来源表
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@Data
@TableName("bis_goods_spu_source")
public class SpuSourceEntity extends Model<SpuSourceEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "gss_id", type = IdType.AUTO)
    private Long gssId;
    /**
     * spu_id
     */
    @TableField("gss_spu_id")
    private Long gssSpuId;
    /**
     * 来源名称(全网商城)
     */
    @TableField("gss_name")
    private String gssName;

    /**
     * 图片链接
     */
    @TableField("gss_img_url")
    private String gssImgUrl;
    /**
     * (目标)跳转链接
     */
    @TableField("gss_dest_url")
    private String gssDestUrl;
    /**
     * 价格(原价)
     */
    @TableField("gss_full_price")
    private Integer gssFullPrice;
    /**
     * 价格(现价,折扣价)
     */
    @TableField("gss_current_price")
    private Integer gssCurrentPrice;
    /**
     * 上市时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("gss_ttm")
    private Date gssTtm;
    /**
     * 点击数/商品浏览数
     */
    @TableField("gss_view")
    private Long gssView;
    /**
     * 评论数
     */
    @TableField("gss_comment")
    private Long gssComment;
    /**
     * 状态标志
     */
    @TableField("gss_state")
    private String gssState;
    /**
     * 创建时间
     */
    @TableField("gss_created")
    private Date gssCreated;
    /**
     * 修改时间
     */
    @TableField("gss_updated")
    private Date gssUpdated;
    /**
     * 商品名称
     */
    @TableField("gss_spu_name")
    private String gssSpuName;


    /**
     * 商品描述
     */
    @TableField("gss_spu_desc")
    private String gssSpuDesc;


    /**
     * 商品分类
     */
    @TableField("gss_spu_category")
    private String gssSpuCategory;

    /**
     * 商品品牌
     */
    @TableField("gss_spu_brand")
    private String gssSpuBrand;

    /**
     * 商品分类id
     */
    @TableField("gss_spu_category_id")
    private String gssSpuCategoryId;

    /**
     * 商品品牌id
     */
    @TableField("gss_spu_brand_id")
    private String gssSpuBrandId;

    @Override
    protected Serializable pkVal() {
        return this.gssId;
    }

}
