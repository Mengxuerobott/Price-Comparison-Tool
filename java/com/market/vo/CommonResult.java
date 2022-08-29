package com.market.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * controller result
 */
@ApiModel
@Data
public class CommonResult<T> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("返回码")
    private int code = 0;
    @ApiModelProperty("返回消息")
    private String msg = "success";
    @ApiModelProperty("返回数据")
    private T data;

    public CommonResult() {
    }

    public static CommonResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static CommonResult error(String msg) {
        return error(500, msg);
    }

    public static CommonResult error(int code, String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(code);
        commonResult.setMsg(msg);
        return commonResult;
    }

    public static CommonResult error(Exception e) {
        CommonResult commonResult = CommonResult.error();
        commonResult.setData(e);
        return commonResult;
    }

    public static CommonResult ok(String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.setMsg(msg);
        return commonResult;
    }

    public static CommonResult ok() {
        return new CommonResult();
    }

    /**
     * 直接将data传入，生成相应的泛型R
     *
     * @param value data域
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> okk(T value) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setData(value);
        return commonResult;
    }

    public static CommonResult b(boolean success) {
        if (success) return ok();
        return error();
    }

    public CommonResult put(String key, T value) {
        setData(value);
        return this;
    }

    public CommonResult put(T value) {
        setData(value);
        return this;
    }

    public CommonResult<T> message(String msg) {
        this.setMsg(msg);
        return this;
    }
}
