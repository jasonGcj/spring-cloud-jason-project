package com.jason.article.controller;

import com.jason.article.service.ArticleService;
import com.jason.article.service.IImageService;
import com.jason.domain.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ImageController
 * @Description TODO
 * @Author GCJ
 * @Date 2020/8/13 12:26
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @ApiOperation("获取轮播图")
    @GetMapping("/queryIndexImage")
    public ResultVo queryArticle(){
        return imageService.queryIndexImage();
    }
}
