package com.jason.article.service;

import com.jason.article.dto.ArticleDto;
import com.jason.article.dto.ArticleLikeDto;
import com.jason.domain.ResultVo;

import java.util.Map;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:07
 */
public interface IArticleService {
    /**
     * 获取所有文章列表
     * @return
     */
    ResultVo queryArticle();

    /**
     * 获取某个文章
     * @param id
     * @return
     */
    ResultVo queryArticleById(String id);

    /**
     * 添加文章
     * @param dto
     * @return
     */
    ResultVo saveArticleInfo(ArticleDto dto);
    /**
     * 文章点赞 / 收藏
     * @param dto
     * @return
     */
    ResultVo operateArticle(ArticleLikeDto dto);

    void asyncArticleRelation(long account,Map<String, Object> map);

    void asyncArticleInfo(Map<String, Object> map);
}
