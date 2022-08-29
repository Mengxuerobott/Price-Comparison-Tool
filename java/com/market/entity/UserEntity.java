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
 * 平台用户表
 * </p>
 *
 * @author Elias123
 * @since 2019-07-11
 */
@Data
@TableName("bis_user")
public class UserEntity extends Model<UserEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 自然主键,全局唯一
     */
    @TableId(value = "user_id", type = IdType.NONE)
    private Long userId;
    /**
     * 手机号
     */
    @TableField("user_phone")
    private String userPhone;
    /**
     * 昵称
     */
    @TableField("user_nickname")
    private String userNickname;
    /**
     * 电子邮件
     */
    @TableField("user_email")
    private String userEmail;
    /**
     * 出生年月日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("user_birth")
    private Date userBirth;
    /**
     * 用户类型
     */
    @TableField("user_type")
    private String userType;
    /**
     * 有效标志
     */
    @TableField("user_sex")
    private String userSex;

    /**
     * 密码
     */
    @TableField("user_password")
    private String  userPassword;



    /**
     * 建立时间
     */
    @TableField("user_balance")
    private Float userBalance;
    /**
     * 修改时间
     */
    @TableField("user_headImgurl")
    private String userHeadimgurl;
    /**
     * 备注
     */
    @TableField("user_remark")
    private String userRemark;
    /**
     * 状态标志
     */
    @TableField("user_state")
    private String userState;
    /**
     * 创建时间
     */
    @TableField("user_created")
    private Date userCreated;
    /**
     * 修改时间
     */
    @TableField("user_updated")
    private Date userUpdated;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
