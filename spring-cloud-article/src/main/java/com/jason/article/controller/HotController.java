package com.jason.article.controller;

import com.jason.article.service.IArticleService;
import com.jason.article.service.IImageService;
import com.jason.domain.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @ClassName ImageController
 * @Description TODO
 * @Author GCJ
 * @Date 2020/8/13 12:26
 */
@RestController
@RequestMapping("/hot")
public class HotController {
    @Autowired
    private IImageService imageService;

    @Autowired
    private IArticleService articleService;

    @ApiOperation("获取轮播图")
    @GetMapping("/queryIndexImage")
    public ResultVo queryArticle(){
        return imageService.queryIndexImage();
    }

    @ApiOperation("获取所有文章热点数据")
    @GetMapping("/showArticle")
    @ApiImplicitParam(name = "flag", value = "LIKED_COUNT", required = true, dataType = "String")
    public ResultVo showArticle(@PathParam("flag") String flag ){
        return articleService.showArticle(flag);
    }
}
