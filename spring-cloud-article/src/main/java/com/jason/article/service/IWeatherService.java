package com.jason.article.service;

import com.jason.domain.ResultVo;

import java.util.Map;

/**
 * @ClassName IWeatherService
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/24 15:27
 */
public interface IWeatherService {
    ResultVo getWeather(Map<String, Object> map);
}
