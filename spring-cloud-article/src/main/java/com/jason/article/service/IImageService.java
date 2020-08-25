package com.jason.article.service;

import com.jason.domain.ResultVo;

/**
 * @ClassName IImageService
 * @Description TODO
 * @Author GCJ
 * @Date 2020/8/25 14:48
 */
public interface IImageService {
    /**
     * 获取首页轮播图
     * @return
     */
    ResultVo queryIndexImage();
}
