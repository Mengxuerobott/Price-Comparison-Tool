package com.market.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by Elias on 2019/7/13
 */
@ApiModel("注册表单")
@Data
public class RegisterForm {
    @ApiModelProperty("用户编号")
    private long userId;
    @NotBlank
    @ApiModelProperty("用户昵称")
    private String userNickname;
    @NotBlank
    @ApiModelProperty("手机号")
    private String userPhone;
    @NotBlank
    @ApiModelProperty("邮箱")
    private String userEmail;
    @NotBlank
    @ApiModelProperty("性别")
    private String userSex;
    @NotBlank
    @ApiModelProperty("密码")
    private String userPassword;
    @NotBlank
    @ApiModelProperty("注册类型,1平台用户，2后台管理员")
    private String registerType;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("出生日期")
    private Date userBirth;
}
