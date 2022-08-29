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
 * 数据字典
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@TableName("sys_dic")
public class DicEntity extends Model<DicEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "dic_id", type = IdType.AUTO)
    private Long dicId;
    /**
     * 字典名称
     */
    @TableField("dic_name")
    private String dicName;
    /**
     * 字典类型
     */
    @TableField("dic_type")
    private String dicType;
    /**
     * 字典码
     */
    @TableField("dic_code")
    private String dicCode;
    /**
     * 字典值
     */
    @TableField("dic_value")
    private String dicValue;
    /**
     * 备注
     */
    @TableField("dic_remark")
    private String dicRemark;
    /**
     * 有效标志
     */
    @TableField("dic_flag")
    private Integer dicFlag;
    /**
     * 创建时间
     */
    @TableField("dic_created")
    private Date dicCreated;
    /**
     * 修改时间
     */
    @TableField("dic_updated")
    private Date dicUpdated;


    public Long getDicId() {
        return dicId;
    }

    public void setDicId(Long dicId) {
        this.dicId = dicId;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getDicRemark() {
        return dicRemark;
    }

    public void setDicRemark(String dicRemark) {
        this.dicRemark = dicRemark;
    }

    public Integer getDicFlag() {
        return dicFlag;
    }

    public void setDicFlag(Integer dicFlag) {
        this.dicFlag = dicFlag;
    }

    public Date getDicCreated() {
        return dicCreated;
    }

    public void setDicCreated(Date dicCreated) {
        this.dicCreated = dicCreated;
    }

    public Date getDicUpdated() {
        return dicUpdated;
    }

    public void setDicUpdated(Date dicUpdated) {
        this.dicUpdated = dicUpdated;
    }

    @Override
    protected Serializable pkVal() {
        return this.dicId;
    }

    @Override
    public String toString() {
        return "DicEntity{" +
        ", dicId=" + dicId +
        ", dicName=" + dicName +
        ", dicType=" + dicType +
        ", dicCode=" + dicCode +
        ", dicValue=" + dicValue +
        ", dicRemark=" + dicRemark +
        ", dicFlag=" + dicFlag +
        ", dicCreated=" + dicCreated +
        ", dicUpdated=" + dicUpdated +
        "}";
    }
}
