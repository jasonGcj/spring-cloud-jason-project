package com.jason.feign.article;

import com.jason.domain.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @ClassName WeatherApi
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/24 15:09
 * Fegin
 */
@FeignClient(value = "spring-cloud-article",path = "spring-cloud-article")
public interface WeatherApi {
    @RequestMapping(value = "/weather/getWeather", method = RequestMethod.POST)
    public ResultVo getWeather(Map<String,Object> map);
}
