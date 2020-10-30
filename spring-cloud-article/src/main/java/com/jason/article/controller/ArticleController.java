package com.jason.article.controller;

import com.jason.article.dto.ArticleDto;
import com.jason.article.dto.ArticleLikeDto;
import com.jason.article.service.IArticleService;
import com.jason.domain.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

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
    private IArticleService articleService;

    @ApiOperation("获取所有文章列表")
    @PostMapping("/queryArticle")
    public ResultVo queryArticle(@RequestBody ArticleDto dto){
        return articleService.queryArticle(dto);
    }

    @ApiOperation("获取某个文章")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String")
    @GetMapping("/querySomeOneArticle")
    public ResultVo queryArticleById(@PathParam("id") String id ){
        return articleService.queryArticleById(id);
    }

    @ApiOperation("添加文章")
    @PostMapping("/saveArticleInfo")
    public List<ResultVo> saveArticleInfo(@RequestBody List<ArticleDto> lists){
        ArrayList<ResultVo> list = new ArrayList<>();
        for (ArticleDto articleDto : lists) {
            ResultVo resultVo = articleService.saveArticleInfo(articleDto);
            list.add(resultVo);
        }
        return list;
    }

    @ApiOperation("文章点赞/收藏")
    @PostMapping("/operateArticle")
    public ResultVo operateArticle(@RequestBody ArticleLikeDto dto){
        return articleService.operateArticle(dto);
    }

}
