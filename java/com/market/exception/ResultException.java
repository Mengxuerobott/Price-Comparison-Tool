package com.market.exception;

import com.market.utils.Constant;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义 controller返回结果 异常
 * runtime exception 才会事务回滚
 */
@Slf4j
public class ResultException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String msg= Constant.ERROR_MSG;
    private int code = 500;

	public ResultException() {
	}

	public ResultException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ResultException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public ResultException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public ResultException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
