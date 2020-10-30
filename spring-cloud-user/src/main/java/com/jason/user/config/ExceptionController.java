package com.jason.user.config;

import com.jason.domain.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName ExceptionController
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/10 9:57
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    // 专门用来捕获和处理Controller层的运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResultVo> runtimeExceptionHandler(RuntimeException e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            LOGGER.info("服务端异常，请稍后再试:"+str);
            ResultVo result = new ResultVo();
            result.setOk(false);
            result.setCode(500);
            result.setMessage("服务端异常，请稍后再试:"+e.getMessage());
            return ResponseEntity.status(200).body(result);
    }

    // 专门用来捕获和处理Controller层的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        LOGGER.info("服务端异常，请稍后再试:"+str);
        ResultVo result = new ResultVo();
        result.setOk(false);
        result.setCode(500);
        result.setMessage("服务端异常，请稍后再试:"+e.getMessage());
        return ResponseEntity.status(200).body(result);
    }
}
