package com.jason.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName WeatherEntity
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/23 9:46
 */
public class WeatherEntity implements Serializable {
    /**
     * 城市ID
     */
    private String cityid;
    /**
     * 当前日期
     */
    private String date;
    /**
     * 当前星期
     */
    private String week;
    /**
     * 气象台更新时间
     */
    private String update_time;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 城市英文名称
     */
    private String cityEn;
    /**
     * 国家名称
     */
    private String country;
    /**
     * 国家英文名称
     */
    private String countryEn;
    /**
     * 天气情况
     */
    private String wea;
    /**
     * 天气对应图标
     * 固定9种类型(您也可以根据wea字段自己处理):
     * xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
     */
    private String wea_img;
    /**
     * 实时温度
     */
    private String tem;
    /**
     * 高温
     */
    private String tem1;
    /**
     * 低温
     */
    private String tem2;
    /**
     * 风向
     */
    private String win;
    /**
     * 风力等级
     */
    private String win_speed;
    /**
     * 风速
     */
    private String win_meter;
    /**
     * 湿度
     */
    private String humidity;
    /**
     * 能见度
     */
    private String visibility;
    /**
     * 气压hPa
     */
    private String pressure;
    /**
     * 空气质量
     */
    private String air;
    private String air_pm25;
    /**
     * 空气质量等级
     */
    private String air_level;
    /**
     * 空气质量描述
     */
    private String air_tips;
    private String alarm;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWea_img() {
        return wea_img;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public void setWin_speed(String win_speed) {
        this.win_speed = win_speed;
    }

    public String getWin_meter() {
        return win_meter;
    }

    public void setWin_meter(String win_meter) {
        this.win_meter = win_meter;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getAir_pm25() {
        return air_pm25;
    }

    public void setAir_pm25(String air_pm25) {
        this.air_pm25 = air_pm25;
    }

    public String getAir_level() {
        return air_level;
    }

    public void setAir_level(String air_level) {
        this.air_level = air_level;
    }

    public String getAir_tips() {
        return air_tips;
    }

    public void setAir_tips(String air_tips) {
        this.air_tips = air_tips;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }
}
