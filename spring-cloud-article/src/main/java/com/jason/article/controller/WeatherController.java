package com.jason.article.controller;

import com.jason.article.service.IWeatherService;
import com.jason.domain.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName WeatherController
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/23 14:58
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private IWeatherService weatherService;

    @RequestMapping("/getWeather")
    public ResultVo getWeather(@RequestBody Map<String, Object> map) {
        return weatherService.getWeather(map);

    }
}
