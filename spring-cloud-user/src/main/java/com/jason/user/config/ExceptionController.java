package com.jason.user.config;

import com.jason.domain.ResultVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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

    // 专门用来捕获和处理Controller层的运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResultVo> runtimeExceptionHandler(RuntimeException e){
            /*StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            System.out.println(str);*/
            ResultVo result = new ResultVo();
            result.setOk(false);
            result.setCode(500);
            result.setMessage("服务端异常，请稍后再试");
            return ResponseEntity.status(200).body(result);
    }

    // 专门用来捕获和处理Controller层的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e){
        ResultVo result = new ResultVo();
        result.setOk(false);
        result.setCode(500);
        result.setMessage("服务端异常，请稍后再试");
        return ResponseEntity.status(200).body(result);
    }
}
