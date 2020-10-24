package com.jason.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.jason.article.controller.WeatherController;
import com.jason.article.service.IWeatherService;
import com.jason.domain.ResultVo;
import com.jason.domain.WeatherEntity;
import com.jason.utils.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @ClassName WeatherServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/24 15:27
 */
@Service
public class WeatherServiceImpl implements IWeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Value("${jason.weather.url}")
    private String url;

    @Value("${jason.weather.appid}")
    private String appid;

    @Value("${jason.weather.appsecret}")
    private String appsecret;

    @Override
    public ResultVo getWeather(Map<String, Object> map) {
        if(CollectionUtils.isEmpty(map)){
            return new ResultVo(false,500,"parameter is null");
        }
        LOGGER.info("weather is parameter : {}", JSON.toJSONString(map));
        map.put("appid",appid);
        map.put("appsecret",appsecret);
        String result = RestUtils.getHttp(url, map);
        LOGGER.info("weather is result : {}",result);
        WeatherEntity weatherEntity = null;
        try {
            weatherEntity = JSON.parseObject(result, WeatherEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("weather is exception : {}",e.getMessage());
            return new ResultVo(false,500,"接口异常查询失败"+e.getMessage());
        }
        if(null != weatherEntity){
            return new ResultVo(true,200,"查询成功",weatherEntity);
        }
        return new ResultVo(false,500,"接口异常查询失败");
    }
}
