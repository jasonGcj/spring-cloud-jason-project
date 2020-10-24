package com.jason.user.controller;

import com.jason.domain.ResultVo;
import com.jason.feign.article.WeatherApi;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName TestController
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/11 14:40
 */
@RequestMapping("/test")
@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private WeatherApi weatherApi;

    @RequestMapping("/hello")
    public String hello(){
        if(SystemUtils.IS_OS_LINUX){
            LOGGER.info("生产服务器运行...");
        }else{
            System.out.println("windows运行...");
        }
        return "Weelcome to jason Home";
    }

    /**
     * 错误信息
     * @param
     * @return
     */
    @RequestMapping("/error")
    public ResultVo errorLogin(@RequestParam String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setMessage(StringUtils.equals("LOGOUT",message) ? "请您先登录" : "操作太快了");
        return resultVo;
    }

    /**
     * 错误信息
     * @param
     * @return
     */
    @RequestMapping("/getServerFree")
    public ResultVo getServerFree(@RequestParam String message) {
        return null;
    }

    /**
     * 错误信息
     * @param
     * @return
     */
    @RequestMapping("/testWeather")
    public ResultVo testweather(@RequestBody Map<String,Object> map) {
        map.put("version","v6");
        return weatherApi.getWeather(map);
    }
}
