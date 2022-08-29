package com.market.exception;

import com.market.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 异常处理器
 */
@ControllerAdvice
public class ResultExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static final String error="error";

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ResultException.class)
    public Object handleRRException(HttpServletRequest request, HttpServletResponse response, ResultException e, Model model) {
        model.addAttribute("message",e.getMessage() );
        model.addAttribute("url", request.getRequestURL());
        return "error";
    }

    /**
     * 处理缺失参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleParameterException(MissingServletRequestParameterException e,Model model) {
        logger.error(e.getMessage(), e);
        model.addAttribute("message","参数<" + e.getParameterName() + ">缺失" );
        return "error";
    }

    /**
     * 处理校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidException(MethodArgumentNotValidException e,Model model) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        HashMap<String, String> map = new HashMap<>();
        for (ObjectError objectError : allErrors) {
            if (objectError instanceof FieldError) {
                map.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
            } else {
                map.put(objectError.getObjectName(), objectError.getDefaultMessage());
            }
        }
        model.addAttribute("map",map);
        model.addAttribute("message","参数校验出错");
        return error;
    }

    /**
     * 处理主键重复异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public CommonResult handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return CommonResult.error("数据库中已存在该记录");
    }

    //dev模式下便于查错
    @Profile("dev")
    @ExceptionHandler(Exception.class)
    public CommonResult handleDevException(Exception e) {
        logger.error(e.getMessage(), e);
        String msg = e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getMessage();
        return CommonResult.error(msg);
    }

}
