package top.teambition.service_base.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.teambition.utils.ResponseResult;

/**
 * @description 全局异常处理器  如果在控制器中没有捕获  就会到这里处理
 * @author liupengyu
 * @version 2021年07月04日 13:30
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e){
        e.printStackTrace();
        return ResponseResult.error();
    }
}
