package com.market.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Elias on 2019/5/25
 */
@Data
@Validated
public class BaseForm {
    @ApiModelProperty("当前页号")
    private Integer page=1;
    @ApiModelProperty("页大小")
    private Integer size=10;
}
