package com.jason.article.controller;

import com.jason.article.domain.ArticleDto;
import com.jason.article.service.impl.ArticleService;
import com.jason.domain.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @ClassName ArticleController
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:09
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("获取所有文章列表")
    @GetMapping("/queryArticle")
    public ResultVo queryArticle(){
        return articleService.queryArticle();
    }

    @ApiOperation("获取某个文章")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String")
    @GetMapping("/querySomeOneArticle")
    public ResultVo queryArticleById(@PathParam("id") String id ){
        return articleService.queryArticleById(id);
    }

    @ApiOperation("添加文章")
    @PostMapping("/addSomeOneArticle")
    public ResultVo addSomeOneArticle(@RequestBody ArticleDto dto){
        return articleService.addSomeOneArticle(dto);
    }

}
