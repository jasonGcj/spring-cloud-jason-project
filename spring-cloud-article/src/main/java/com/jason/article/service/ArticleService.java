package com.jason.article.service;

import com.jason.article.domain.ArticleDto;
import com.jason.domain.ResultVo;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:07
 */
public interface ArticleService {
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
    ResultVo addSomeOneArticle(ArticleDto dto);

}
